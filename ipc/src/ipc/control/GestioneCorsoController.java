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
			Long id = sqlDao.createAndStoreEsame(data);
			data.put("idEsame", id);
			sqlDao.createAndStoreInfoEsame(data);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}
}
