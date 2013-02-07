package service;

import entity.Friendship;
import entity.Fight;
import entity.LocalMonster;
import facade.FriendshipFacade;
import facade.FightFacade;
import facade.MonsterFacade;
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
@WebServlet(name = "FightResponseService", urlPatterns = {"/service/fight/won", "/service/fight/lost", "/service/fight/reject"})
public class FightResponseService extends HttpServlet {

    @EJB
    FightFacade monsterRequestFacade;
    @EJB
    MonsterFacade monsterFacade;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {

	String fightID = request.getParameter("fightID");
	String monsterID = request.getParameter("monsterID");

	if (fightID == null) {
	    response.sendError(HttpServletResponse.SC_BAD_REQUEST,
		    "Fight ID not specified");
	} else {
	    List<Fight> rs = monsterRequestFacade.findByFightID(fightID);
	    if (rs.isEmpty()) {
		response.sendError(HttpServletResponse.SC_BAD_REQUEST,
			"Fight ID not found on this server");
	    } else {
		for (Fight r : rs) {
		    if (monsterID == null || r.getLocalMonsterID().equals(monsterID)) {
			if (request.getServletPath().equalsIgnoreCase("/service/fight/won")) {
			    r.setStatus(Fight.FightStatus.WON.ordinal());
			    monsterRequestFacade.edit(r);
			} else if (request.getServletPath().equalsIgnoreCase("/service/fight/lost")) {
			    r.setStatus(Fight.FightStatus.LOST.ordinal());
			    LocalMonster m = monsterFacade.find(r.getLocalMonsterID());
			    monsterFacade.remove(m);
			    monsterRequestFacade.edit(r);
			} else if (request.getServletPath().equalsIgnoreCase("/service/fight/reject")) {
			    monsterRequestFacade.remove(r);
			}
		    }
		}
	    }
	}

    }
}