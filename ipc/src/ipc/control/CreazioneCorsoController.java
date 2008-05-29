package ipc.control;

import ipc.db.AccountDAO;
import ipc.db.CorsoDAO;
import ipc.db.DAOFactory;
import ipc.entity.Account;
import ipc.entity.Corso;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class CreazioneCorsoController {
	
	private List<Account> elencoAccountProfessori = null;
	
	public List<Account> getElencoAccountProfessori() {
		try {
			DAOFactory factory = DAOFactory.instance(DAOFactory.HIBERNATE);
			AccountDAO accountDao = factory.getAccountDAO();
			
			List<Account> elenco = accountDao.getElenco();
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
			DAOFactory factory = DAOFactory.instance(DAOFactory.HIBERNATE);
			CorsoDAO corsoDao = factory.getCorsoDAO();
			return corsoDao.getElenco();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Boolean creazioneCorso(String nome, String acronimo,
								  String dataApertura, String dataChiusura,
								  String comunicazioni, String descrizione,
								  Set elencoCollaboratori, Set elencoTitolari) {
		Date openDate = null;
		Date closeDate = null;
		try {
			DAOFactory factory = DAOFactory.instance(DAOFactory.HIBERNATE);
			CorsoDAO corsoDao = factory.getCorsoDAO();
			
			if(corsoDao.get(acronimo) == null) {
				if(dataApertura != null && dataChiusura != null) {
					openDate = new SimpleDateFormat("MM/dd/yy").parse(dataApertura);
					closeDate = new SimpleDateFormat("MM/dd/yy").parse(dataChiusura);
					if(openDate.before(new Date()) || openDate.after(closeDate)) {
						return false;
					}
				} else return false;
				Corso c = new Corso();
				c.setAcronimo(acronimo);
				c.setDataApertura(openDate);
				c.setDataChiusura(closeDate);
				c.setStatus("attivo");
				if(comunicazioni != null)
					c.setComunicazioni(comunicazioni);
				if(descrizione != null)
					c.setDescrizione(descrizione);
				/*TODO: Siamo sicuri che l'elenco titolari puo' essere nullo?*/
				if(elencoTitolari != null)
					c.setElencoTitolari(elencoTitolari);
				if(elencoCollaboratori != null)
					c.setElencoCollaboratori(elencoCollaboratori);
				return corsoDao.create(c);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
