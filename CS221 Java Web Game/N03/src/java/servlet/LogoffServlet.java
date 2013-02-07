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
@WebServlet(name = "Logoff", urlPatterns = {"/Logoff"})
public class LogoffServlet extends HttpServlet {
    @EJB
    SessionController sessionController;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	sessionController.logoff(request.getSession());
	response.sendRedirect("Login");
    }

    @Override
    public String getServletInfo() {
	return "Invalidates the session and redirects to Login servlet";
    }
}
