package servlet;

import client.MonsterClient;
import controller.FightRequestController;
import controller.MonsterController;
import controller.SessionController;
import entity.LocalMonster;
import entity.Fight;
import entity.Fight.FightStatus;
import entity.RemoteMonster;
import entity.User;
import facade.FriendshipFacade;
import facade.MonsterFacade;
import facade.FightFacade;
import facade.ServerFacade;
import java.io.IOException;
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
@WebServlet(name = "FightServlet", urlPatterns = {"/Fight"})
public class FightServlet extends HttpServlet {

    @EJB
    MonsterFacade monsterFacade;
    @EJB
    FriendshipFacade friendshipFacade;
    @EJB
    ServerFacade serverFacade;
    @EJB
    FightFacade requestFacade;
    @EJB
    FightRequestController fightController;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {

	try {

	    Fight fight = requestFacade.find(request.getParameter("id"));
	    request.setAttribute("fight", fight);


	    String action = request.getParameter("action");

	    if (action != null) {
		if (action.equals("fight")) {
		    fightController.fight(fight);
		} else if (action.equals("reject")) {
		    fightController.reject(fight);
		}
		response.sendRedirect("Monsters");

	    } else {

		/* Local Monster */
		LocalMonster localMonster = monsterFacade.find(fight.getLocalMonsterID());
		if (localMonster == null) {
		    requestFacade.remove(fight);
		    response.sendRedirect("Monsters");
		} else {
		request.setAttribute("localMonster", localMonster);

		/* Remote Monster */
		MonsterClient monsterClient = new MonsterClient(serverFacade.find(fight.getRemoteServerNumber()));
		RemoteMonster remoteMonster = monsterClient.getMonster(fight.getRemoteMonsterID());
		request.setAttribute("remoteMonster", remoteMonster);
		request.getRequestDispatcher("/WEB-INF/fight.jsp").forward(request, response);
		}
	    }


	} catch (Exception ex) {
	    request.setAttribute("error", ex);
	}

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {



	doGet(request, response);
    }
}
