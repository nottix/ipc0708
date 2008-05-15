package ipc.control;

import java.util.*;

import ipc.entity.Corso;
import ipc.entity.Account;
import ipc.db.SQLDAO;

public class CreazioneCorsoController {
	
	private List<Account> elencoAccountProfessori = null;
	
	public List<Account> getElencoAccountProfessori() {
		try {
			SQLDAO sqlDao = new SQLDAO();
			List<Account> elenco = sqlDao.listAccount();
			elencoAccountProfessori = new LinkedList<Account>();
			for(int i=0; i<elenco.size(); i++) {
				if(elenco.get(i).getTipologia().equals("professore"))
					elencoAccountProfessori.add(elenco.get(i));
			}
			return elencoAccountProfessori;
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
		System.out.println("titolare: "+titolare);
		if(titolare == null || titolare.length() == 0)
			return false;
		//titolare = (String) data.get("titolare2");
		//if(titolare == null || titolare.length() == 0)
		//	return false;
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
		
		HashSet col = new HashSet();
		HashSet elenco = (HashSet)data.get("elencoCollaboratori");
		Iterator i = elenco.iterator();
		while(i.hasNext()) {
			String val = (String)i.next();
			System.out.println("list: "+val);
			col.add(sqlDAO.getAccount(val));
		}
		data.put("elencoCollaboratori", col);
		sqlDAO.createAndStoreCorso(data);
		return true;
	}
}
