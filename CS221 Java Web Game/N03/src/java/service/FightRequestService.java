package service;

import client.MonsterClient;
import entity.LocalMonster;
import entity.Fight;
import entity.RemoteMonster;
import entity.Server;
import facade.MonsterFacade;
import facade.FightFacade;
import facade.ServerFacade;
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
@WebServlet(name = "FightRequestService", urlPatterns = {"/service/fight/request"})
public class FightRequestService extends HttpServlet {

    @EJB
    FightFacade monsterRequestFacade;
    @EJB
    MonsterFacade monsterFacade;
    @EJB
    ServerFacade serverFacade;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {

	Map params = request.getParameterMap();
	String[] requiredParams = new String[]{"fightID", "localMonsterID", "remoteMonsterID", "remoteServerNumber"};
	if (!params.keySet().containsAll(Arrays.asList(requiredParams))) {
	    response.sendError(
		    HttpServletResponse.SC_BAD_REQUEST,
		    "Parameter set incorrect");
	} else {

	    try {
		Long localMonsterID = Long.parseLong(request.getParameter("localMonsterID"));		
		LocalMonster monster = monsterFacade.find(localMonsterID);
		
		Integer remoteServerNumber = Integer.parseInt(request.getParameter("remoteServerNumber"));
		Server remoteServer = serverFacade.get(remoteServerNumber, true);

		MonsterClient monsterClient = new MonsterClient(remoteServer);
		RemoteMonster remoteMonster = monsterClient.getMonster(request.getParameter("remoteMonsterID"));

		if (monster == null) {
		    response.sendError(HttpServletResponse.SC_BAD_REQUEST,
			    "Local Monster ID does not exist");
		} else {
		    Fight r = new Fight();
		    r.setFightID(request.getParameter("fightID"));
		    r.setLocalMonsterID(monster.getMonsterID());
		    r.setLocalUserID(monster.getUserID());
		    r.setRemoteMonsterID(request.getParameter("remoteMonsterID"));
		    r.setRemoteUserID(remoteMonster.getUserID());
		    r.setRemoteServerNumber(remoteServer.getServerNumber());
		    r.setStatus(Fight.FightStatus.RECEIVED.ordinal());

		    monsterRequestFacade.create(r);
		}
		
	    } catch (Exception ex) {
		response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
			ex.toString());
	    }

	}

    }
}