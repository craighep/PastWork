package servlet;

import controller.SessionController;
import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author James Bowcott
 */
@WebServlet(name="Login", urlPatterns={"/Login"})
public class LoginServlet extends HttpServlet {
    @EJB
    SessionController sessionController;
    
    private static final String ERROR_NO_PASSWORD = "No password entered";
    private static final String ERROR_BAD_LOGIN = "Username or password not valid";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	if (SessionController.verifySessionUser(request.getSession(false))) {
	    response.sendRedirect("Home");
	} else {
	    request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
	}
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	String username = request.getParameter("username");
	String password = request.getParameter("password");
	
	if (username != null && username.length() > 0) {
	    if (password != null && password.length() > 0) {
		if (sessionController.login(request.getSession(), username, password)) {
		    response.sendRedirect("Home");
		    return;
		} else {
		    request.setAttribute("error", ERROR_BAD_LOGIN);
		}
	    } else {
		request.setAttribute("error", ERROR_NO_PASSWORD);
	    }

	}
	
	doGet(request, response);
    }
    
}
