package servlet;

import client.MonsterClient;
import com.sun.jersey.api.client.UniformInterfaceException;
import controller.SessionController;
import entity.Friendship;
import entity.Fight;
import entity.RemoteMonster;
import entity.User;
import facade.FriendshipFacade;
import facade.FightFacade;
import facade.ServerFacade;
import facade.UserFacade;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONException;

/**
 *
 * @author James Bowcott
 */
@WebServlet(name = "Home", urlPatterns = {"/Home"})
public class HomeServlet extends HttpServlet {

    @EJB
    FightFacade monsterRequestFacade;
    @EJB
    FriendshipFacade friendshipFacade;
    @EJB
    UserFacade userFacade;
    @EJB
    ServerFacade serverFacade;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	User user = SessionController.getSessionUser(request.getSession());

	/* Fight Requests */
	List<Fight> fightRequests = monsterRequestFacade.findFightsForUser(user.getUserID(), Fight.FightStatus.RECEIVED);
	request.setAttribute("fightRequests", fightRequests);

	/* Friend Requests */
	List<Friendship> friendRequests = friendshipFacade.findReceivedRequests(user.getUserID());
	request.setAttribute("friendRequests", friendRequests);

	try {
	    /* Friends monsters */
	    List<Friendship> friends = friendshipFacade.findFriends(user.getUserID());
	    List<RemoteMonster> monsters = new ArrayList<RemoteMonster>();
	    for (Friendship f : friends) {
		MonsterClient monsterClient = new MonsterClient(serverFacade.get(f.getRemoteServerNumber(), false));
		try {
		    monsters.addAll(monsterClient.getMonsters(f.getRemoteUserID()));
		} catch (Exception ex) {
		}
	    }
	    request.setAttribute("remoteMonsters", monsters);

	} catch (Exception ex) {
	    request.setAttribute("error", "Unable to retrieve friend's monsters");
	}


	request.getRequestDispatcher("/WEB-INF/home.jsp").forward(request, response);
    }
}
