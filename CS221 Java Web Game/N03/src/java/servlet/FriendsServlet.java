package servlet;

import controller.FriendshipController;
import controller.SessionController;
import controller.UserController;
import entity.Friendship;
import entity.Server;
import entity.User;
import facade.FriendshipFacade;
import facade.ServerFacade;
import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author James Bowcott
 */
@WebServlet(name = "Friends", urlPatterns = {"/Friends"})
public class FriendsServlet extends HttpServlet {

    @EJB
    FriendshipFacade friendshipFacade;
    @EJB
    ServerFacade serverFacade;
    @EJB
    FriendshipController friendshipController;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	User user = SessionController.getSessionUser(request.getSession());

	/* Friends */
	List<Friendship> friends = friendshipFacade.findFriends(user.getUserID());
	request.setAttribute("friends", friends);

	/* Received requests */
	List<Friendship> requests = friendshipFacade.findReceivedRequests(user.getUserID());
	request.setAttribute("receivedRequests", requests);

	/* Sent requests */
	List<Friendship> sentRequests = friendshipFacade.findSentRequests(user.getUserID());
	request.setAttribute("sentRequests", sentRequests);

	/* DirectoryServer list */
	List<Server> servers = serverFacade.findAll();
	request.setAttribute("servers", servers);
	
	/* Server Users */
	String remoteServerID = request.getParameter("remoteServerID");
	if (remoteServerID != null) {
	    try {
		Integer remoteServerNumber = Integer.parseInt(remoteServerID);
		Server server = serverFacade.find(remoteServerNumber);
		List<User> users = UserController.getRemoteUsers(server);                
		request.setAttribute("remoteUsers", users);
		request.setAttribute("selectedServerID", remoteServerID);
	    } catch (Exception ex) {
		request.setAttribute("error", ex);
		ex.printStackTrace();
	    }
	}
	
	request.getRequestDispatcher("/WEB-INF/friends.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	String remoteUserId = request.getParameter("remoteUserID");
	String remoteServerId = request.getParameter("remoteServerID");
	Integer remoteServerNumber = null;
	if (remoteServerId != null) {
	    remoteServerNumber = Integer.parseInt(remoteServerId);
	}
	String delete = request.getParameter("delete");
	String accept = request.getParameter("accept");
	String reject = request.getParameter("reject");

	try {
	    if (delete != null && delete.length() > 0) {
		delete(Long.valueOf(delete));
	    } else if (accept != null && accept.length() > 0) {
		accept(Long.valueOf(accept));
	    } else if (reject != null && reject.length() > 0) {
		delete(Long.valueOf(reject));
	    } else if (remoteUserId != null) {
		newFriendRequest(SessionController.getSessionUser(request.getSession()).getUserID(), remoteUserId, serverFacade.find(remoteServerNumber));
	    }
	} catch (Exception ex) {
	    request.setAttribute("error", ex.getMessage());
	}

	doGet(request, response);

    }
    
     
    
    private void delete(Long id) throws Exception {
	Friendship f = friendshipFacade.find(id);
	if (f != null) {
	    try {
		friendshipController.unfriend(f);
	    } catch (EJBException ex) {
		throw new Exception("Could not send friend deletion request to remote server (" + ex.getCausedByException() + ")");
	    }
	} else {
	    throw new Exception("The friendship could not be found");
	}
    }

    private void accept(Long id) throws Exception {
	Friendship f = friendshipFacade.find(id);
	if (f != null) {
	    try {
		friendshipController.acceptRequest(f);
	    } catch (EJBException ex) {
		throw new Exception("Could not send friend request acception to remote server (" + ex.getCausedByException() + ")");
	    }
	} else {
	    throw new Exception("The friendship could not be found");
	}
    }

    private void newFriendRequest(String localUserID, String remoteUserId, Server remoteServer) throws Exception {
	try {
	    if (friendshipController.createRequest(localUserID, remoteUserId, remoteServer) == null) {
		throw new Exception("Could not send friend request to remote server. Does the user id exist?");
	    }
	} catch (EJBException ex) {
	    throw new Exception("Could not send friend request to remote server (" + ex.getCausedByException().toString() + ")");
	}
    }

}
