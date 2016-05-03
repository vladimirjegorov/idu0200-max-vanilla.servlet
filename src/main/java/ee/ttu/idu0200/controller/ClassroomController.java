package ee.ttu.idu0200.controller;

import ee.ttu.idu0200.converter.ClassroomConverter;
import ee.ttu.idu0200.db.ClassroomDao;
import ee.ttu.idu0200.form.ClassroomForm;
import ee.ttu.idu0200.model.Classroom;
import ee.ttu.idu0200.validator.ClassroomFormValidator;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class ClassroomController {

  public static final String CLASSROOM_JSP = "/WEB-INF/jsp/classroom/classroom.jsp";
  public static final String CLASSROOM_NOT_FOUND_JSP = "/WEB-INF/jsp/classroom/classroomNotFound.jsp";

  @Inject
  private ClassroomDao classroomDao;

  @Inject
  private ClassroomFormValidator classroomFormValidator;

  @Inject
  private ClassroomConverter classroomConverter;

  public String findById(HttpServletRequest req, HttpServletResponse resp) {
    String idAttribute = req.getParameter("id");
    Long id = Long.valueOf(idAttribute);

    Classroom classroom = classroomDao.findById(id);
    if (classroom == null) {
      return CLASSROOM_NOT_FOUND_JSP;
    }

    ClassroomForm classroomForm = classroomConverter.toClassroomForm(classroom);

    req.setAttribute("classroom", classroomForm);
    return CLASSROOM_JSP;
  }

  public String save(HttpServletRequest req, HttpServletResponse resp) {
    ClassroomForm classroomForm = classroomConverter.toClassroomForm(req);
    Map<String, String> bindingResult = classroomFormValidator.validate(classroomForm);
    req.setAttribute("bindingResult", bindingResult);
    if (bindingResult.isEmpty()) {
      Classroom classroom = classroomConverter.toClassroom(classroomForm);
      classroomDao.update(classroom);
      Classroom classroomReloaded = classroomDao.findById(classroom.getId());
      ClassroomForm classroomFormReloaded = classroomConverter.toClassroomForm(classroomReloaded);
      req.setAttribute("classroom", classroomFormReloaded);
      req.setAttribute("successMessage", "saveSuccess");
    } else {
      req.setAttribute("classroom", classroomForm);
    }

    return CLASSROOM_JSP;
  }
}
