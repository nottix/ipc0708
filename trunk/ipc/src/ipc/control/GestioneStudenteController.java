package ipc.control;

import ipc.db.CorsoDAO;
import ipc.db.DAOFactory;
import ipc.db.EsameDAO;
import ipc.db.IscrizioneCorsoDAO;
import ipc.db.PrenotazioneEsameDAO;
import ipc.db.ProgettoDAO;
import ipc.entity.Account;
import ipc.entity.Corso;
import ipc.entity.Esame;
import ipc.entity.IscrizioneCorso;
import ipc.entity.PrenotazioneEsame;
import ipc.entity.Progetto;

import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * @version 	1.0
 * @author		Laurenziello Vincenzo
 * @author 		Notargiacomo Simone
 * @author		Scenna Fabrizio
 */
public class GestioneStudenteController {
	
	private List<Corso> elencoCorsiDispAttivi;
	private List<Esame> elencoEsamiDispAttivi;
	private List<Progetto> elencoProgettiAttiviCorso;
	
	public List<Corso> getElencoCorsiDispAttivi() {
		try {
			DAOFactory factory = DAOFactory.instance(DAOFactory.HIBERNATE);
			CorsoDAO corsoDao = factory.getCorsoDAO();
			List<Corso> elencoCorsi = corsoDao.getElenco();
			this.elencoCorsiDispAttivi = new LinkedList<Corso>();
			Corso corso;

			Iterator<Corso> i = elencoCorsi.iterator();
			System.out.println("size: "+elencoCorsi.size());
			while(i.hasNext()) {
				corso = i.next();
				System.out.println("status: " + corso.getStatus());
				if( corso.getStatus()!=null && corso.getStatus().equals("attivo") && corso.isDisponibile()) {
					System.out.println("corso: " + corso.getAcronimo());
					this.elencoCorsiDispAttivi.add(corso);
				}
			}
			return this.elencoCorsiDispAttivi;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<Esame> getElencoEsamiDispAttivi() {
		try {
			DAOFactory factory = DAOFactory.instance(DAOFactory.HIBERNATE);
			EsameDAO esameDao = factory.getEsameDAO();
			List<Esame> elencoEsami = esameDao.getElenco();
			this.elencoEsamiDispAttivi = new LinkedList<Esame>();
			Esame esame;

			Iterator<Esame> i = elencoEsami.iterator();
			System.out.println("size: "+elencoEsami.size());
			while(i.hasNext()) {
				esame = i.next();
				System.out.println("esame: "+esame.getId());
				if( esame.getStatus()!=null && esame.getStatus().equals("attivo") && esame.isDisponibile()) {
					this.elencoEsamiDispAttivi.add(esame);
				}
			}
			return this.elencoEsamiDispAttivi;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<Esame> getElencoEsamiDispAttiviCorso(String acronimo) {
		try {
			DAOFactory factory = DAOFactory.instance(DAOFactory.HIBERNATE);
			EsameDAO esameDao = factory.getEsameDAO();
			CorsoDAO corsoDao = factory.getCorsoDAO();
			Corso c = corsoDao.get(acronimo);
			List<Esame> elencoEsami = esameDao.getElenco();
			this.elencoEsamiDispAttivi = new LinkedList<Esame>();
			Esame esame;

			Iterator<Esame> i = elencoEsami.iterator();
			System.out.println("size: "+elencoEsami.size());
			while(i.hasNext()) {
				esame = i.next();
				System.out.println("esame: "+esame.getId());
				if( esame.getStatus()!=null && esame.getStatus().equals("attivo")  && esame.getIdCorso().equals(c.getId()) && esame.isDisponibile()) {
					this.elencoEsamiDispAttivi.add(esame);
				}
			}
			return this.elencoEsamiDispAttivi;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<Progetto> getElencoProgettiDispAttiviCorso(String acronimo) {
		try {
			DAOFactory factory = DAOFactory.instance(DAOFactory.HIBERNATE);
			ProgettoDAO progettoDao = factory.getProgettoDAO();
			CorsoDAO corsoDao = factory.getCorsoDAO();
			Corso c = corsoDao.get(acronimo);
			List<Progetto> elencoProgetti = progettoDao.getElenco();
			this.elencoProgettiAttiviCorso = new LinkedList<Progetto>();
			Progetto project;
			Iterator<Progetto> i = elencoProgetti.iterator();
			while(i.hasNext()) {
				project = i.next();
				if( project.getStatus()!=null && project.getStatus().equals("attivo") &&
					project.getIdCorso().equals(c.getId())) {
					this.elencoProgettiAttiviCorso.add(project);
				}
			}
			return this.elencoProgettiAttiviCorso;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<Account> getElencoTitolariCorso(String acronimo) {
		try {
			
			DAOFactory factory = DAOFactory.instance(DAOFactory.HIBERNATE);
			CorsoDAO corsoDao = factory.getCorsoDAO();
			Corso c = corsoDao.get(acronimo);
			List<Account> elencoTitolari = new LinkedList<Account>();
			Iterator<Account> i = c.getElencoTitolari().iterator();
			while(i.hasNext()) {
				elencoTitolari.add(i.next());
			}
			return elencoTitolari;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<Account> getElencoCollaboratoriCorso(String acronimo) {
		try {
			DAOFactory factory = DAOFactory.instance(DAOFactory.HIBERNATE);
			CorsoDAO corsoDao = factory.getCorsoDAO();
			Corso c = corsoDao.get(acronimo);
			List<Account> elencoCollaboratori = new LinkedList<Account>();
			Iterator<Account> i = c.getElencoCollaboratori().iterator();
			while(i.hasNext()) {
				elencoCollaboratori.add(i.next());
			}
			return elencoCollaboratori;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Boolean iscrizioneCorso(String email, String acronimo) {
		try {
			DAOFactory factory = DAOFactory.instance(DAOFactory.HIBERNATE);
			IscrizioneCorsoDAO iscrizioneCorsoDao = factory.getIscrizioneCorsoDAO();
			CorsoDAO corsoDao = factory.getCorsoDAO();

			Corso corso = corsoDao.get(acronimo);
			IscrizioneCorso iscrizione = new IscrizioneCorso();
			iscrizione.setIdCorso(corso.getId());
			iscrizione.setIdStudente(email);
			iscrizione.setStatus("pendent");
			iscrizione.setDataIscrizione(new Date());
			iscrizione.setFlag("");
			return iscrizioneCorsoDao.create(iscrizione);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public Boolean prenotazioneEsame(String email, Long idEsame) {
		try {
			DAOFactory factory = DAOFactory.instance(DAOFactory.HIBERNATE);
			EsameDAO esameDao = factory.getEsameDAO();
			PrenotazioneEsameDAO prenotazioneEsameDao = factory.getPrenotazioneEsameDAO();
			
			Esame esame = esameDao.read(idEsame);
			PrenotazioneEsame prenotazione = new PrenotazioneEsame();
			prenotazione.setDataEsame(esame.getDataEsame());
			prenotazione.setDataPrenotazione(new Date());
			prenotazione.setIdEsame(idEsame);
			prenotazione.setIdStudente(email);
			prenotazione.setStatus("pendent");
			prenotazione.setPresenzaEsame(false);

			return prenotazioneEsameDao.create(prenotazione);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public Esame getEsame(Long id) {
		try {
			DAOFactory factory = DAOFactory.instance(DAOFactory.HIBERNATE);
			EsameDAO esameDao = factory.getEsameDAO();
			return esameDao.read(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
