package entity;

import javax.persistence.*;

/**
 *
 * @author James Bowcott
 */
@Entity
@NamedQueries({
//    @NamedQuery(name = "Friendship.find",		    query = "SELECT f FROM Friendship f WHERE f.localUserId = :localUserId AND f.remoteUserId = :remoteUserId AND f.remoteServerId = :remoteServerId"),
//    @NamedQuery(name = "Friendship.findByUserIds",	    query = "SELECT f FROM Friendship f WHERE f.localUserId = :localUserId AND f.remoteUserId = :remoteUserId"),
//    @NamedQuery(name = "Friendship.findByLocalUserId",	    query = "SELECT f FROM Friendship f WHERE f.localUserId = :localUserId"),
    @NamedQuery(name = "Friendship.findFriends",	    query = "SELECT f FROM Friendship f WHERE f.localUserID = :localUserID AND f.status = " + Friendship.StatusAccepted),
    @NamedQuery(name = "Friendship.findReceivedRequests",   query = "SELECT f FROM Friendship f WHERE f.localUserID = :localUserID AND f.status = " + Friendship.StatusReceived),
    @NamedQuery(name = "Friendship.findSentRequests",	    query = "SELECT f FROM Friendship f WHERE f.localUserID = :localUserID AND f.status = " + Friendship.StatusSent),
    @NamedQuery(name = "Friendship.findByFriendID",	    query = "SELECT f FROM Friendship f WHERE f.friendID = :friendID")
})
public class Friendship extends AbstractEntity {
        
    public static final int StatusAccepted = 1;
    public static final int StatusSent = 2;
    public static final int StatusReceived = 3;
    
    /* Fields */
    
    private String friendID;
    private String localUserID;
    private String remoteUserID;
    private Integer remoteServerNumber;
    
    private Integer status;
    
        
    /* Constructors */
    
    public Friendship() {
	super();
	this.friendID = AbstractEntity.generateUID();
    }
    
    public Friendship(
	    String localUserID,
	    String remoteUserID,
	    Server remoteServer,
	    Integer status) {
	this();
	this.localUserID = localUserID;
	this.remoteUserID = remoteUserID;
	this.remoteServerNumber = remoteServer.getServerNumber();
	this.status = status;
    }
    
    /* Methods */

    public String getLocalUserID() {
	return localUserID;
    }
    
    public void setLocalUserID(String localUserID) {
	this.localUserID = localUserID;
    }

    public void setLocalUser(User localUser) {
	this.localUserID = localUser.getUserID();
    }

    public String getRemoteUserID() {
	return remoteUserID;
    }

    public void setRemoteUserID(String remoteUser) {
	this.remoteUserID = remoteUser;
    }

    public Integer getStatus() {
	return status;
    }

    public void setStatus(Integer status) {
	this.status = status;
    }

    public String getFriendID() {
	return this.friendID;
    }

    public void setFriendID(String friendID) {
	this.friendID = friendID;
    }


    public Integer getRemoteServerNumber() {
	return remoteServerNumber;
    }
    
    public void setRemoteServerNumber(Integer remoteServerNumber) {
	this.remoteServerNumber = remoteServerNumber;
    }

    public void setRemoteServer(Server remoteServer) {
	this.remoteServerNumber = remoteServer.getServerNumber();
    }
    
}
