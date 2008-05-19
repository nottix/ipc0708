package ipc.control;

import java.util.List;
import java.util.LinkedList;
import java.util.Hashtable;
import ipc.entity.Account;
import ipc.db.SQLDAO;
import java.util.*;
import ipc.entity.*;

public class GestioneAccountController {

	private List<Account> elencoAccountStudentiPendent = null;
	private List<Account> elencoAccountStudenti = null;
	private List<Account> elencoAccount = null;
	
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
			List<Account> elenco = sqlDao.listAccount();
			elencoAccountStudenti = new LinkedList<Account>();
			for(int i=0; i<elenco.size(); i++) {
				if(elenco.get(i).getTipologia().equals("studente")) {
					elencoAccountStudenti.add(elenco.get(i));
				}
			}
			return elencoAccountStudenti;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<Account> getElencoAccount() {
		try {
			SQLDAO sqlDao = new SQLDAO();
			elencoAccount = sqlDao.listAccount();
			return elencoAccount;
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
	
	public Account getAccount(String email) {
		try {
			SQLDAO sqlDao = new SQLDAO();
			return sqlDao.getAccount(email);
		}
		catch(Exception e) {}
		return null;
	}
	
	public List<Corso> getCorsiWhereIsTitolare(String email) {
		try {
			SQLDAO sqlDAO = new SQLDAO();
			List<Corso> elencoCorsi = sqlDAO.listCorso();
			List<Corso> elencoCorsiTitolare = new LinkedList<Corso>();
			Set elencoTitolari;
			
			Iterator<Corso> i = elencoCorsi.iterator();
			Corso corso;
			Account account;
			while(i.hasNext()) {
				corso = i.next();
				System.out.println("Corso: "+corso.getAcronimo());
				elencoTitolari = corso.getElencoTitolari();
				Iterator j = elencoTitolari.iterator();
				System.out.println("Corso: "+corso.getAcronimo()+", titolarisize: "+elencoTitolari.size());
				while(j.hasNext()) {
					account = (Account)j.next();
					System.out.println("Titolare: "+account.getEmail()+", tito: "+email);
					if(account.getEmail().equals(email))
						elencoCorsiTitolare.add(corso);
				}
			}
			
			return elencoCorsiTitolare;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<Corso> getCorsiWhereIsCollaboratore(String email) {
		try {
			SQLDAO sqlDAO = new SQLDAO();
			List<Corso> elencoCorsi = sqlDAO.listCorso();
			List<Corso> elencoCorsiCollaboratore = new LinkedList<Corso>();
			Set elencoCollaboratore;
			
			Iterator<Corso> i = elencoCorsi.iterator();
			Corso corso;
			Account account;
			while(i.hasNext()) {
				corso = i.next();
				System.out.println("Corso: "+corso.getAcronimo());
				elencoCollaboratore = corso.getElencoCollaboratori();
				Iterator j = elencoCollaboratore.iterator();
				//System.out.println("Corso: "+corso.getAcronimo()+", titolarisize: "+elencoTitolari.size());
				while(j.hasNext()) {
					account = (Account)j.next();
					//System.out.println("Titolare: "+account.getEmail()+", tito: "+email);
					if(account.getEmail().equals(email))
						elencoCorsiCollaboratore.add(corso);
				}
			}
			
			return elencoCorsiCollaboratore;
		} catch (Exception e) {
			e.printStackTrace();
		}
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
	
	public Boolean ripristinaPasswordAccountStudente(String email) {
		Hashtable<String, String> data = new Hashtable<String, String>();
		try {
			SQLDAO sqlDAO = new SQLDAO();
			String passwordEnc = Account.convertToMD5(Account.generatePassword());
			data.put("password", passwordEnc);
			System.out.println("email "+email+", pwd "+passwordEnc);
			return sqlDAO.updateAccount(email, data);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public Boolean creazioneAccountGestore(Hashtable data) {
		try {
			SQLDAO sqlDao = new SQLDAO();
			if(sqlDao.getAccount((String)data.get("email"))==null) {
				data.put("isGestore", Boolean.TRUE);
				data.put("tipologia", "gestore");
				data.put("password", Account.convertToMD5((String)data.get("password")));
				System.out.println("isGestore");
				return sqlDao.createAndStoreAccount(data)!=null;
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}