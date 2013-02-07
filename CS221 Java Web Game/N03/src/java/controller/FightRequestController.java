package controller;

import client.FightClient;
import client.MonsterClient;
import com.sun.jersey.api.client.ClientHandlerException;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.UniformInterfaceException;
import entity.Fight;
import entity.Fight.FightStatus;
import entity.LocalMonster;
import entity.Monster;
import entity.RemoteMonster;
import entity.Server;
import facade.FightFacade;
import facade.MonsterFacade;
import facade.ServerFacade;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.core.Response;
import org.json.JSONException;

/**
 *
 * @author James Bowcott
 */
@Stateless
public class FightRequestController {
    
    @EJB
    FightFacade fightFacade;
    @EJB
    MonsterFacade monsterFacade;
    @EJB
    ServerFacade serverFacade;
    
    public Fight createRequest(LocalMonster localMonster, Server remoteServer, String remoteMonsterID, String remoteUserID)
	    throws UniformInterfaceException, ClientHandlerException {
	
	Fight f = new Fight(localMonster, remoteServer, remoteMonsterID, remoteUserID, FightStatus.SENT);
	FightClient client = new FightClient(remoteServer);
	ClientResponse response = client.request(f);
	
	if (response.getStatus() == Response.Status.OK.getStatusCode()) {
	    fightFacade.create(f);
	    return f;
	} else {
	    throw new UniformInterfaceException(response);
	}
    }
    
    public void fight(Fight f) throws Exception {
	try {
	    MonsterClient monsterClient = new MonsterClient(serverFacade.find(f.getRemoteServerNumber()));
	    RemoteMonster remoteMonster = monsterClient.getMonster(f.getRemoteMonsterID());
	    LocalMonster localMonster = monsterFacade.find(f.getLocalMonsterID());
	    
	    Monster dead = MonsterController.fight(localMonster, remoteMonster);
	    if (remoteMonster == dead) {
		won(f);
		monsterFacade.edit(localMonster);
	    } else {
		lost(f, remoteMonster);
		monsterFacade.remove(localMonster);
	    }
	    
	} catch (JSONException jsonEx) {
	    throw new Exception("Error getting remote monster information",jsonEx);
	} catch (UniformInterfaceException uiEx) {
	    throw new Exception("Remote server communication error",uiEx);
	}
    }
    
    public void lost(Fight f, RemoteMonster m) throws UniformInterfaceException, ClientHandlerException {
	FightClient client = new FightClient(serverFacade.find(f.getRemoteServerNumber()));
	ClientResponse response = client.won(f, m);
	f.setStatus(FightStatus.LOST);
	fightFacade.edit(f);
    }
    
    public void won(Fight f) throws UniformInterfaceException, ClientHandlerException {
	FightClient client = new FightClient(serverFacade.find(f.getRemoteServerNumber()));
	ClientResponse response = client.lost(f);
	f.setStatus(FightStatus.WON);
	fightFacade.edit(f);
	
    }
    
    public void reject(Fight f) throws UniformInterfaceException, ClientHandlerException {
	FightClient client = new FightClient(serverFacade.find(f.getRemoteServerNumber()));
	ClientResponse response = client.reject(f);
	fightFacade.remove(f);
    }
}
