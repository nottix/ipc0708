package ipc.control;

import ipc.entity.Corso;
import ipc.entity.Account;
import ipc.db.SQLDAO;

import java.util.List;
import java.util.LinkedList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;

public class GestioneAccountController {

	private List<Account> elencoAccountStudentiPendent = null;
	private List<Account> elencoAccountStudenti = null;
	private List<Account> elencoAccount = null;
	
	public List<Account> getElencoAccountStudentiPendent() throws Exception {
		SQLDAO sqlDao = new SQLDAO();
		List<Account> elenco = sqlDao.listAccount();
		elencoAccountStudentiPendent = new LinkedList<Account>();
		for(int i=0; i<elenco.size(); i++) {
			if(elenco.get(i).getTipologia().equals("studente") && elenco.get(i).getStatus().equals("pendent")) {
				elencoAccountStudentiPendent.add(elenco.get(i));
			}
		}
		return elencoAccountStudentiPendent;
	}
	
	public List<Account> getElencoAccountStudenti() throws Exception {
		SQLDAO sqlDao = new SQLDAO();
		List<Account> elenco = sqlDao.listAccount();
		elencoAccountStudenti = new LinkedList<Account>();
		for(int i=0; i<elenco.size(); i++) {
			if(elenco.get(i).getTipologia().equals("studente")) {
				elencoAccountStudenti.add(elenco.get(i));
			}
		}
		return elencoAccountStudenti;
	}
	
	public List<Account> getElencoAccount() throws Exception {
		SQLDAO sqlDao = new SQLDAO();
		elencoAccount = sqlDao.listAccount();
		return elencoAccount;
	}
	
	public Account getAccountStudente(String email) throws Exception {
		SQLDAO sqlDao = new SQLDAO();
		return sqlDao.getAccount(email);
	}
	
	public Account getAccount(String email) throws Exception {
		SQLDAO sqlDao = new SQLDAO();
		return sqlDao.getAccount(email);
	}
	
	public List<Corso> getCorsiWhereIsTitolare(String email) throws Exception {
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
	}
	
	public List<Corso> getCorsiWhereIsCollaboratore(String email) throws Exception {
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
	}
	
	public void modificaAccountStudente(String email, Hashtable data) throws Exception {
		SQLDAO sqlDAO = new SQLDAO();
		sqlDAO.updateAccount(email, data);
	}
	
	public void abilitaAccountStudente(String email) throws Exception {
		Hashtable<String, String> data = new Hashtable<String, String>();
		data.put("status", "attivo");
		SQLDAO sqlDAO = new SQLDAO();
		sqlDAO.updateAccount(email, data);
	}
	
	public void ripristinaPasswordAccountStudente(String email) throws Exception {
		Hashtable<String, String> data = new Hashtable<String, String>();
		SQLDAO sqlDAO = new SQLDAO();
		String passwordEnc = Account.convertToMD5(Account.generatePassword());
		data.put("password", passwordEnc);
		System.out.println("email "+email+", pwd "+passwordEnc);
		sqlDAO.updateAccount(email, data);
	}
	
	public void creazioneAccountGestore(Hashtable<String, Object> data) throws Exception {
		SQLDAO sqlDao = new SQLDAO();
		if(sqlDao.getAccount((String)data.get("email"))==null) {
			data.put("isGestore", Boolean.TRUE);
			data.put("tipologia", "gestore");
			data.put("password", Account.convertToMD5((String)data.get("password")));
			data.put("status", "attivo");
			System.out.println("isGestore");
			sqlDao.createAndStoreAccount(data);
		}
	}
}
