package servlet;

import client.MonsterClient;
import controller.FightRequestController;
import controller.SessionController;
import entity.LocalMonster;
import entity.Friendship;
import entity.RemoteMonster;
import entity.Server;
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
@WebServlet(name = "MonsterServlet", urlPatterns = {"/Monster"})
public class MonsterServlet extends HttpServlet {

    @EJB
    MonsterFacade monsterFacade;
    @EJB
    FriendshipFacade friendshipFacade;
    @EJB
    ServerFacade serverFacade;
    @EJB
    FightFacade fightFacade;
    @EJB
    FightRequestController fightController;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	User user = SessionController.getSessionUser(request.getSession());

	/* Parameters*/
	String localMonsterID = request.getParameter("monsterID");
	String selectedFriendID = request.getParameter("friendID");
	String remoteMonsterID = request.getParameter("remoteMonsterID");

	/* Local Monster */
	if (localMonsterID != null) {
	    LocalMonster localMonster = monsterFacade.findByMonsterID(request.getParameter("monsterID"));
	    request.setAttribute("monster", localMonster);

	    /* Offers */
	    String saleOffer = request.getParameter("saleOffer");
	    String breedOffer = request.getParameter("breedOffer");
	    if (saleOffer != null && breedOffer != null) {
		localMonster.setSaleOffer(Integer.parseInt(saleOffer));
		localMonster.setBreedOffer(Integer.parseInt(breedOffer));
		monsterFacade.edit(localMonster);
	    }

	    try {

		/* Friends */
		List<Friendship> friends = friendshipFacade.findFriends(user.getUserID());
		request.setAttribute("friends", friends);

		if (selectedFriendID != null) {
		    Friendship selectedFriendship = friendshipFacade.find(selectedFriendID);
		    if (selectedFriendship != null) {

			/* Remote Monsters */
			Server remoteServer = serverFacade.find(selectedFriendship.getRemoteServerNumber());
			MonsterClient monsterClient = new MonsterClient(remoteServer);
			List<RemoteMonster> remoteMonsters = monsterClient.getMonsters(selectedFriendship.getRemoteUserID());
			request.setAttribute("remoteMonsters", remoteMonsters);

			if (remoteMonsterID != null) {
			    /* Create fight */
			    fightController.createRequest(localMonster,
				    remoteServer,
				    remoteMonsterID,
				    selectedFriendship.getRemoteUserID());
			    response.sendRedirect("Monsters");
			    return;
			} 

		    }

		}

	    } catch (Exception ex) {
		ex.printStackTrace();
		request.setAttribute("error", ex);
	    }

	    request.getRequestDispatcher("/WEB-INF/monster.jsp").forward(request, response);

	} else {
	    response.sendRedirect("Monsters");
	}

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {



	doGet(request, response);
    }
}
