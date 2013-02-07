package facade;

import entity.Friendship;
import entity.Server;
import entity.User;
import facade.AbstractFacade;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author James Bowcott
 */
@Stateless
public class FriendshipFacade extends AbstractFacade<Friendship> {

    @PersistenceContext(unitName = "MMP1PU")
    private EntityManager em;
    @EJB
    UserFacade userFacade;

    @Override
    protected EntityManager getEntityManager() {
	return em;
    }

    public FriendshipFacade() {
	super(Friendship.class);
    }
    
    public List<Friendship> findByFriendID(String friendID) {
	Query q = em.createNamedQuery("Friendship.findByFriendID", Friendship.class);
	q.setParameter("friendID", friendID);
	return q.getResultList();
    }

//    public List<Friendship> findByLocalUserId(String localUserId) {
//	return em.createNamedQuery("Friendship.findByLocalUserId")
//		.setParameter("localUserId", localUserId).getResultList();
//    }
//
    public List<Friendship> findReceivedRequests(String localUserID) {
	Query q = em.createNamedQuery("Friendship.findReceivedRequests", Friendship.class);
	q.setParameter("localUserID", localUserID);
	return q.getResultList();
    }

    public List<Friendship> findSentRequests(String localUserID) {
	Query q = em.createNamedQuery("Friendship.findSentRequests", Friendship.class);
	q.setParameter("localUserID", localUserID);
	return q.getResultList();
    }

    public List<Friendship> findFriends(String localUserID) {
	Query q = em.createNamedQuery("Friendship.findFriends", Friendship.class);
	q.setParameter("localUserID", localUserID);
	return q.getResultList();
    }
    

    public List<Friendship> findReceivedRequests(User localUser) {
	return findReceivedRequests(localUser.getUserID());
    }

    public List<Friendship> findSentRequests(User localUser) {
	return findSentRequests(localUser.getUserID());
    }

    public List<Friendship> findFriends(User localUser) {
	return findFriends(localUser.getUserID());
    }

    public Friendship findFriendship(String localUserID, String remoteUserID, Server remoteServer) {
        Query q = em.createQuery("SELECT f FROM Friendship f WHERE f.localUserID = :localUserID AND f.remoteUserID = :remoteUserID AND f.remoteServerNumber = :remoteServerNumber");
        q.setParameter("localUserID", localUserID);
        q.setParameter("remoteUserID", remoteUserID);
        q.setParameter("remoteServerNumber", remoteServer.getServerNumber());
        try {
            return (Friendship) q.getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }
}
