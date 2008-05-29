/**
 * 
 */
package ipc.control;

import ipc.db.AccountDAO;
import ipc.db.CorsoDAO;
import ipc.db.DAOFactory;
import ipc.db.IscrizioneCorsoDAO;
import ipc.entity.Account;
import ipc.entity.Corso;
import ipc.entity.IscrizioneCorso;
import ipc.utils.AccountCognomeComparator;
import ipc.utils.AccountEmailComparator;
import ipc.utils.AccountMatricolaComparator;
import ipc.utils.AccountNomeComparator;
import ipc.utils.MultiComparator;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Simone Notargiacomo
 *
 */
public class GestioneQueryController {
	
	private List<Corso> elencoCorsi = null;
	private List<Account> reportDefault = null;
	
	public List<Corso> getElencoCorsi() {
		try {
			DAOFactory factory = DAOFactory.instance(DAOFactory.HIBERNATE);
			CorsoDAO corsoDao = factory.getCorsoDAO();
			this.elencoCorsi = corsoDao.getElenco();
			return this.elencoCorsi;
		} catch (Exception e) {
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
			DAOFactory factory = DAOFactory.instance(DAOFactory.HIBERNATE);
			CorsoDAO corsoDao = factory.getCorsoDAO();
			IscrizioneCorsoDAO iscrizioneCorsoDao = factory.getIscrizioneCorsoDAO();
			AccountDAO accountDao = factory.getAccountDAO();
			
			reportDefault = new LinkedList<Account>();
			Corso corso = corsoDao.get(acronimo);
			if(corso!=null) {
				Corso cmpCorso;
				IscrizioneCorso cmpIscr;
				Iterator<IscrizioneCorso> i = iscrizioneCorsoDao.getElenco().iterator();
				while(i.hasNext()) {
					cmpIscr = i.next();
					cmpCorso = corsoDao.read(cmpIscr.getIdCorso());
					if(cmpCorso.getAcronimo().equals(acronimo))
						reportDefault.add(accountDao.read(cmpIscr.getIdStudente()));
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
