package facade;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.ws.rs.core.Response;

/**
 *
 * @author NetBeans
 */
public abstract class AbstractFacade<T> {
    private Class<T> entityClass;

    public AbstractFacade(Class<T> entityClass) {
	this.entityClass = entityClass;
    }

    protected abstract EntityManager getEntityManager();

    public boolean create(T entity) {
	if (validate(entity)) {
	    getEntityManager().persist(entity);
	    return true;
	} else {
	    return false;
	}
    }

    public void edit(T entity) {
	getEntityManager().merge(entity);
    }

    public void remove(T entity) {
	getEntityManager().remove(getEntityManager().merge(entity));
    }

    public T find(Object id) {
	if (id instanceof String) {
	    return getEntityManager().find(entityClass, Long.valueOf((String)id));
	} else if (id instanceof Integer) {
	    return getEntityManager().find(entityClass, Long.valueOf((Integer)id));
	} else {
	    return getEntityManager().find(entityClass, id);
	}
    }

    public List<T> findAll() {
	CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
	cq.select(cq.from(entityClass));
	return getEntityManager().createQuery(cq).getResultList();
    }

    public List<T> findRange(int[] range) {
	CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
	cq.select(cq.from(entityClass));
	Query q = getEntityManager().createQuery(cq);
	q.setMaxResults(range[1] - range[0]);
	q.setFirstResult(range[0]);
	return q.getResultList();
    }

    public int count() {
	CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
	Root<T> rt = cq.from(entityClass);
	cq.select(getEntityManager().getCriteriaBuilder().count(rt));
	Query q = getEntityManager().createQuery(cq);
	return ((Long) q.getSingleResult()).intValue();
    }
    
    public boolean validate(T entity) {
	return true;
    }
    
    public static Response okResponse() {
	return Response.ok().build();
    }
    
    public static Response badResponse() {
	return Response.status(Response.Status.BAD_REQUEST).build();
    }
}
