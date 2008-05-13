package ipc.control;

import java.util.*;
import ipc.entity.*;
import ipc.db.*;

public class GestioneAccountController {

	private List<Account> elencoAccountStudenti = null;
	
	public List<Account> getElencoAccountStudenti() {
		try {
			SQLDAO sqlDao = new SQLDAO();
			elencoAccountStudenti = sqlDao.listAccount();
			for(int i=0; i<elencoAccountStudenti.size(); i++) {
				if(!elencoAccountStudenti.get(i).getTipologia().equals("studente") ||
						!elencoAccountStudenti.get(i).getStatus().equals("pendent"))
					elencoAccountStudenti.remove(i);
			}
			return elencoAccountStudenti;
		} catch (Exception e) {
			// TODO Blocco catch generato automaticamente
			e.printStackTrace();
		}
		return null;
	}
}
