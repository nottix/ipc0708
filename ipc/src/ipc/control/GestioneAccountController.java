package ipc.control;

import java.util.*;
import ipc.entity.*;
import ipc.db.*;

public class GestioneAccountController {

	private List<Account> elencoAccountStudenti = null;
	
	public List<Account> getElencoAccountStudenti() {
		try {
			SQLDAO sqlDao = new SQLDAO();
			elencoAccountStudenti = sqlDao.listAccount();
			for(int i=0; i<elencoAccountStudenti.size(); i++) {
				if(!elencoAccountStudenti.get(i).getTipologia().equals("studente") ||
						!elencoAccountStudenti.get(i).getStatus().equals("pendent"))
					elencoAccountStudenti.remove(i);
			}
			return elencoAccountStudenti;
		} catch (Exception e) {
			// TODO Blocco catch generato automaticamente
			e.printStackTrace();
		}
		return null;
	}
	
	public void abilitaAccountStudente(String email) {
		Hashtable<String, String> data = new Hashtable<String, String>();
		data.put("status", "abilitato");
		try {
			SQLDAO sqlDAO = new SQLDAO();
			sqlDAO.updateAccount(email, data);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
