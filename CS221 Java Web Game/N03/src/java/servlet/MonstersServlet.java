package servlet;

import controller.MonsterController;
import controller.SessionController;
import entity.LocalMonster;
import entity.Fight;
import entity.Fight.FightStatus;
import entity.User;
import facade.FriendshipFacade;
import facade.MonsterFacade;
import facade.FightFacade;
import facade.ServerFacade;
import java.io.IOException;
import java.util.Collections;
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
@WebServlet(name = "MonstersServlet", urlPatterns = {"/Monsters"})
public class MonstersServlet extends HttpServlet {

    @EJB
    MonsterFacade monsterFacade;
    @EJB
    FriendshipFacade friendshipFacade;
    @EJB
    ServerFacade serverFacade;
    @EJB
    FightFacade requestFacade;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	User user = SessionController.getSessionUser(request.getSession());
	
	if (request.getParameter("generate") != null) {
	    LocalMonster m = new LocalMonster(user.getUserID());
	    monsterFacade.create(m);
	}
	
	if (request.getParameter("delete") != null) {
	    LocalMonster m = monsterFacade.find(request.getParameter("delete"));
	    if (m != null) {
		monsterFacade.remove(m);
	    }
	}


	// Monster list
	List<LocalMonster> monsters = monsterFacade.findByUser(user.getUserID());
	request.setAttribute("monsters", monsters);
	
	// Requests
	List<Fight> receivedRequests = requestFacade.findFightsForUser(user.getUserID(), FightStatus.RECEIVED);
	request.setAttribute("receivedRequests", receivedRequests);
	List<Fight> sentRequests = requestFacade.findFightsForUser(user.getUserID(), FightStatus.SENT);
	request.setAttribute("sentRequests", sentRequests);
	
	List<Fight> completedRequests = requestFacade.findCompletedFightsForUser(user.getUserID());
	Collections.reverse(completedRequests);
	request.setAttribute("completedRequests", completedRequests);
	
	request.getRequestDispatcher("/WEB-INF/monsters.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	
	doGet(request, response);
    }
}
