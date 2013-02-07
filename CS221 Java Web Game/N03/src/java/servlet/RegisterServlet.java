package servlet;

import controller.ServerController;
import controller.SessionController;
import entity.Server;
import entity.User;
import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import facade.UserFacade;
import javax.ejb.EJBException;
import javax.servlet.http.HttpServlet;

/**
 *
 * @author James Bowcott
 */
@WebServlet(name = "Register", urlPatterns = {"/Register"})
public class RegisterServlet extends HttpServlet {

    @EJB
    SessionController sessionController;
    @EJB
    UserFacade userFacade;
    @EJB ServerController serverController;
    private static final String ERROR_BAD_REGISTRATION = "Invalid user details";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	String username = request.getParameter("username");
	String password = request.getParameter("password");
	String name = request.getParameter("name");

	User user = new User(username, name, password);

	if (userFacade.validate(user)) {
            if (userFacade.findByUserId(username) != null) {
                request.setAttribute("error", "Username already exists");
                request.setAttribute("result", false);
            } else {
                try {
                    userFacade.create(user);
                    request.setAttribute("result", true);
                } catch (Exception e) {
                    // User already exists
                    request.setAttribute("error", e.toString());
                    request.setAttribute("result", false);
                }
            }
	} else {
	    request.setAttribute("error", ERROR_BAD_REGISTRATION);
	    request.setAttribute("result", false);
	}

	request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
    }
}
