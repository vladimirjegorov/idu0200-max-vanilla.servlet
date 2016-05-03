package ee.ttu.idu0200.db;

import ee.ttu.idu0200.model.Classroom;
import org.apache.logging.log4j.Logger;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static org.apache.logging.log4j.LogManager.getLogger;

public class ClassroomDao {

  private static Logger LOG = getLogger(ClassroomDao.class);

  public static final String SELECT_ALL_CLASSROOMS = "SELECT id, title, seating_capacity, description FROM classroom";
  public static final String SELECT_CLASSROOM_BY_ID = "SELECT id, title, seating_capacity, description FROM classroom WHERE id = ?";
  public static final String UPDATE_CLASSROOM = "UPDATE classroom SET title=?, seating_capacity=?, description=? WHERE id=?";

  @Resource(name = "jdbc/dataSource")
  private DataSource dataSource;


  public Classroom findById(long id) {
    try {
      try (Connection conn = dataSource.getConnection()) {
        PreparedStatement sql = conn.prepareStatement(SELECT_CLASSROOM_BY_ID);
        sql.setLong(1, id);

        LOG.info("findById: id = " + id);

        ResultSet set = sql.executeQuery();
        if (set.next()) {
          return toClassroom(set);
        }
        return null;
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public List<Classroom> findAll() {
    ArrayList<Classroom> classrooms = new ArrayList<>();
    try {
      try (Connection conn = dataSource.getConnection()) {
        PreparedStatement sql = conn.prepareStatement(SELECT_ALL_CLASSROOMS);
        ResultSet set = sql.executeQuery();
        while (set.next()) {
          classrooms.add(toClassroom(set));
        }
        LOG.info("findAll: size = " + classrooms.size());
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    return classrooms;
  }

  public void update(Classroom classroom) {
    try {
      try (Connection conn = dataSource.getConnection()) {
        PreparedStatement sql = conn.prepareStatement(UPDATE_CLASSROOM);
        sql.setString(1, classroom.getTitle());
        sql.setInt(2, classroom.getSeatingCapacity());
        sql.setString(3, classroom.getDescription());
        sql.setLong(4, classroom.getId());

        sql.executeUpdate();
        LOG.info("update: id = " + classroom.getId());
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  private Classroom toClassroom(ResultSet set) throws SQLException {
    Classroom classroom = new Classroom();
    classroom.setId(set.getLong("id"));
    classroom.setTitle(set.getString("title"));
    classroom.setSeatingCapacity(set.getInt("seating_capacity"));
    classroom.setDescription(set.getString("description"));
    return classroom;
  }


}
