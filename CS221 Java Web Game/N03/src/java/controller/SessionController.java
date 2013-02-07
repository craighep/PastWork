package controller;

import entity.User;
import facade.UserFacade;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.servlet.http.HttpSession;

/**
 *
 * @author James Bowcott
 */
@Stateless
public class SessionController {
    @EJB
    private UserFacade userFacade;
    
    private static final String SESSION_ATTRIBUTE_USER = "user";
    
    public boolean login(HttpSession session, String username, String password) {
	password = UserController.hashPassword(password);
	User user = userFacade.findByUserId(username);
	if (user != null) {
	    if (user.getPasswordHash().equals(password)) {
		session.setAttribute(SESSION_ATTRIBUTE_USER, user);
		return true;
	    }
	}
	return false;
    }
    
    public static boolean verifySessionUser(HttpSession session) {
	return (session != null && session.getAttribute(SESSION_ATTRIBUTE_USER) != null);
    }
    
    public static User getSessionUser(HttpSession session) {
	return (User) session.getAttribute(SESSION_ATTRIBUTE_USER);
    }
        
    public static void logoff(HttpSession session) {
	session.invalidate();
    }
    
    public void refreshUser(HttpSession session) {
	if (verifySessionUser(session) && userFacade != null) {
	    String userID = ((User) session.getAttribute(SESSION_ATTRIBUTE_USER)).getUserID();
	    User user = userFacade.findByUserId(userID);
	    session.setAttribute(SESSION_ATTRIBUTE_USER, user);
	}
    }
        
}
