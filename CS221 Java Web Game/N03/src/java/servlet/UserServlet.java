package servlet;

import controller.SessionController;
import entity.User;
import facade.UserFacade;
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
@WebServlet(name = "User", urlPatterns = {"/User"})
public class UserServlet extends HttpServlet {

    @EJB
    UserFacade userFacade;
    @EJB
    SessionController sessionController;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	req.getRequestDispatcher("/WEB-INF/user.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	String name = request.getParameter("name");
        String avatarURL = request.getParameter("avatarURL");
	String password = request.getParameter("password");
	String verify = request.getParameter("verify");
	User user = sessionController.getSessionUser(request.getSession());

	user.setName(name);
        user.setAvatarURL(avatarURL);
	
	if (password != null) {
	    if (verify != null && password.equals(verify)) {
		user.setPassword(password);
	    } else {
		request.setAttribute("error", "Passwords do not match");
	    }
	}

	if (userFacade.validate(user)) {
	    userFacade.edit(user);
	} else {
	    request.setAttribute("error", "New user details not valid");
	}

	doGet(request, response);

    }
}
