package service;
 
import entity.Friendship;
import facade.FriendshipFacade;
import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
/**
 * @author James Bowcott
 */
@WebServlet(name = "FriendAcceptRejectService", urlPatterns = 
	{"/service/friends/accept", "/service/friends/reject"})
public class FriendAcceptRejectService extends HttpServlet {
 
    @EJB FriendshipFacade friendshipFacade;
 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	
	String friendID = request.getParameter("friendID");
	
	if (friendID == null) {
	    response.sendError(HttpServletResponse.SC_BAD_REQUEST,
		    "Friend ID not specified");
	} else {
	    List<Friendship> fs = friendshipFacade.findByFriendID(friendID);
	    if (fs.isEmpty()) {
		response.sendError(HttpServletResponse.SC_BAD_REQUEST,
			"Friend ID not found on this server");
	    } else {
		if (request.getServletPath().equalsIgnoreCase("/service/friends/accept")) {
		    for (Friendship f : fs){
			f.setStatus(Friendship.StatusAccepted);
			friendshipFacade.edit(f);
		    }
		} else if (request.getServletPath().equalsIgnoreCase("/service/friends/reject")) {
		    for (Friendship f : fs) {
			friendshipFacade.remove(f);
		    }
		}
	    }
	}
 
    }
}