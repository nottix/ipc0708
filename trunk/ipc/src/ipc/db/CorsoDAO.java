package ipc.db;

import ipc.entity.Corso;

/**
 * @version 	1.0
 * @author		Laurenziello Vincenzo
 * @author 		Notargiacomo Simone
 * @author		Scenna Fabrizio
 */
public interface CorsoDAO extends GenericDAO<Corso, Long> {
	
	Corso get(String acronimo);

}
