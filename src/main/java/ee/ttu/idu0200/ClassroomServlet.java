package ee.ttu.idu0200;

import ee.ttu.idu0200.controller.ClassroomController;
import ee.ttu.idu0200.controller.ClassroomListController;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.apache.commons.lang3.StringUtils.isNotEmpty;
import static org.apache.commons.lang3.math.NumberUtils.isNumber;
import static org.apache.logging.log4j.LogManager.getLogger;

@WebServlet({"/classroom/s"})
public class ClassroomServlet extends HttpServlet {

  private static Logger LOG = getLogger(ClassroomServlet.class);

  @Inject
  private ClassroomListController classroomListController;

  @Inject
  private ClassroomController classroomController;

  @Override
  public void init() throws ServletException {
    LOG.info("ClassroomServlet.init() : I was created");
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String idParameter = req.getParameter("id");
    String viewName = "";

    if (isNotEmpty(idParameter) && isNumber(idParameter)) {
      viewName = classroomController.findById(req, resp);
    } else {
      viewName = classroomListController.findAll(req, resp);
    }
    getServletContext().getRequestDispatcher(viewName).forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String viewName = "";

    String action = req.getParameter("action");
    if ("save".equals(action)) {
      viewName = classroomController.save(req, resp);
    }

    getServletContext().getRequestDispatcher(viewName).forward(req, resp);
  }
}
