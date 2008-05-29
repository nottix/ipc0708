package ipc.db;

import ipc.entity.Corso;

import org.hibernate.Hibernate;
import org.hibernate.Query;

public class CorsoDAOImpl extends GenericDAOImpl<Corso, Long> implements CorsoDAO {
	
	public Corso get(String acronimo) {
		
        Query q = getSession().createQuery("from Corso a where a.acronimo = :acronimo");
        q.setParameter("acronimo", acronimo, Hibernate.STRING);
        Corso result = null;
        if(q.uniqueResult()!=null)
        	result = (Corso) q.uniqueResult();
		
		return result;
	}

}
