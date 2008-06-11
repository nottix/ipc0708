package ipc.db;

import java.util.List;
import java.io.*;
/**
 * @version 	1.0
 * @author		Laurenziello Vincenzo
 * @author 		Notargiacomo Simone
 * @author		Scenna Fabrizio
 */
public interface GenericDAO<T, ID extends Serializable> {

	List<T> getElenco();
	
	T read(ID id);
	
	Boolean create(T entity);
	
	Boolean update(T entity);
	
	Boolean delete(T entity);
	
}
