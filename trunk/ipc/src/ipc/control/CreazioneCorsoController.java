package ipc.control;

import ipc.entity.Corso;
import ipc.entity.Account;
import ipc.db.SQLDAO;

import java.util.List;
import java.util.LinkedList;
import java.util.Hashtable;
import java.util.Date;
import java.util.Iterator;
import java.util.HashSet;

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
	
	public Boolean creazioneCorso(Hashtable<String, Object> data) {
		try {
			/**
			 * First data checks
			 */
			String nome = (String) data.get("nome");
			String acronimo = (String) data.get("acronimo");
			Date dataApertura = (Date) data.get("dataApertura");
			Date dataChiusura = (Date) data.get("dataChiusura");
			Boolean ret=true;
			if(nome == null || nome.length() == 0)
				ret=false;
			else if(acronimo == null  || acronimo.length() == 0)
				ret=false;
			else if(dataApertura == null)
				ret=false;
			else if(dataChiusura == null)
				ret=false;
			else if(dataApertura.before(new Date()) || dataApertura.after(dataChiusura))
				ret=false;
			else {
				/**
				 * Second check: a course already exists!
				 */
				SQLDAO sqlDAO = new SQLDAO();
				Corso aCourse = sqlDAO.getCorso(acronimo);
				if(aCourse != null)
					return false;
				
				Iterator i;
				HashSet<Account> col = new HashSet<Account>();
				HashSet elenco = (HashSet)data.get("elencoCollaboratori");
				if(elenco!=null) {
					i = elenco.iterator();
					while(i.hasNext()) {
						String val = (String)i.next();
						System.out.println("list: "+val);
						col.add(sqlDAO.getAccount(val));
					}
					data.put("elencoCollaboratori", col);
				}
				
				elenco = (HashSet)data.get("elencoTitolari");
				if(elenco!=null) {
					col = new HashSet<Account>();
					if(elenco.size()>2)
						return false;
					i = elenco.iterator();
					while(i.hasNext()) {
						String val = (String)i.next();
						System.out.println("listTitolari: "+val);
						col.add(sqlDAO.getAccount(val));
					}
					data.put("elencoTitolari", col);
				}
				
				data.put("status", "attivo");
				
				ret = (sqlDAO.createAndStoreCorso(data) >= 0);
			}
			return ret;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}