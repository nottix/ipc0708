/**
 * 
 */
package ipc.control;

import ipc.entity.*;
import java.util.*;
import ipc.db.*;

/**
 * @author Simone Notargiacomo
 *
 */
public class GestioneCorsoController {

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
	
	public Boolean creazioneEsame(Hashtable<String, Object> data) {
		try {
			SQLDAO sqlDao = new SQLDAO();
			String acronimo = (String)data.get("acronimo");
			Long idCorso = sqlDao.getCorso(acronimo).getId();
			System.out.println("ID CORSO: "+idCorso);
			data.put("idCorso", idCorso);
			data.put("status", "attivo");
			Long id = sqlDao.createAndStoreEsame(data);
			//sqlDao.createAndStoreInfoEsame(data);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}
}