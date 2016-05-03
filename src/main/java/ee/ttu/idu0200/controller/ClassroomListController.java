package ee.ttu.idu0200.controller;

import ee.ttu.idu0200.db.ClassroomDao;
import ee.ttu.idu0200.model.Classroom;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ClassroomListController {

  @Inject
  private ClassroomDao classroomDao;

  public String findAll(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    List<Classroom> classroomList = classroomDao.findAll();
    req.setAttribute("classroomList", classroomList);

    return "/WEB-INF/jsp/classroom/classroomList.jsp";
  }
}
