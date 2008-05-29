/**
 * 
 */
package ipc.control;

import ipc.entity.Account;
import ipc.entity.Corso;
import ipc.db.SQLDAO;

import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Simone Notargiacomo
 *
 */
public class GestioneCorsoController {

	private List<Corso> elencoCorsi = null;
	
private List<Account> elencoAccountProfessori = null;
	
	public List<Account> getElencoAccountProfessori() {
		try {
			SQLDAO sqlDao = new SQLDAO();
			List<Account> elenco = sqlDao.listAccount();
			elencoAccountProfessori = new LinkedList<Account>();
			for(int i=0; i<elenco.size(); i++) {
				if(elenco.get(i).getTipologia().equals("professore"))
					elencoAccountProfessori.add(elenco.get(i));
			}
			return elencoAccountProfessori;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Corso getCorso(String acronimo) {
		try {
			SQLDAO sqlDAO = new SQLDAO();
			return sqlDAO.getCorso(acronimo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<Corso> getElencoCorsi() {
		try {
			SQLDAO sqlDao = new SQLDAO();
			elencoCorsi = sqlDao.listCorso();
			return elencoCorsi;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Boolean creazioneEsame(Hashtable<String, Object> data) {
		try {
			SQLDAO sqlDao = new SQLDAO();
			String acronimo = (String)data.get("acronimo");
			Long idCorso = sqlDao.getCorso(acronimo).getId();
			System.out.println("ID CORSO: "+idCorso);
			data.put("idCorso", idCorso);
			data.put("status", "attivo");
			return sqlDao.createAndStoreEsame(data) != null;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public Boolean modificaCorso(String acronimo, Hashtable<String, Object> data) {
		try {
			SQLDAO sqlDAO = new SQLDAO();
			Long idCorso = sqlDAO.getCorso(acronimo).getId();
			data.put("idCorso", idCorso);
			return sqlDAO.updateCorso(idCorso, data);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
