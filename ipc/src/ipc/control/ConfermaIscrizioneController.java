package ipc.control;

import ipc.db.CorsoDAO;
import ipc.db.DAOFactory;
import ipc.db.EsameDAO;
import ipc.db.IscrizioneCorsoDAO;
import ipc.db.PrenotazioneEsameDAO;
import ipc.entity.Corso;
import ipc.entity.Esame;
import ipc.entity.IscrizioneCorso;
import ipc.entity.PrenotazioneEsame;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * @version 	1.0
 * @author		Laurenziello Vincenzo
 * @author 		Notargiacomo Simone
 * @author		Scenna Fabrizio
 */
public class ConfermaIscrizioneController {

	private List<Corso> elencoCorsi;
	private List<Corso> elencoCorsiAccedibili;
	private List<IscrizioneCorso> elencoIC;
	private List<PrenotazioneEsame> elencoPE;
	private List<Esame> elencoEsami;
	//ritornare lista tutte le prenotazioni di un corso dato il suo acronimo
	
	public List<Esame> getEsamiCorso(String acronimo) {
		try {
			DAOFactory factory = DAOFactory.instance(DAOFactory.HIBERNATE);
			CorsoDAO corsoDao = factory.getCorsoDAO();
			EsameDAO esameDao = factory.getEsameDAO();
			Long idCorso = corsoDao.get(acronimo).getId();
			Iterator<Esame> tmp = esameDao.getElenco().iterator();
			elencoEsami = new LinkedList<Esame>();
			while(tmp.hasNext()) {
				Esame e = tmp.next();
				if(e.getIdCorso().equals(idCorso))
					elencoEsami.add(e);
			}
			return elencoEsami;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<PrenotazioneEsame> getPrenotazioniEsame(String acronimo) {
		try {
			DAOFactory factory = DAOFactory.instance(DAOFactory.HIBERNATE);
			PrenotazioneEsameDAO prenotazioneEsameDao = factory.getPrenotazioneEsameDAO();
			this.getEsamiCorso(acronimo);
			elencoPE = new LinkedList<PrenotazioneEsame>();
			Iterator<PrenotazioneEsame> tmp = prenotazioneEsameDao.getElenco().iterator();
			while(tmp.hasNext()) {
				PrenotazioneEsame pe = tmp.next();
				Iterator<Esame> e = this.elencoEsami.iterator();
				while(e.hasNext())
					if(pe.getIdEsame().equals(e.next().getId()))
						elencoPE.add(pe);
			}
			return elencoPE;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<IscrizioneCorso> getElencoIscrizioniCorso(String acronimo) {
		try {
			DAOFactory factory = DAOFactory.instance(DAOFactory.HIBERNATE);
			CorsoDAO corsoDao = factory.getCorsoDAO();
			IscrizioneCorsoDAO iscrizioneCorsoDao = factory.getIscrizioneCorsoDAO();
			Long idCorso = corsoDao.get(acronimo).getId();
			Iterator<IscrizioneCorso> iscrizioniCorso = iscrizioneCorsoDao.getElenco().iterator();
			elencoIC = new LinkedList<IscrizioneCorso>();
			while(iscrizioniCorso.hasNext()) {
				IscrizioneCorso tmp = iscrizioniCorso.next();
				if(tmp.getIdCorso().equals(idCorso) && tmp.getStatus().equals("pendent"))
					elencoIC.add(tmp);
			}
			return elencoIC;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<Corso> getElencoCorsiAccedibili(String email) {
		try {
			DAOFactory factory = DAOFactory.instance(DAOFactory.HIBERNATE);
			CorsoDAO corsoDao = factory.getCorsoDAO();
			elencoCorsi = corsoDao.getElenco();
			elencoCorsiAccedibili = new LinkedList<Corso>();
			Iterator<Corso> i = elencoCorsi.iterator();
			Corso corso;
			while(i.hasNext()) {
				corso = i.next();
				if(corso.isTitolare(email) || corso.isCollaboratore(email)) {
					elencoCorsiAccedibili.add(corso);
				}
			}
			return elencoCorsiAccedibili;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public IscrizioneCorso getIscrizioneCorso(Long id) {
		try {
			DAOFactory factory = DAOFactory.instance(DAOFactory.HIBERNATE);
			IscrizioneCorsoDAO iscrizioneCorsoDao = factory.getIscrizioneCorsoDAO();
			return iscrizioneCorsoDao.read(id);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public PrenotazioneEsame getPrenotazioneEsame(Long id) {
		try {
			DAOFactory factory = DAOFactory.instance(DAOFactory.HIBERNATE);
			PrenotazioneEsameDAO prenotazioneEsameDao = factory.getPrenotazioneEsameDAO();
			return prenotazioneEsameDao.read(id);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Boolean confermaIscrizioneCorso(Long id) {
		try {
			DAOFactory factory = DAOFactory.instance(DAOFactory.HIBERNATE);
			IscrizioneCorsoDAO iscrizioneCorsoDao = factory.getIscrizioneCorsoDAO();
			IscrizioneCorso iscrizioneCorso = iscrizioneCorsoDao.read(id);
			if(iscrizioneCorso == null)
				return false;
			iscrizioneCorso.setStatus("attivo");
			return iscrizioneCorsoDao.update(iscrizioneCorso);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public Boolean confermaPrenotazioneEsame(Long id) {
		try {
			DAOFactory factory = DAOFactory.instance(DAOFactory.HIBERNATE);
			PrenotazioneEsameDAO prenotazioneEsameDao = factory.getPrenotazioneEsameDAO();
			PrenotazioneEsame prenotazioneEsame = prenotazioneEsameDao.read(id);
			if(prenotazioneEsame == null)
				return false;
			prenotazioneEsame.setStatus("attivo");
			return prenotazioneEsameDao.update(prenotazioneEsame);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public Boolean rifiutaIscrizioneCorso(Long id) {
		try {
			DAOFactory factory = DAOFactory.instance(DAOFactory.HIBERNATE);
			IscrizioneCorsoDAO iscrizioneCorsoDao = factory.getIscrizioneCorsoDAO();
			IscrizioneCorso iscrizioneCorso = iscrizioneCorsoDao.read(id);
			if(iscrizioneCorso == null)
				return false;
			iscrizioneCorso.setStatus("disattivo");
			return iscrizioneCorsoDao.update(iscrizioneCorso);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public Boolean rifiutaPrenotazioneEsame(Long id) {
		try {
			DAOFactory factory = DAOFactory.instance(DAOFactory.HIBERNATE);
			PrenotazioneEsameDAO prenotazioneEsameDao = factory.getPrenotazioneEsameDAO();
			PrenotazioneEsame prenotazioneEsame = prenotazioneEsameDao.read(id);
			if(prenotazioneEsame == null)
				return false;
			prenotazioneEsame.setStatus("disattivo");
			return prenotazioneEsameDao.update(prenotazioneEsame);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
