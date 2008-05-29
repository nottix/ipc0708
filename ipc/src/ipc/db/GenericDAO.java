package ipc.db;

import java.util.List;
import java.io.*;

public interface GenericDAO<T, ID extends Serializable> {

	List<T> getElenco();
	
	T read(ID id);
	
	Boolean create(T entity);
	
	Boolean update(T entity);
	
	Boolean delete(T entity);
	
}
