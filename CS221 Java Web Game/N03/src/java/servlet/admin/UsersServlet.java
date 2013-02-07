/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet.admin;

import entity.User;
import facade.UserFacade;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
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
@WebServlet(name = "UsersServlet", urlPatterns = {"/Admin"})
public class UsersServlet extends HttpServlet {
    @EJB UserFacade userFacade;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	
	List<User> users = userFacade.findAll();
	request.setAttribute("users", users);
	
	request.getRequestDispatcher("/WEB-INF/admin.jsp").forward(request, response);
	
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	
	if (request.getParameter("delete") != null) {
	    User u = userFacade.find(Long.valueOf(request.getParameter("delete")));
	    userFacade.remove(u);
	} else if (request.getParameter("reset_password") != null) {
	    User u = userFacade.find(Long.valueOf(request.getParameter("reset_password")));
	    String p = request.getParameter("password");
	    if (p != null && p.length() > 2) {
		u.setPassword(p);
		userFacade.edit(u);
	    } else {
		request.setAttribute("error", "New password not entered or too short");
	    }
	}
	
	doGet(request, response);
    }

}