package ipc.control;
import java.util.Hashtable;
import ipc.db.SQLDAO;
import ipc.entity.Account;

public class GestioneStudenteController {
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
			sqlDAO.createAndStoreAccount(data);
			ret = true;
		}
		return ret;
	}
}
