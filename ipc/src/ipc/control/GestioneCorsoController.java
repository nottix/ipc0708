package ipc.control;

import ipc.db.AccountDAO;
import ipc.db.CorsoDAO;
import ipc.db.DAOFactory;
import ipc.db.EsameDAO;
import ipc.entity.Account;
import ipc.entity.Corso;
import ipc.entity.Esame;

import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.Date;

/**
 * @version 	1.0
 * @author		Laurenziello Vincenzo
 * @author 		Notargiacomo Simone
 * @author		Scenna Fabrizio
 */
public class GestioneCorsoController {

	private List<Corso> elencoCorsi = null;
	
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
	
	public Corso getCorso(String acronimo) {
		try {
			DAOFactory factory = DAOFactory.instance(DAOFactory.HIBERNATE);
			CorsoDAO corsoDao = factory.getCorsoDAO();
			
			return corsoDao.get(acronimo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<Corso> getElencoCorsi() {
		try {
			DAOFactory factory = DAOFactory.instance(DAOFactory.HIBERNATE);
			CorsoDAO corsoDao = factory.getCorsoDAO();
			elencoCorsi = corsoDao.getElenco();
			return elencoCorsi;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Boolean creazioneEsame(String acronimo, String dataEsame, String inizioPeriodo,
			String finePeriodo, String aule) {
		try {
			DAOFactory factory = DAOFactory.instance(DAOFactory.HIBERNATE);
			CorsoDAO corsoDao = factory.getCorsoDAO();
			EsameDAO esameDao = factory.getEsameDAO();
			
			Long idCorso = corsoDao.get(acronimo).getId();
			System.out.println("ID CORSO: "+idCorso);

			Date examDate = new SimpleDateFormat("MM/dd/yy").parse(dataEsame);
			Date dataInizioPrenotazioni = new SimpleDateFormat("MM/dd/yy").parse(inizioPeriodo);
			Date dataFinePrenotazioni = new SimpleDateFormat("MM/dd/yy").parse(finePeriodo);
			if( dataInizioPrenotazioni.before(dataFinePrenotazioni) &&
					dataInizioPrenotazioni.after(new Date()) &&
					dataFinePrenotazioni.before(examDate)) {
				Esame esame = new Esame();
				esame.setAuleEsame(aule);

				esame.setDataEsame(examDate);
				esame.setDataInizioPeriodoPrenotazione(dataInizioPrenotazioni);
				esame.setDataFinePeriodoPrenotazione(dataFinePrenotazioni);
				esame.setIdCorso(idCorso);
				esame.setStatus("attivo");

				return esameDao.create(esame);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public Boolean modificaCorso(String acronimo, String nome, String descrizione,
			String comunicazioni, String dataApertura, String dataChiusura,
			Set elencoCollaboratori, Set elencoTitolari) {
		try {
			DAOFactory factory = DAOFactory.instance(DAOFactory.HIBERNATE);
			CorsoDAO corsoDao = factory.getCorsoDAO();
			
			Corso corso = corsoDao.get(acronimo);
			corso.setAcronimo(acronimo);
			if(nome!=null)
				corso.setNome(nome);
			if(descrizione!=null)
				corso.setDescrizione(descrizione);
			if(comunicazioni!=null)
				corso.setComunicazioni(comunicazioni);
			if(dataApertura!=null)
				corso.setDataApertura(new SimpleDateFormat("MM/dd/yy").parse(dataApertura));
			if(dataChiusura!=null)
				corso.setDataChiusura(new SimpleDateFormat("MM/dd/yy").parse(dataChiusura));
			if(elencoCollaboratori!=null)
				corso.setElencoCollaboratori(elencoCollaboratori);
			if(elencoTitolari!=null)
				corso.setElencoTitolari(elencoTitolari);
			
			return corsoDao.update(corso);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
