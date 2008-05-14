package ipc.control;

import java.util.List;
import java.util.LinkedList;
import java.util.Hashtable;
import ipc.entity.Account;
import ipc.db.SQLDAO;

public class GestioneAccountController {

	private List<Account> elencoAccountStudenti = null;
	
	public List<Account> getElencoAccountStudentiPendent() {
		try {
			SQLDAO sqlDao = new SQLDAO();
			List<Account> elenco = sqlDao.listAccount();
			elencoAccountStudenti = new LinkedList<Account>();
			for(int i=0; i<elenco.size(); i++) {
				if(elenco.get(i).getTipologia().equals("studente") && elenco.get(i).getStatus().equals("pendent")) {
					elencoAccountStudenti.add(elenco.get(i));
				}
			}
			return elencoAccountStudenti;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void abilitaAccountStudente(String email) {
		Hashtable<String, String> data = new Hashtable<String, String>();
		data.put("status", "attivo");
		try {
			SQLDAO sqlDAO = new SQLDAO();
			sqlDAO.updateAccount(email, data);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
