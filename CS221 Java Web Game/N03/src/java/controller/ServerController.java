package controller;

import client.ServerClient;
import entity.Server;
import facade.ServerFacade;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author James Bowcott
 */
@Stateless
public class ServerController {
    @EJB
    ServerFacade facade;
    
    public Server getLocalServer() {
	return facade.get(3, true);
    }
    
    public Server updateDirectoryServer(Integer serverNumber) throws Exception {
	ServerClient sc;
	Server s, ds;
	
	try {
	    sc = new ServerClient();
	} catch (Exception ex) {
	    throw new Exception("Could not instantiate ServerClient", ex.getCause());
	}
	
	try {
	    ds = sc.getServer(serverNumber);
	} catch (Exception ex) {
	    throw new Exception("Communications error with Directory Server", ex.getCause());
	}
	
	s = facade.get(serverNumber, false);
	if (s == null) {
	    s = new Server(serverNumber, null);
	}
	
	if (ds.getServiceRootURL() != null) {
	    s.setServiceRootURL(ds.getServiceRootURL());
	}
	
	facade.edit(s);
	return s;
    }
    
    public void updateAllDirectoryServers() throws Exception {
	ServerClient sc;
	List<Server> dsl;
	Server s;
	
	try {
	    sc = new ServerClient();
	} catch (Exception ex) {
	    throw new Exception("Could not instantiate ServerClient", ex.getCause());
	}
	
	try {
	    dsl = sc.getServers();
	} catch (Exception ex) {
	    throw new Exception("Communications error with Directory Server", ex.getCause());
	}
	
	for (Server ds : dsl) {
	    s = facade.get(ds.getServerNumber(), false);
	    if (s == null) {
		s = new Server(ds.getServerNumber(), ds.getServiceRootURL());
	    } else {
		s.setServiceRootURL(ds.getServiceRootURL());
	    }
	    facade.edit(s);
	}
	
    }
    
}
