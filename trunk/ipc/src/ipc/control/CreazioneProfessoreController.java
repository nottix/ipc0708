/**
 * 
 */
package ipc.control;

import java.util.*;
import ipc.db.SQLDAO;
import ipc.entity.*;

/**
 * @author Simone Notargiacomo
 *
 */
public class CreazioneProfessoreController {

	public Boolean creazioneProfessore(Hashtable<String, Object> hash) {
		Boolean ret = false;
		try {
			SQLDAO sqldao = new SQLDAO();
			if(hash.get("email")!=null) {
				if(sqldao.getAccount((String)hash.get("email"))==null) {
					System.out.println("Utente non esistente");
					hash.put("status", "attivo");
					hash.put("tipologia", "professore");
					String pwd = (String)hash.get("password");
					hash.put("password", Account.convertToMD5(pwd));
					sqldao.createAndStoreAccount(hash);
					ret = true;
				}
			}
			//Invio email informativa
		}
		catch(Exception e) {
			ret = false;
		}
		return ret;
	}
}
