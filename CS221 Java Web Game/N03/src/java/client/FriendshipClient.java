package client;

import com.sun.jersey.api.client.ClientResponse;
import controller.ServerController;
import entity.Friendship;
import entity.Server;
import javax.ejb.EJB;

/**
 * @author James Bowcott
 */
public class FriendshipClient extends AbstractClient {
    
    @EJB
    ServerController serverController;
    
    public FriendshipClient(Server server) {
	super(server,"friends");
    }

    public ClientResponse sendRequest(Friendship f) {
//	Integer thisServerNumber = serverController.getLocalServer().getServerNumber();
//	String thisServerID = String.valueOf(thisServerNumber);
	return getWebResource()
		.path("request")
		.queryParam("friendID", f.getFriendID())
		.queryParam("localUserID", f.getRemoteUserID())
		.queryParam("remoteUserID", f.getLocalUserID())
		.queryParam("remoteServerNumber", "3")
		.get(ClientResponse.class);
    }
    
    public ClientResponse acceptRequest(Friendship f) {
	return getWebResource()
		.path("accept")
		.queryParam("friendID", f.getFriendID())
		.get(ClientResponse.class);
    }
    
    public ClientResponse rejectRequest(Friendship f) {
	return getWebResource()
		.path("reject")
		.queryParam("friendID", f.getFriendID())
		.get(ClientResponse.class);
    }
}
