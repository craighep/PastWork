package entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author James Bowcott
 */
@Entity
//@NamedQueries({
//    @NamedQuery(name = "Server.findByServerId", query = "SELECT s FROM Server s WHERE s.serverId = :serverId")
//})
public class Server extends AbstractEntity {
    
    /* Fields */
    
    /* The unique identifier of the server.
     * This is what the server has decided to be named, and should be unique among all other servers.
     * Required */

    
    /* The base URL of REST services of the server.
     * Requires a full URI description including the protocol.
     * For example "http://www.myserver.com:8080/myapp/webresources" */
    @Basic(optional = false)
    private String serviceRootURL;
    
//    @OneToMany(mappedBy="server")
//    private List<User> users;
        
    /* Constructors */
    
    public Server() {
	super();
    }
    
    public Server(Integer serverNumber, String serviceRootURL) {
	this();
	this.setId(serverNumber.longValue());
	this.serviceRootURL = serviceRootURL;
    }
    
    /* Accessors */

    public Integer getServerNumber() {
	return this.getId().intValue();
    }

    public void setServerNumber(Integer serverNumber) {
	this.setId(serverNumber.longValue());
    }
    
    public String getServerID() {
	return String.valueOf(this.getServerNumber());
    }

    public String getServiceRootURL() {
	return serviceRootURL;
    }

    public void setServiceRootURL(String serviceRootURL) {
	this.serviceRootURL = serviceRootURL;
    }
    
    public boolean isLocalServer() {
	return (this.getServerNumber() == 3);
    }

//    public List<User> getUsers() {
//	return users;
//    }
//    
//    public void addUser(User user) {
//	users.add(user);
//    }
}