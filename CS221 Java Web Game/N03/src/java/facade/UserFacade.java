package facade;

import entity.User;
import facade.AbstractFacade;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author James Bowcott
 */
@Stateless
public class UserFacade extends AbstractFacade<User> {

    @PersistenceContext(unitName = "MMP1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
	return em;
    }

    public UserFacade() {
	super(User.class);
    }

    public boolean userIdExists(String userId) {
	return findByUserId(userId) != null;
    }

    public User findByUserId(String userID) {
//	return super.find(userId);
	Query q = em.createNamedQuery("User.findByUserID");
	q.setParameter("userID", userID);
	try {
	    return (User) q.getSingleResult();
	} catch (Exception ex) {
	    return null;
	}
    }

    @Override
    public boolean validate(User u) {
	return (u.getUserID() != null && u.getUserID().length() > 2)
		&& (u.getName() != null && u.getName().length() > 4)
		&& (u.getPasswordHash() != null && u.getPasswordHash().length() > 30);
    }

}
