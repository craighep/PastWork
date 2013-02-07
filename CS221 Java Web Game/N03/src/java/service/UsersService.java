package service;

import entity.User;
import facade.UserFacade;
import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author James Bowcott
 **/
@WebServlet(name = "UsersService", urlPatterns = {"/service/users"})
public class UsersService extends HttpServlet {

    @EJB
    UserFacade userFacade;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	
	// Setup what kind of content we are returning and the PrintWriter
	response.setContentType("text/plain;charset=UTF-8");
	PrintWriter out = response.getWriter();

	try {
	    String userId = request.getParameter("userID");
	    
	    if (userId == null) {
		getJsonUsers().write(out);
	    } else {
		// Find the user object with the specified userId
		User user = userFacade.findByUserId(userId);	
		if (user != null) {
		    // User was found. Write it out in JSON
		    getJsonUser(user).write(out);
		} else {
		    // User was not found. Send a Bad Request HTTP error code.
		    response.sendError(response.SC_BAD_REQUEST,
			    "User not found");
		}
	    }

	} catch (JSONException ex) {
	    // A JSONException may occur if any malformed data is passed
	    // to the producers.
	    System.err.println("JSON Exception:");
	    System.err.println(ex);
	    response.sendError(response.SC_SERVICE_UNAVAILABLE);
	} finally {
	    out.close();
	}
    }
    

    private JSONObject getJsonUser(User user) throws JSONException {
	JSONObject jsonUser = new JSONObject();
	jsonUser.put("userID", user.getUserID());
	jsonUser.put("name", user.getName());
	jsonUser.put("money", user.getMoney());
	return jsonUser;
    }

    private JSONArray getJsonUsers() throws JSONException {
	JSONArray jsonUsers = new JSONArray();
	for (User user : userFacade.findAll()) {
	    jsonUsers.put(getJsonUser(user));
	}
	return jsonUsers;
    }
}
