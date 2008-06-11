package ipc.db;

import ipc.entity.Corso;

import org.hibernate.Hibernate;
import org.hibernate.Query;
/**
 * @version 	1.0
 * @author		Laurenziello Vincenzo
 * @author 		Notargiacomo Simone
 * @author		Scenna Fabrizio
 */
public class CorsoDAOImpl extends GenericDAOImpl<Corso, Long> implements CorsoDAO {
	
	public Corso get(String acronimo) {
		begin();
        Query q = getSession().createQuery("from Corso a where a.acronimo = :acronimo");
        q.setParameter("acronimo", acronimo, Hibernate.STRING);
        Corso result = null;
        if(q.uniqueResult()!=null)
        	result = (Corso) q.uniqueResult();
		
		return result;
	}

}
