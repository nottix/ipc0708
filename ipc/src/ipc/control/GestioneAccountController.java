package ipc.control;

import ipc.db.AccountDAO;
import ipc.db.CorsoDAO;
import ipc.db.DAOFactory;
import ipc.entity.Account;
import ipc.entity.Corso;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class GestioneAccountController {

	private List<Account> elencoAccountStudentiPendent = null;
	private List<Account> elencoAccountStudenti = null;
	private List<Account> elencoAccount = null;
	
	public List<Account> getElencoAccountStudentiPendent() {
		try {
			List<Account> elenco = this.getElencoAccount();
			elencoAccountStudentiPendent = new LinkedList<Account>();
			for(int i=0; i<elenco.size(); i++) {
				if(elenco.get(i).getTipologia().equals("studente") && elenco.get(i).getStatus().equals("pendent")) {
					elencoAccountStudentiPendent.add(elenco.get(i));
				}
			}
			return elencoAccountStudentiPendent;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<Account> getElencoAccountStudenti() {
		try {
			List<Account> elenco = this.getElencoAccount();
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
			DAOFactory factory = DAOFactory.instance(DAOFactory.HIBERNATE);
			AccountDAO accountDao = factory.getAccountDAO();
			elencoAccount = accountDao.getElenco();
			return elencoAccount;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Account getAccount(String email) {
		try {
			DAOFactory factory = DAOFactory.instance(DAOFactory.HIBERNATE);
			AccountDAO accountDao = factory.getAccountDAO();
			return accountDao.read(email);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<Corso> getCorsiWhereIsTitolare(String email) {
		try {
			DAOFactory factory = DAOFactory.instance(DAOFactory.HIBERNATE);
			CorsoDAO corsoDao = factory.getCorsoDAO();
			List<Corso> elencoCorsi = corsoDao.getElenco();
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
			DAOFactory factory = DAOFactory.instance(DAOFactory.HIBERNATE);
			CorsoDAO corsoDao = factory.getCorsoDAO();
			List<Corso> elencoCorsi = corsoDao.getElenco();
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
	
	public Boolean modificaAccountStudente(String email, String nome, String cognome, 
			String matricola, String note) {
		try {
			DAOFactory factory = DAOFactory.instance(DAOFactory.HIBERNATE);
			AccountDAO accountDao = factory.getAccountDAO();
			
			Account account = accountDao.read(email);
			if(nome!=null)
				account.setNome(nome);
			if(cognome!=null)
				account.setCognome(cognome);
			if(matricola!=null)
				account.setMatricola(matricola);
			if(note!=null)
				account.setNoteStud(note);
			return accountDao.update(account);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public Boolean abilitaAccountStudente(String email) {
		try {
			DAOFactory factory = DAOFactory.instance(DAOFactory.HIBERNATE);
			AccountDAO accountDao = factory.getAccountDAO();
			
			Account account = accountDao.read(email);
			account.setStatus("attivo");
			return accountDao.update(account);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public Boolean ripristinaPasswordAccountStudente(String email) {
		try {
			DAOFactory factory = DAOFactory.instance(DAOFactory.HIBERNATE);
			AccountDAO accountDao = factory.getAccountDAO();
			
			Account account = accountDao.read(email);
			if(account!=null) {
				account.setPassword(Account.convertToMD5(Account.generatePassword()));
				return accountDao.update(account);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public Boolean creazioneAccountGestore(String email, String nome, String cognome, String password) {
		try {
			DAOFactory factory = DAOFactory.instance(DAOFactory.HIBERNATE);
			AccountDAO accountDao = factory.getAccountDAO();
			
			if(accountDao.read(email) == null) {
				Account account = new Account();
				account.setNome(nome);
				account.setCognome(cognome);
				account.setEmail(email);
				account.setPassword(Account.convertToMD5(password));
				account.setStatus("attivo");
				account.setTipologia("gestore");
				account.setIsGestore(true);
				return accountDao.create(account);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
