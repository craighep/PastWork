package facade;

import controller.ServerController;
import entity.Server;
import facade.AbstractFacade;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author James Bowcott
 */
@Stateless
public class ServerFacade extends AbstractFacade<Server> {

    @PersistenceContext(unitName = "MMP1PU")
    private EntityManager em;
    @EJB
    ServerController controller;

    @Override
    protected EntityManager getEntityManager() {
	return em;
    }

    public ServerFacade() {
	super(Server.class);
    }

    @Override
    public List<Server> findAll() {
	try {
	    controller.updateAllDirectoryServers();
	} catch (Exception ex) {
	    Logger.getLogger(ServerFacade.class.getName()).log(Level.SEVERE, null, ex);
	}
	
	CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
	CriteriaQuery q = cb.createQuery(Server.class);
	Root<Server> c = q.from(Server.class);
	q.select(c);
	q.orderBy(cb.asc(c.get("id")));
	return getEntityManager().createQuery(q).getResultList();
	
	//return super.findAll();
    }

    public Server get(Integer serverNumber, boolean update) {
	if (update) {
	try {
	    controller.updateDirectoryServer(serverNumber);
	} catch (Exception e) {
	    // TODO
	}
	}
	
	Server s = super.find(serverNumber);
	return s;
    }
}
