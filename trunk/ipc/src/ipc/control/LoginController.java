/**
 * 
 */
package ipc.control;

import java.util.Hashtable;
import java.util.Iterator;

import ipc.db.SQLDAO;
import ipc.entity.Account;
import ipc.entity.Corso;

/**
 * @author Simone Notargiacomo
 *
 */
public class LoginController {

	private String tipologia = null;
	
	public String getTipologia() {
		return this.tipologia;
	}
	
	public Boolean login(String email, String password) {
		try {
			Account unAccount = null;
			SQLDAO sqlDAO = new SQLDAO();
			unAccount = sqlDAO.getAccount(email);
			if(unAccount==null)
				throw new Exception("user.not.exists");
			String passDB = unAccount.getPassword();
			String passEnc = Account.convertToMD5(password);
			if(!passEnc.equals(passDB))
				throw new Exception("password.errata");
			if(!unAccount.getStatus().equals("attivo"))
				throw new Exception("account.disattivo");
			this.tipologia = unAccount.getTipologia();
			return unAccount.getTipologia() != null;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public Boolean richiestaNuovaPasswordStudente(Hashtable data) {
		try {
			/**
			 * First Stage:
			 * check if the data in Hashtable are inconsistent or not
			 */
			String tipologia = (String) data.get("tipologia");
			if(!tipologia.equals("studente"))
				throw new Exception("Tipologia non è Studenten");
			Boolean isDirettore = (Boolean) data.get("isDirettore");
			if(isDirettore!=null && isDirettore)
				throw new Exception("Non può essere Direttore");
//			Boolean isTitolare = (Boolean) data.get("isTitolare");
//			if(isTitolare!=null && isTitolare)
//				throw new Exception("Non può essere Titolare
			String status = (String) data.get("status");
			System.out.println("status: "+status);
			if(!status.equals("ripristino"))
				throw new Exception("Non richiesto ripristino");
			/**
			 * Second stage:
			 * check if exists this student with associate email
			 */
			SQLDAO sqlDAO = new SQLDAO();
			Account test = null;
			test = sqlDAO.getAccount((String) data.get("email"));
			if (test == null)
				throw new Exception("Account inesistente");
			return sqlDAO.updateAccount((String) data.get("email"), data);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	@SuppressWarnings("unchecked")
	public Boolean richiestaRegStudente(Hashtable data) {
		try {
			/**
			 * First Stage:
			 * check if the data in Hashtable are inconsistent or not
			 */
			String tipologia = (String) data.get("tipologia");
			if(!tipologia.equals("studente"))
				throw new Exception("Non è uno Studenten");
			Boolean isDirettore = (Boolean) data.get("isDirettore");
			if(isDirettore!=null && isDirettore)
				throw new Exception("Non può essere Direttore");
//			Boolean isTitolare = (Boolean) data.get("isTitolare");
//			if(isTitolare!=null && isTitolare)
//				return false;
			String status = (String) data.get("status");
			if(!status.equals("pendent"))
				throw new Exception("L'Account non è in attesa");
			/**
			 * Second stage:
			 * check if exists another student with same email
			 */
			SQLDAO sqlDAO = new SQLDAO();
			Account test = null;
			test = sqlDAO.getAccount((String) data.get("email"));
			if (test == null) {
				String tmp = (String) data.get("password");
				data.put("password", Account.convertToMD5(tmp));
				return sqlDAO.createAndStoreAccount(data) != null;
			}
			else
				throw new Exception("L'Account esiste");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public Boolean isDirettore(String email) {
		try {
			SQLDAO sqlDAO = new SQLDAO();
			return sqlDAO.getAccount(email).getIsDirettore();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public Boolean isGestore(String email) {
		try {
			SQLDAO sqlDAO = new SQLDAO();
			return sqlDAO.getAccount(email).getIsGestore();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public Boolean isTitolare(String email) {
		try {
			SQLDAO sqlDAO = new SQLDAO();
			Iterator<Corso> i = sqlDAO.listCorso().iterator();
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
			SQLDAO sqlDAO = new SQLDAO();
			Iterator<Corso> i = sqlDAO.listCorso().iterator();
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
