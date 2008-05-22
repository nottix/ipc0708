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

	public void creazioneProfessore(Hashtable<String, Object> hash) throws Exception {
		SQLDAO sqldao = new SQLDAO();
		if(hash.get("email")!=null) {
			if(sqldao.getAccount((String)hash.get("email"))==null) {
				System.out.println("Utente non esistente");
				hash.put("status", "attivo");
				hash.put("tipologia", "professore");
				String pwd = (String)hash.get("password");
				hash.put("password", Account.convertToMD5(pwd));
				sqldao.createAndStoreAccount(hash);
			}
		}
		//Invio email informativa
	}
}
