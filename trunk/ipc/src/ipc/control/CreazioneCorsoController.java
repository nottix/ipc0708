package ipc.control;

import java.util.Date;
import java.util.List;
import java.util.Hashtable;

import ipc.entity.Corso;
import ipc.entity.Account;
import ipc.db.SQLDAO;

public class CreazioneCorsoController {
	
	public List<Account> getElencoAccountProfessore() {
		try {
			SQLDAO sqlDao = new SQLDAO();
			List<Account> elenco = sqlDao.listAccount();
			for(int i=0; i<elenco.size(); i++) {
				if(!elenco.get(i).getTipologia().equals("professore"))
					elenco.remove(elenco.get(i));
			}
			return elenco;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<Corso> getElencoCorsi() {
		try {
			SQLDAO sqlDao = new SQLDAO();
			return sqlDao.listCorso();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Boolean creazioneCorso(Hashtable data) throws Exception {
		/**
		 * First data checks
		 */
		String nome = (String) data.get("nome");
		if(nome == null || nome.length() == 0)
			return false;
		String acronimo = (String) data.get("acronimo");
		if(acronimo == null  || acronimo.length() == 0)
			return false;
		String titolare = (String) data.get("titolare1");
		if(titolare == null || titolare.length() == 0)
			return false;
		Date dataApertura = (Date) data.get("dataApertura");
		if(dataApertura == null)
			return false;
		Date dataChiusura = (Date) data.get("dataChiusura");
		if(dataChiusura == null)
			return false;
		/**
		 * Second check: a course already exists!
		 */
		SQLDAO sqlDAO = new SQLDAO();
		Corso aCourse = sqlDAO.getCorso(acronimo);
		if(aCourse != null)
			return false;
		sqlDAO.createAndStoreCorso(data);
		return true;
	}
}
