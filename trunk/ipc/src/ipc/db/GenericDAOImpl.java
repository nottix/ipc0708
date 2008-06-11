package ipc.db;

import org.hibernate.Session;

import java.io.Serializable;
import java.lang.reflect.*;
import java.util.List;
import org.hibernate.criterion.*;
import org.hibernate.Criteria;
/**
 * @version 	1.0
 * @author		Laurenziello Vincenzo
 * @author 		Notargiacomo Simone
 * @author		Scenna Fabrizio
 */
public abstract class GenericDAOImpl<T, ID extends Serializable> implements GenericDAO<T, ID> {

	private Class<T> persistentClass;
	private Session session;

	public GenericDAOImpl() {
		this.persistentClass = (Class<T>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
	}

	@SuppressWarnings("unchecked")
	public void setSession(Session s) {
		this.session = s;
	}

	protected Session getSession() {
		if (session == null)
			throw new IllegalStateException("Session has not been set on DAO before usage");
		return session;
	}

	public Class<T> getPersistentClass() {
		return persistentClass;
	}
	
	public List<T> getElenco() {
		begin();
		return find();
	}
	
	@SuppressWarnings("unchecked")
	public T read(ID id) {
		begin();
		T entity = (T) getSession().load(getPersistentClass(), id);
		return entity;
	}
	
	public Boolean create(T entity) {
		begin();
        getSession().save(entity);
        commit();
		return true;
	}
	
	public Boolean update(T entity) {
		begin();
		getSession().update(entity);
		commit();
		return true;
	}
	
	public Boolean delete(T entity) {
		begin();
		getSession().delete(entity);
		commit();
		return true;
	}
	
	public void begin() {
		getSession().getTransaction().begin();
	}
	
	public void commit() {
		getSession().getTransaction().commit();
	}
	
    public void flush() {
        getSession().flush();
    }

    public void clear() {
        getSession().clear();
    }
    
    @SuppressWarnings("unchecked")
    protected List<T> find(Criterion... criterion) {
        Criteria crit = getSession().createCriteria(getPersistentClass());
        for (Criterion c : criterion) {
            crit.add(c);
        }
        return crit.list();
   }
}
