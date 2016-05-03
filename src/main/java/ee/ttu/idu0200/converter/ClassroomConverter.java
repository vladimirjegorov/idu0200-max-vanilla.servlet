package ee.ttu.idu0200.converter;

import ee.ttu.idu0200.form.ClassroomForm;
import ee.ttu.idu0200.model.Classroom;

import javax.servlet.http.HttpServletRequest;

import static org.apache.commons.lang3.time.DateUtils.parseDate;

public class ClassroomConverter {

  public ClassroomForm toClassroomForm(HttpServletRequest request) {
    ClassroomForm classroomForm = new ClassroomForm();
    classroomForm.setId(request.getParameter("id"));
    classroomForm.setTitle(request.getParameter("title"));
    classroomForm.setSeatingCapacity(request.getParameter("seatingCapacity"));
    classroomForm.setDescription(request.getParameter("description"));

    return classroomForm;
  }

  public ClassroomForm toClassroomForm(Classroom classroom) {
    ClassroomForm classroomForm = new ClassroomForm();
    classroomForm.setId(String.valueOf(classroom.getId()));
    classroomForm.setTitle(classroom.getTitle());
    classroomForm.setSeatingCapacity(String.valueOf(classroom.getSeatingCapacity()));
    classroomForm.setDescription(classroom.getDescription());
    return classroomForm;
  }

  public Classroom toClassroom(ClassroomForm classroomForm) {
    Classroom classroom = new Classroom();
    classroom.setId(Long.valueOf(classroomForm.getId()));
    classroom.setTitle(classroomForm.getTitle());

    String seatingCapacity = classroomForm.getSeatingCapacity();
    classroom.setSeatingCapacity(Integer.valueOf(seatingCapacity));
    classroom.setDescription(classroomForm.getDescription());

    return classroom;
  }
}
