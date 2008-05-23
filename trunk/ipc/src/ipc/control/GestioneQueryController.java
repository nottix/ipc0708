/**
 * 
 */
package ipc.control;

import ipc.entity.*;

import java.util.*;

import ipc.utils.*;

import ipc.db.*;

/**
 * @author Simone Notargiacomo
 *
 */
public class GestioneQueryController {
	
	private List<Corso> elencoCorsi = null;
	private List<Account> reportDefault = null;
	
	public List<Corso> getElencoCorsi() {
		try {
			SQLDAO sqlDao = new SQLDAO();
			this.elencoCorsi = sqlDao.listCorso();
			return this.elencoCorsi;
		} catch (Exception e) {
			// TODO Blocco catch generato automaticamente
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Report di tutti gli Studenti iscritti ad un Corso
	 * @param acronimo
	 * @return Studenti iscritti al Corso
	 */
	public List<Account> queryDefault(String acronimo) {
		try {
			SQLDAO sqlDao = new SQLDAO();
			reportDefault = new LinkedList<Account>();
			Corso corso = sqlDao.getCorso(acronimo);
			if(corso!=null) {
				Corso cmpCorso;
				IscrizioneCorso cmpIscr;
				Iterator<IscrizioneCorso> i = sqlDao.listIscrizioneCorso().iterator();
				while(i.hasNext()) {
					cmpIscr = i.next();
					cmpCorso = sqlDao.getCorso(cmpIscr.getIdCorso());
					if(cmpCorso.getAcronimo().equals(acronimo))
						reportDefault.add(sqlDao.getAccount(cmpIscr.getIdStudente()));
				}
			}
			return reportDefault;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public List<Account> ordinamentoReport(List<String> columns, List<Account> report) {
		String column;
		MultiComparator multi = new MultiComparator();
		Iterator<String> i = columns.iterator();
		while(i.hasNext()) {
			column = i.next();
			if(column.equals("nome")) {
				System.out.println("col: "+column);
				multi.addComparator(new AccountNomeComparator());
			}
			else if(column.equals("cognome")) {
				System.out.println("col: "+column);
				multi.addComparator(new AccountCognomeComparator());
			}
			else if(column.equals("email")) {
				System.out.println("col: "+column);
				multi.addComparator(new AccountEmailComparator());
			}
			else if(column.equals("matricola")) {
				System.out.println("col: "+column);
				multi.addComparator(new AccountMatricolaComparator());
			}
		}
		System.out.println("report size: "+report.size());
		Collections.sort(report, multi);
		System.out.println("report size: "+report.size());
		
		return report;
	}

}
