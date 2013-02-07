/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entity.Friendship;
import entity.Fight;
import entity.Fight.FightStatus;
import entity.LocalMonster;
import entity.User;
import facade.AbstractFacade;
import java.util.Collections;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author James Bowcott
 */
@Stateless
public class FightFacade extends AbstractFacade<Fight> {
    
    public FightFacade() {
	super(Fight.class);
    }
    
    @PersistenceContext(unitName = "MMP1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
	return em;
    }
        
    public List<Fight> findAllFightsForUser(String userID) {
	Query q = em.createNamedQuery("Fight.findAllFightsForUserID");
	q.setParameter("userID", userID);
	return q.getResultList();
    }
    
    public List<Fight> findFightsForUser(String userID, FightStatus status) {
	Query q = em.createNamedQuery("Fight.findFightsForUserID");
	q.setParameter("userID", userID);
	q.setParameter("status", status.ordinal());
	return q.getResultList();
    }
    
    public List<Fight> findCompletedFightsForUser(String userID) {
	Query q = em.createNamedQuery("Fight.findCompletedFightsForUserID");
	q.setParameter("userID", userID);
	return q.getResultList();
    }
    
    public List<Fight> findByFightID(String fightID) {
	Query q = em.createNamedQuery("Fight.findByFightID", Fight.class);
	q.setParameter("fightID", fightID);
	return q.getResultList();
    }
    
    public List<Fight> findByLocalMonster(LocalMonster monster) {
	Query q = em.createNamedQuery("Fight.findByLocalMonsterID", Fight.class);
	q.setParameter("monsterID", monster.getMonsterID());
	return q.getResultList();
    }
        
}
