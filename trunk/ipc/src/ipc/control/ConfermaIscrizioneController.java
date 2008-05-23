package ipc.control;

import ipc.db.*;
import ipc.entity.*;
import java.util.*;

public class ConfermaIscrizioneController {

	private List<Corso> elencoCorsi;
	private List<Corso> elencoCorsiAccedibili;
	private List<IscrizioneCorso> elencoIC;
	private List<PrenotazioneEsame> elencoPE;
	private List<Esame> elencoEsami;
	//ritornare lista tutte le prenotazioni di un corso dato il suo acronimo
	
	public List<Esame> getEsamiCorso(String acronimo) throws Exception {
		SQLDAO sqlDAO = new SQLDAO();
		Long idCorso = sqlDAO.getCorso(acronimo).getId();
		Iterator<Esame> tmp = sqlDAO.listEsame().iterator();
		elencoEsami = new LinkedList<Esame>();
		while(tmp.hasNext()) {
			Esame e = tmp.next();
			if(e.getIdCorso().equals(idCorso))
				elencoEsami.add(e);
		}
		return elencoEsami;
	}
	
	public List<PrenotazioneEsame> getPrenotazioniEsame(String acronimo) throws Exception {
		SQLDAO sqlDAO = new SQLDAO();
		this.getEsamiCorso(acronimo);
		elencoPE = new LinkedList<PrenotazioneEsame>();
		Iterator<PrenotazioneEsame> tmp = sqlDAO.listPrenotazioneEsame().iterator();
		while(tmp.hasNext()) {
			PrenotazioneEsame pe = tmp.next();
			Iterator<Esame> e = this.elencoEsami.iterator();
			while(e.hasNext())
				if(pe.getIdEsame().equals(e.next().getId()))
					elencoPE.add(pe);
		}
		return elencoPE;
	}
	
	public List<IscrizioneCorso> getElencoIscrizioniCorso(String acronimo) throws Exception {
		SQLDAO sqlDAO = new SQLDAO();
		Long idCorso = sqlDAO.getCorso(acronimo).getId();
		Iterator<IscrizioneCorso> iscrizioniCorso = sqlDAO.listIscrizioneCorso().iterator();
		elencoIC = new LinkedList<IscrizioneCorso>();
		while(iscrizioniCorso.hasNext()) {
			IscrizioneCorso tmp = iscrizioniCorso.next();
			System.out.println("idCorso: "+idCorso+" anotherIdCorso: "+tmp.getIdCorso()+" iscr: "+tmp.getStatus());
			if(tmp.getIdCorso().equals(idCorso) && tmp.getStatus().equals("pendent"))
				elencoIC.add(tmp);
		}
		return elencoIC;
	}
	
	public List<Corso> getElencoCorsiAccedibili(String email) {
		try {
			SQLDAO sqlDao = new SQLDAO();
			elencoCorsi = sqlDao.listCorso();
			elencoCorsiAccedibili = new LinkedList<Corso>();
			Iterator<Corso> i = elencoCorsi.iterator();
			Corso corso;
			System.out.println("email: "+email);
			while(i.hasNext()) {
				corso = i.next();
				System.out.println("titolo: "+corso.getAcronimo());
				if(corso.isTitolare(email) || corso.isCollaboratore(email)) {
					System.out.println("ok2");
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
			SQLDAO sqlDao = new SQLDAO();
			return sqlDao.getIscrizioneCorso(id);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public PrenotazioneEsame getPrenotazioneEsame(Long id) {
		try {
			SQLDAO sqlDao = new SQLDAO();
			return sqlDao.getPrenotazioneEsame(id);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void confermaIscrizioneCorso(Long id) throws Exception {
		SQLDAO sqlDao = new SQLDAO();
		Hashtable<String, Object> data = new Hashtable<String, Object>();
		data.put("status", "attivo");
		sqlDao.updateIscrizioneCorso(id, data);
	}
	
	public void confermaPrenotazioneEsame(Long id) throws Exception {
		SQLDAO sqlDao = new SQLDAO();
		Hashtable<String, Object> data = new Hashtable<String, Object>();
		data.put("status", "attivo");
		sqlDao.updatePrenotazioneEsame(id, data);
	}
	
	public void rifiutaIscrizioneCorso(Long id) throws Exception {
		SQLDAO sqlDao = new SQLDAO();
		Hashtable<String, Object> data = new Hashtable<String, Object>();
		data.put("status", "disattivo");
		sqlDao.updateIscrizioneCorso(id, data);
	}
	
	public void rifiutaPrenotazioneEsame(Long id) throws Exception {
		SQLDAO sqlDao = new SQLDAO();
		Hashtable<String, Object> data = new Hashtable<String, Object>();
		data.put("status", "disattivo");
		sqlDao.updatePrenotazioneEsame(id, data);
	}
}