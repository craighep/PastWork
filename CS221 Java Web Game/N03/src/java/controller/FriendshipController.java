package controller;

import client.FriendshipClient;
import com.sun.jersey.api.client.ClientHandlerException;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.UniformInterfaceException;
import entity.Friendship;
import entity.Server;
import entity.User;
import facade.FriendshipFacade;
import facade.ServerFacade;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.Query;
import javax.ws.rs.core.Response;

/**
 *
 * @author James Bowcott
 */
@Stateless
public class FriendshipController {

    @EJB FriendshipFacade friendshipFacade;   
    @EJB ServerFacade serverFacade;

    public Friendship createRequest(String localUserID, String remoteUserID, Server remoteServer)
	    throws UniformInterfaceException, ClientHandlerException, Exception {
	
        // Check this friendship doesnt already exist
        if (friendshipFacade.findFriendship(localUserID, remoteUserID, remoteServer) != null) {
            throw new Exception("This friendship already exists");
        }
        
        // Check that were not sending a friendship to ourselves
        if (localUserID.equals(remoteUserID) && remoteServer.isLocalServer()) {
            throw new Exception("You cant send a friendship to yourself!");
        }
        
	// Create the Friendship object
	Friendship f = new Friendship(localUserID, remoteUserID, remoteServer, Friendship.StatusSent);
	
	// Instantiate the Friendship client
	FriendshipClient client = new FriendshipClient(remoteServer);
	
	// Send the request
	ClientResponse response = client.sendRequest(f);
	
	// Was it successfull?
	if (response.getStatus() == Response.Status.OK.getStatusCode()) {
	    friendshipFacade.create(f);
	    return f;
	} else {
	    throw new UniformInterfaceException(response);
	}
    }

    public void acceptRequest(Friendship f) throws Exception {
	FriendshipClient client = new FriendshipClient(serverFacade.find(f.getRemoteServerNumber()));
	ClientResponse response = client.acceptRequest(f);
	if (response.getStatus() == Response.Status.OK.getStatusCode()) {
	    f.setStatus(Friendship.StatusAccepted);
	    friendshipFacade.edit(f);
	} else {
	    throw new UniformInterfaceException(response);
	}
    }
    
    public void unfriend(Friendship f) throws UniformInterfaceException {
	FriendshipClient client = new FriendshipClient(serverFacade.find(f.getRemoteServerNumber()));
	ClientResponse response = client.rejectRequest(f);
	// If the request fails, remove the friendshipFacade entity anyway
	friendshipFacade.remove(f);
	if (response.getStatus() != Response.Status.OK.getStatusCode()) {
	    throw new UniformInterfaceException(response);
	}
    }

}