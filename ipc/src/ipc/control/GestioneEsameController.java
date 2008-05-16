package ipc.control;

import ipc.db.SQLDAO;
import ipc.entity.Corso;
import java.util.*;

public class GestioneEsameController {
	private List<Corso> elencoCorsi = null;
	
	public List<Corso> getElencoCorsi() {
		try {
			SQLDAO sqlDao = new SQLDAO();
			elencoCorsi = sqlDao.listCorso();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return elencoCorsi;
	}
	
	public Boolean creazioneProgetto(Hashtable data) {
		try {
			SQLDAO sqlDao = new SQLDAO();
			String acronimo = (String)data.get("acronimo");
			Long idCorso = sqlDao.getCorso(acronimo).getId();
			System.out.println("ID CORSO: "+idCorso);
			data.put("maxUploadPerStudente", Integer.valueOf((String)data.get("maxUploadPerStudente")));
			data.put("maxDimGruppo", Integer.valueOf((String)data.get("maxDimGruppo")));
			data.put("idCorso", idCorso);
			sqlDao.createAndStoreProgetto(data);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}
}
