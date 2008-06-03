package ipc.control;

import ipc.db.CorsoDAO;
import ipc.db.DAOFactory;
import ipc.db.EsameDAO;
import ipc.db.PrenotazioneEsameDAO;
import ipc.db.ProgettoDAO;
import ipc.entity.Corso;
import ipc.entity.Esame;
import ipc.entity.PrenotazioneEsame;
import ipc.entity.Progetto;

import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Date;

/**
 * @version 	1.0
 * @author		Laurenziello Vincenzo
 * @author 		Notargiacomo Simone
 * @author		Scenna Fabrizio
 */
public class GestioneEsameController {

	private List<Corso> elencoCorsi;
	private List<Corso> elencoCorsiAccessibili;
	private List<Esame> elencoEsami;
	private List<PrenotazioneEsame> elencoPrenotazioneEsame; 
	private List<PrenotazioneEsame> elencoPrenotazioniEsamiCorso;
	
	public List<Corso> getElencoCorsi() {
		DAOFactory factory = DAOFactory.instance(DAOFactory.HIBERNATE);
		CorsoDAO corsoDao = factory.getCorsoDAO();
		this.elencoCorsi = corsoDao.getElenco();
		return this.elencoCorsi;
	}
	
	public PrenotazioneEsame getPrenotazioneEsame(Long id) {
		try {
			DAOFactory factory = DAOFactory.instance(DAOFactory.HIBERNATE);
			PrenotazioneEsameDAO prenotazioneEsameDao = factory.getPrenotazioneEsameDAO();
			return prenotazioneEsameDao.read(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<Corso> getElencoCorsiAccessibili(String email) {
		try {
			Iterator<Corso> eCorsi = this.getElencoCorsi().iterator();
			this.elencoCorsiAccessibili = new LinkedList<Corso>();
			while(eCorsi.hasNext()) {
				Corso c = eCorsi.next();
				if(c.isTitolare(email) || c.isCollaboratore(email))
					this.elencoCorsiAccessibili.add(c);
			}
			return this.elencoCorsiAccessibili;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<Esame> getElencoEsamiCorso(String acronimo) {
		try {
			DAOFactory factory = DAOFactory.instance(DAOFactory.HIBERNATE);
			CorsoDAO corsoDao = factory.getCorsoDAO();
			EsameDAO esameDao = factory.getEsameDAO();
			
			Long idCorso = corsoDao.get(acronimo).getId();
			Iterator<Esame> tmp = esameDao.getElenco().iterator();
			this.elencoEsami = new LinkedList<Esame>();
			while(tmp.hasNext()) {
				Esame e = tmp.next();
				if(e.getIdCorso().equals(idCorso))
					this.elencoEsami.add(e);
			}
			return this.elencoEsami;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<PrenotazioneEsame> getPrenotatiEsame(Long idEsame) {
		try {
			DAOFactory factory = DAOFactory.instance(DAOFactory.HIBERNATE);
			PrenotazioneEsameDAO prenotazioneEsameDao = factory.getPrenotazioneEsameDAO();
			
			Iterator<PrenotazioneEsame> pe = prenotazioneEsameDao.getElenco().iterator();
			this.elencoPrenotazioneEsame = new LinkedList<PrenotazioneEsame>();
			while(pe.hasNext()) {
				PrenotazioneEsame prenotazioneEsame = pe.next();
				if(prenotazioneEsame.getIdEsame().equals(idEsame)) {
					this.elencoPrenotazioneEsame.add(prenotazioneEsame);
				}
			}
			return this.elencoPrenotazioneEsame;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<PrenotazioneEsame> getPrenotatiEsameAttivi(Long idEsame) {
		try {
			DAOFactory factory = DAOFactory.instance(DAOFactory.HIBERNATE);
			PrenotazioneEsameDAO prenotazioneEsameDao = factory.getPrenotazioneEsameDAO();
			
			Iterator<PrenotazioneEsame> pe = prenotazioneEsameDao.getElenco().iterator();
			this.elencoPrenotazioneEsame = new LinkedList<PrenotazioneEsame>();
			while(pe.hasNext()) {
				PrenotazioneEsame prenotazioneEsame = pe.next();
				if(prenotazioneEsame.getIdEsame().equals(idEsame) && prenotazioneEsame.getStatus().equals("attivo")) {
					this.elencoPrenotazioneEsame.add(prenotazioneEsame);
				}
			}
			return this.elencoPrenotazioneEsame;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<PrenotazioneEsame> getElencoPrenotazioniEsamiCorso(String acronimo) {
		getElencoEsamiCorso(acronimo);
		Iterator<Esame> i = elencoEsami.iterator();
		elencoPrenotazioniEsamiCorso = new LinkedList<PrenotazioneEsame>();
		while(i.hasNext()) {
			getPrenotatiEsameAttivi(i.next().getId());
			elencoPrenotazioniEsamiCorso.addAll(elencoPrenotazioneEsame);
		}
		return elencoPrenotazioniEsamiCorso;
	}
	
	public Boolean modificaVoto(Long idPrenotazione, String voto, String esaminatore, 
			Boolean presenza, Boolean accettato, String nota) {
		try {
			DAOFactory factory = DAOFactory.instance(DAOFactory.HIBERNATE);
			PrenotazioneEsameDAO prenotazioneEsameDao = factory.getPrenotazioneEsameDAO();
			
			PrenotazioneEsame ptmp = this.getPrenotazioneEsame(idPrenotazione);
			if(ptmp.getStatus().equals("attivo")) {
				ptmp.setVotoEsame(voto);
				ptmp.setVotoAccettato(accettato);
				if(presenza != null)
					ptmp.setPresenzaEsame(presenza);
				if(esaminatore != null)
					ptmp.setEsaminatore(esaminatore);
				if(nota != null)
					ptmp.setNota(nota);
				return prenotazioneEsameDao.update(ptmp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
		
	public Boolean creazioneProgetto(String acronimo, String titolo, Integer maxUpload,
			Integer maxDim, String dataConsegna) {
		try {
			DAOFactory factory = DAOFactory.instance(DAOFactory.HIBERNATE);
			CorsoDAO corsoDao = factory.getCorsoDAO();
			ProgettoDAO progettoDao = factory.getProgettoDAO();
			Date commitDate = new SimpleDateFormat("MM/dd/yy").parse(dataConsegna);
			if(commitDate.after(new Date())) {
				Long idCorso = corsoDao.get(acronimo).getId();
				Progetto progetto = new Progetto();
				progetto.setIdCorso(idCorso);
				progetto.setDataConsegna(commitDate);
				progetto.setMaxDimGruppo(maxDim);
				progetto.setMaxUploadPerStudente(maxUpload);
				progetto.setTitolo(titolo);
				progetto.setStatus("attivo");
				return progettoDao.create(progetto);
			}
		} catch (NumberFormatException nfe) {
			nfe.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
