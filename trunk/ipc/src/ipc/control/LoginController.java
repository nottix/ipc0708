package ipc.control;

import ipc.db.AccountDAO;
import ipc.db.CorsoDAO;
import ipc.db.DAOFactory;
import ipc.entity.Account;
import ipc.entity.Corso;

import java.util.Iterator;

/**
 * @version 	1.0
 * @author		Laurenziello Vincenzo
 * @author 		Notargiacomo Simone
 * @author		Scenna Fabrizio
 */
public class LoginController {

	private String tipologia = null;
	
	public String getTipologia() {
		return this.tipologia;
	}
	
	public Boolean login(String email, String password) {
		try {
			Account account = null;
			DAOFactory factory = DAOFactory.instance(DAOFactory.HIBERNATE);
			AccountDAO accountDao = factory.getAccountDAO(); 
			account = accountDao.read(email);
			if(account==null)
				return false;
			if(account.login(email, password) && account.getStatus().equals("attivo")) {
				System.out.println("entrato");
				this.tipologia = account.getTipologia();
				return this.tipologia!=null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public Boolean richiestaNuovaPasswordStudente(String email) {
		try {
			DAOFactory factory = DAOFactory.instance(DAOFactory.HIBERNATE);
			AccountDAO accountDao = factory.getAccountDAO();
			Account account = null;
			account = accountDao.read(email);
			
			if (account == null ||
					!account.getTipologia().equals("studente") ||
					account.getIsDirettore() ||
					account.getIsGestore())
				return false;
			
			account.setStatus("ripristino");
			return accountDao.update(account);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	@SuppressWarnings("unchecked")
	public Boolean richiestaRegStudente(String nome, String cognome, String matricola,
			String email, String password) {
		try {
			DAOFactory factory = DAOFactory.instance(DAOFactory.HIBERNATE);
			AccountDAO accountDao = factory.getAccountDAO();
			Account account = null;
			account = accountDao.read(email);
			if (account == null) {
				account = new Account();
				account.setNome(nome);
				account.setCognome(cognome);
				account.setMatricola(matricola);
				account.setEmail(email);
				account.setPassword(Account.convertToMD5(password));
				account.setStatus("pendent");
				account.setTipologia("studente");
				accountDao.create(account);
				return accountDao.create(account);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public Boolean isDirettore(String email) {
		try {
			DAOFactory factory = DAOFactory.instance(DAOFactory.HIBERNATE);
			AccountDAO accountDao = factory.getAccountDAO();
			return accountDao.read(email).getIsDirettore();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public Boolean isGestore(String email) {
		try {
			DAOFactory factory = DAOFactory.instance(DAOFactory.HIBERNATE);
			AccountDAO accountDao = factory.getAccountDAO();
			return accountDao.read(email).getIsGestore();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public Boolean isTitolare(String email) {
		try {
			DAOFactory factory = DAOFactory.instance(DAOFactory.HIBERNATE);
			CorsoDAO corsoDao = factory.getCorsoDAO();
			Iterator<Corso> i = corsoDao.getElenco().iterator();
			while(i.hasNext()) {
				Corso c = i.next();
				if(c.isTitolare(email).equals(true)) 
					return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public Boolean isCollaboratore(String email) {
		try {
			DAOFactory factory = DAOFactory.instance(DAOFactory.HIBERNATE);
			CorsoDAO corsoDao = factory.getCorsoDAO();
			Iterator<Corso> i = corsoDao.getElenco().iterator();
			while(i.hasNext()) {
				Corso c = i.next();
				if(c.isCollaboratore(email).equals(true)) 
					return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
