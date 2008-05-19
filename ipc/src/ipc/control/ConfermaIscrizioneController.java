package ipc.control;

import ipc.db.*;
import ipc.entity.*;
import java.util.*;

public class ConfermaIscrizioneController {

	private List<Corso> elencoCorsi;
	private List<Corso> elencoCorsiAccedibili;
	
	public List<Corso> getElencoCorsiAccedibili(String email) {
		try {
			SQLDAO sqlDao = new SQLDAO();
			elencoCorsi = sqlDao.listCorso();
			elencoCorsiAccedibili = new LinkedList<Corso>();
			Iterator<Corso> i = elencoCorsi.iterator();
			Corso corso;
			System.out.println("email: "+email);
			while(i.hasNext()) {
				corso = i.next();
				System.out.println("titolo: "+corso.getAcronimo());
				if(corso.isTitolare(email) || corso.isCollaboratore(email)) {
					System.out.println("ok2");
					elencoCorsiAccedibili.add(corso);
				}
			}
			return elencoCorsiAccedibili;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Boolean confermaIscrizioneCorso(Long id) {
		try {
			SQLDAO sqlDao = new SQLDAO();
//			Corso corso = sqlDao.getCorso(acronimo);
//			Long idCorso = corso.getId();
//			Hashtable<String, Object> data = new Hashtable<String, Object>();
//			data.put("status", "attivo");
//			return sqlDao.updateIscrizioneCorso(idCorso, data);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}
}
