/**
 * 
 */
package ipc.control;

import ipc.entity.Corso;
import ipc.db.SQLDAO;

import java.util.Hashtable;
import java.util.List;

/**
 * @author Simone Notargiacomo
 *
 */
public class GestioneCorsoController {

	private List<Corso> elencoCorsi = null;
	
	public List<Corso> getElencoCorsi() throws Exception {
		SQLDAO sqlDao = new SQLDAO();
		elencoCorsi = sqlDao.listCorso();
		return elencoCorsi;
	}
	
	public void creazioneEsame(Hashtable<String, Object> data) throws Exception {
		SQLDAO sqlDao = new SQLDAO();
		String acronimo = (String)data.get("acronimo");
		Long idCorso = sqlDao.getCorso(acronimo).getId();
		System.out.println("ID CORSO: "+idCorso);
		data.put("idCorso", idCorso);
		data.put("status", "attivo");
		sqlDao.createAndStoreEsame(data);
		//sqlDao.createAndStoreInfoEsame(data);
	}
}
