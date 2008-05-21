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

	public String login(String email, String password) {
		Account unAccount = null;
		SQLDAO sqlDAO = new SQLDAO();
		try {
			unAccount = sqlDAO.getAccount(email);
			System.out.println("email: "+unAccount.getEmail());
			String passDB = unAccount.getPassword();
			System.out.println("echeccazzo");
			String passEnc = Account.convertToMD5(password);
			System.out.println("passDB: "+passDB+", passEnc: "+passEnc+", password: "+password);
			if(passEnc.equals(passDB))
				return unAccount.getTipologia();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Boolean richiestaNuovaPasswordStudente(Hashtable data) throws Exception {
		/**
		 * First Stage:
		 * check if the data in Hashtable are inconsistent or not
		 */
		String tipologia = (String) data.get("tipologia");
		if(!tipologia.equals("studente"))
			return false;
		Boolean isDirettore = (Boolean) data.get("isDirettore");
		if(isDirettore!=null && isDirettore)
			return false;
		Boolean isTitolare = (Boolean) data.get("isTitolare");
		if(isTitolare!=null && isTitolare)
			return false;
		String status = (String) data.get("status");
		System.out.println("status: "+status);
		if(!status.equals("ripristino"))
			return false;
		/**
		 * Second stage:
		 * check if exists this student with associate email
		 */
		SQLDAO sqlDAO = new SQLDAO();
		Account test = null;
		test = sqlDAO.getAccount((String) data.get("email"));
		if (test == null)
			return false;
		System.out.println("L'account esiste!");
		return sqlDAO.updateAccount((String) data.get("email"), data);
	}
	
	@SuppressWarnings("unchecked")
	public Boolean richiestaRegStudente(Hashtable data) throws Exception {
		/**
		 * First Stage:
		 * check if the data in Hashtable are inconsistent or not
		 */
		String tipologia = (String) data.get("tipologia");
		if(!tipologia.equals("studente"))
			return false;
		Boolean isDirettore = (Boolean) data.get("isDirettore");
		if(isDirettore!=null && isDirettore)
			return false;
		Boolean isTitolare = (Boolean) data.get("isTitolare");
		if(isTitolare!=null && isTitolare)
			return false;
		String status = (String) data.get("status");
		if(!status.equals("pendent"))
			return false;
		/**
		 * Second stage:
		 * check if exists another student with same email
		 */
		Boolean ret = false;
		SQLDAO sqlDAO = new SQLDAO();
		System.out.println("Dopo sql");
		Account test = null;
		test = sqlDAO.getAccount((String) data.get("email"));
		if (test == null) {
			String tmp = (String) data.get("password");
			data.put("password", Account.convertToMD5(tmp));
			sqlDAO.createAndStoreAccount(data);
			ret = true;
		}
		return ret;
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
