/**
 * 
 */
package ipc.control;

import java.util.Hashtable;

import ipc.db.SQLDAO;
import ipc.entity.Account;

/**
 * @author Simone Notargiacomo
 *
 */
public class LoginController {

	public Boolean login(String email, String password) {
		Account unAccount = null;
		SQLDAO sqlDAO = new SQLDAO();
		try {
			System.out.println("AAAAAAA: "+email);
			unAccount = sqlDAO.getAccount(email);
			System.out.println(unAccount==null?"nullo":"non nullo");
			System.out.println("email: "+unAccount.getEmail());
		} catch (Exception e) {}
		String passDB = unAccount.getPassword();
		String passEnc = unAccount.convertToMD5(password);
		if(passEnc.equals(passDB))
			return Boolean.TRUE;
		else
			return Boolean.FALSE;
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
}
