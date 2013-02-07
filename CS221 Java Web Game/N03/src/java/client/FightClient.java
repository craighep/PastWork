package client;

import com.sun.jersey.api.client.ClientHandlerException;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.UniformInterfaceException;
import entity.Fight;
import entity.RemoteMonster;
import entity.Server;

/**
 *
 * @author James Bowcott
 */
public class FightClient extends AbstractClient {
    
    public FightClient(Server server) {
	super(server, "fight");
    }
    
    public ClientResponse request(Fight fight)
	    throws UniformInterfaceException, ClientHandlerException {
	return getWebResource()
		.path("request")
		.queryParam("fightID", fight.getFightID())
		.queryParam("localMonsterID", fight.getRemoteMonsterID())
		.queryParam("remoteMonsterID", fight.getLocalMonsterID())
		.queryParam("remoteServerNumber", String.valueOf(fight.getRemoteServerNumber()))
		.get(ClientResponse.class);
    }
      
    public ClientResponse won(Fight fight, RemoteMonster monster)
	    throws UniformInterfaceException, ClientHandlerException {
	return getWebResource()
		.path("won")
		.queryParam("fightID", fight.getFightID())
		.queryParam("monsterID", fight.getRemoteMonsterID())
		.queryParam("strength", String.valueOf(monster.getCurrentStrength()))
		.queryParam("defence", String.valueOf(monster.getCurrentDefence()))
		.queryParam("health", String.valueOf(monster.getCurrentHealth()))
		.get(ClientResponse.class);
    }
    
    public ClientResponse lost(Fight fight)
	    throws UniformInterfaceException, ClientHandlerException {
	return getWebResource()
		.path("lost")
		.queryParam("fightID", fight.getFightID())
		.queryParam("monsterID", fight.getRemoteMonsterID())
		.get(ClientResponse.class);
    }

    public ClientResponse reject(Fight fight)
	    throws UniformInterfaceException, ClientHandlerException {
	return getWebResource()
		.path("reject")
		.queryParam("fightID", fight.getFightID())
		.queryParam("monsterID", fight.getRemoteMonsterID())
		.get(ClientResponse.class);
    }
}
