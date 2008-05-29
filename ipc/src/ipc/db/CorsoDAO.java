package ipc.db;

import ipc.entity.Corso;

public interface CorsoDAO extends GenericDAO<Corso, Long> {
	
	Corso get(String acronimo);

}
