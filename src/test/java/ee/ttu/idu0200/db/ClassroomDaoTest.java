package ee.ttu.idu0200.db;

import ee.ttu.idu0200.model.Classroom;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ClassroomDaoTest {

  @InjectMocks
  private ClassroomDao classroomDao;
  @Mock
  private DataSource dataSourceMock;

  private Connection connectionMock;
  private PreparedStatement preparedStatementMock;

  @Before
  public void setUp() throws SQLException {
    connectionMock = mock(Connection.class);
    when(dataSourceMock.getConnection()).thenReturn(connectionMock);
    preparedStatementMock = mock(PreparedStatement.class);
    when(connectionMock.prepareStatement(anyString())).thenReturn(preparedStatementMock);
  }

  @Test
  public void findByIdReturnsClassroomWhenClassroomFound() throws Exception {
    ResultSet resultSetMock = mock(ResultSet.class);
    when(resultSetMock.next()).thenReturn(true);
    when(resultSetMock.getLong("id")).thenReturn(123L);
    when(resultSetMock.getString("title")).thenReturn("Title");
    when(resultSetMock.getInt("seating_capacity")).thenReturn(11);
    when(resultSetMock.getString("description")).thenReturn("Description");

    when(preparedStatementMock.executeQuery()).thenReturn(resultSetMock);

    Classroom classroom = classroomDao.findById(123L);

    verify(preparedStatementMock).setLong(1, 123L);
    assertEquals(123L, classroom.getId());
    assertEquals("Title", classroom.getTitle());
    assertEquals(11, classroom.getSeatingCapacity());
    assertEquals("Description", classroom.getDescription());
  }

  @Test
  public void findByIdReturnsNullWhenClassroomNotFound() throws Exception {
    ResultSet resultSetMock = mock(ResultSet.class);
    when(resultSetMock.next()).thenReturn(false);

    when(preparedStatementMock.executeQuery()).thenReturn(resultSetMock);

    Classroom classroom = classroomDao.findById(123L);

    verify(preparedStatementMock).setLong(1, 123L);
    assertNull(classroom);
  }

  @Test
  public void findAllReturnsAListOfClassrooms() throws Exception {
    ResultSet resultSetMock = mock(ResultSet.class);
    when(resultSetMock.next()).thenReturn(true, true, false);
    when(resultSetMock.getLong("id")).thenReturn(123L, 124L);
    when(resultSetMock.getString("title")).thenReturn("Title1", "Title2");
    when(resultSetMock.getInt("seating_capacity")).thenReturn(11, 12);
    when(resultSetMock.getString("description")).thenReturn("Description1", "Description2");

    when(preparedStatementMock.executeQuery()).thenReturn(resultSetMock);

    List<Classroom> classroomList = classroomDao.findAll();

    Classroom classroom0 = classroomList.get(0);
    assertEquals(123L, classroom0.getId());
    assertEquals("Title1", classroom0.getTitle());
    assertEquals(11, classroom0.getSeatingCapacity());
    assertEquals("Description1", classroom0.getDescription());

    Classroom classroom1 = classroomList.get(1);
    assertEquals(124L, classroom1.getId());
    assertEquals("Title2", classroom1.getTitle());
    assertEquals(12, classroom1.getSeatingCapacity());
    assertEquals("Description2", classroom1.getDescription());
  }

  @Test
  public void updatesClassroom() throws Exception {
    Classroom classroom = new Classroom();
    classroom.setId(123L);
    classroom.setTitle("Title");
    classroom.setSeatingCapacity(11);
    classroom.setDescription("Description");

    classroomDao.update(classroom);
    verify(preparedStatementMock).setString(1, "Title");
    verify(preparedStatementMock).setInt(2, 11);
    verify(preparedStatementMock).setString(3, "Description");
    verify(preparedStatementMock).setLong(4, 123L);
    verify(preparedStatementMock).executeUpdate();
    verifyNoMoreInteractions(preparedStatementMock);
  }
}