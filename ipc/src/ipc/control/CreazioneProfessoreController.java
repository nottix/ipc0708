/**
 * 
 */
package ipc.control;

import java.util.*;
import ipc.db.SQLDAO;

/**
 * @author Simone Notargiacomo
 *
 */
public class CreazioneProfessoreController {

	public Boolean creazioneProfessore(Hashtable<String, String> hash) {
		Boolean ret = false;
		try {
			SQLDAO sqldao = new SQLDAO();
			if(hash.get("email")!=null) {
				if(sqldao.getAccount(hash.get("email"))==null) {
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
