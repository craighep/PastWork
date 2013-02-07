package service;
 
import entity.Friendship;
import entity.Server;
import entity.User;
import facade.FriendshipFacade;
import facade.ServerFacade;
import facade.UserFacade;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
/**
 * @author James Bowcott
 */
@WebServlet(name = "FriendRequestService", urlPatterns = {"/service/friends/request"})
public class FriendRequestService extends HttpServlet {
 
    @EJB FriendshipFacade friendshipFacade;
    @EJB UserFacade userFacade;
    @EJB ServerFacade serverFacade;
 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
 	
	Map params = request.getParameterMap();
	String[] requiredParams = new String[] {"friendID", "localUserID", "remoteUserID", "remoteServerNumber"};
	if (!params.keySet().containsAll(Arrays.asList(requiredParams))) {
	    response.sendError(
		    HttpServletResponse.SC_BAD_REQUEST,
		    "Parameter set incorrect");
	} else {
	  
	    User user = userFacade.findByUserId(request.getParameter("localUserID"));
	    
	    if (user == null) {
		response.sendError(HttpServletResponse.SC_BAD_REQUEST,
			"Local User ID does not exist");
	    } else {
		Friendship f = new Friendship();
		f.setFriendID(request.getParameter("friendID"));
		f.setLocalUser(user);
		f.setRemoteUserID(request.getParameter("remoteUserID"));
		
		Integer serverNumber = Integer.parseInt(request.getParameter("remoteServerNumber"));
		Server server = serverFacade.get(serverNumber, false);
		f.setRemoteServer(server);
		
		f.setStatus(Friendship.StatusReceived);
		
		friendshipFacade.create(f);
	    }
	    
	}
 
    }
}