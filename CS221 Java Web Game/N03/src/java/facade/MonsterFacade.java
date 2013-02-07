package facade;

import entity.*;
import entity.Fight.FightStatus;
import facade.AbstractFacade;
import java.util.List;
import java.util.ListIterator;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class MonsterFacade extends AbstractFacade<LocalMonster> {
    
    @EJB FightFacade fightFacade;
    
    /* Attributes */
    @PersistenceContext(unitName = "MMP1PU")
    private EntityManager em;
    
    /* Constructors */
    
    public MonsterFacade() {
        super(LocalMonster.class);
    }

    @Override
    public LocalMonster find(Object id) {
	LocalMonster monster = null;
	if (id instanceof String) {
	    monster = findByMonsterID((String) id);//super.find(Long.valueOf((String) id));
	} else {
	    monster = super.find(id);
	}
	if (monster == null || monster.isDead()) {
	    //remove(monster);
	    return null;
	} else {
	    return monster;
	}
    }
    
    public LocalMonster findByMonsterID(String monsterID) {
	Query q = em.createNamedQuery("LocalMonster.findByMonsterID");
	q.setParameter("monsterID", monsterID);
	try {
	    return (LocalMonster) q.getSingleResult();
	} catch (NoResultException ex) {
	    return null;
	}
    }

    @Override
    public void remove(LocalMonster entity) {
	/* Remove any fight requests */
	List<Fight> fights = fightFacade.findByLocalMonster(entity);
	for (Fight fight : fights) {
	    if (fight.getStatus() == FightStatus.RECEIVED.ordinal()
		    || fight.getStatus() == FightStatus.SENT.ordinal()) {
		fightFacade.remove(fight);
	    }
	}
	super.remove(entity);
    }
    
    public List<LocalMonster> findByUser(String userID) {
	Query q = em.createNamedQuery("LocalMonster.findByUserID");
	q.setParameter("userID", userID);
	List<LocalMonster> monsters = q.getResultList();
	
	ListIterator<LocalMonster> iter = monsters.listIterator();
	while(iter.hasNext()) {
	    LocalMonster monster = iter.next();
	    if (monster.isDead()) {
		//remove(monster);
		iter.remove();
	    }
	}
	
	return monsters;
    }
        
//    public List<Monster> findByUserId(User user) {
//	return findByUserId(user.getUserID());
//    }
    
    
//  
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
