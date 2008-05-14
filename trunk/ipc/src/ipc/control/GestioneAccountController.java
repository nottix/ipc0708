package ipc.control;

import java.util.List;
import java.util.LinkedList;
import java.util.Hashtable;
import ipc.entity.Account;
import ipc.db.SQLDAO;
import java.util.*;

public class GestioneAccountController {

	private List<Account> elencoAccountStudentiPendent = null;
	private List<Account> elencoAccountStudenti = null;
	
	public List<Account> getElencoAccountStudentiPendent() {
		try {
			SQLDAO sqlDao = new SQLDAO();
			List<Account> elenco = sqlDao.listAccount();
			elencoAccountStudentiPendent = new LinkedList<Account>();
			for(int i=0; i<elenco.size(); i++) {
				if(elenco.get(i).getTipologia().equals("studente") && elenco.get(i).getStatus().equals("pendent")) {
					elencoAccountStudentiPendent.add(elenco.get(i));
				}
			}
			return elencoAccountStudentiPendent;
		} catch (Exception e) {
			// TODO Blocco catch generato automaticamente
			e.printStackTrace();
		}
		return null;
	}
	
	public List<Account> getElencoAccountStudenti() {
		try {
			SQLDAO sqlDao = new SQLDAO();
			elencoAccountStudenti = sqlDao.listAccount();
			return elencoAccountStudenti;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Account getAccountStudente(String email) {
		try {
			SQLDAO sqlDao = new SQLDAO();
			return sqlDao.getAccount(email);
		}
		catch(Exception e) {}
		return null;
	}
	
	public Boolean modificaAccountStudente(String email, Hashtable data) {
		try {
			SQLDAO sqlDAO = new SQLDAO();
			return sqlDAO.updateAccount(email, data);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	public Boolean abilitaAccountStudente(String email) {
		Hashtable<String, String> data = new Hashtable<String, String>();
		data.put("status", "attivo");
		try {
			SQLDAO sqlDAO = new SQLDAO();
			return sqlDAO.updateAccount(email, data);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
