package ipc.control;

import ipc.db.SQLDAO;
import ipc.entity.Corso;
import ipc.entity.Esame;
import ipc.entity.PrenotazioneEsame;
import java.util.*;

public class GestioneEsameController {

	private List<Corso> elencoCorsi;
	private List<Corso> elencoCorsiAccessibili;
	private List<Esame> elencoEsami;
	private List<PrenotazioneEsame> elencoPrenotazioneEsame; 
	private List<PrenotazioneEsame> elencoPrenotazioniEsamiCorso;
	
	public List<Corso> getElencoCorsi() throws Exception {
		SQLDAO sqlDao = new SQLDAO();
		this.elencoCorsi = sqlDao.listCorso();
		return this.elencoCorsi;
	}
	
	public PrenotazioneEsame getPrenotazioneEsame(Long id) throws Exception {
		SQLDAO sqlDao = new SQLDAO();
		return sqlDao.getPrenotazioneEsame(id);
	}
	
	public List<Corso> getElencoCorsiAccessibili(String email) throws Exception {
		Iterator<Corso> eCorsi = this.getElencoCorsi().iterator();
		this.elencoCorsiAccessibili = new LinkedList<Corso>();
		while(eCorsi.hasNext()) {
			Corso c = eCorsi.next();
			if(c.isTitolare(email) || c.isCollaboratore(email))
				this.elencoCorsiAccessibili.add(c);
		}
		return this.elencoCorsiAccessibili;
	}
	
	public List<Esame> getElencoEsamiCorso(String acronimo) throws Exception {
		SQLDAO sqlDAO = new SQLDAO();
		Long idCorso = sqlDAO.getCorso(acronimo).getId();
		Iterator<Esame> tmp = sqlDAO.listEsame().iterator();
		this.elencoEsami = new LinkedList<Esame>();
		while(tmp.hasNext()) {
			Esame e = tmp.next();
			if(e.getIdCorso().equals(idCorso))
				this.elencoEsami.add(e);
		}
		return this.elencoEsami;
	}
	
	public List<PrenotazioneEsame> getPrenotatiEsame(Long idEsame) throws Exception {
		SQLDAO sqlDAO = new SQLDAO();
		Iterator<PrenotazioneEsame> pe = sqlDAO.listPrenotazioneEsame().iterator();
		this.elencoPrenotazioneEsame = new LinkedList<PrenotazioneEsame>();
		while(pe.hasNext()) {
			PrenotazioneEsame prenotazioneEsame = pe.next();
			if(prenotazioneEsame.getIdEsame().equals(idEsame)) {
				this.elencoPrenotazioneEsame.add(prenotazioneEsame);
			}
		}
		return this.elencoPrenotazioneEsame;
	}
	
	public List<PrenotazioneEsame> getElencoPrenotazioniEsamiCorso(String acronimo) throws Exception {
		getElencoEsamiCorso(acronimo);
		Iterator<Esame> i = elencoEsami.iterator();
		elencoPrenotazioniEsamiCorso = new LinkedList<PrenotazioneEsame>();
		while(i.hasNext()) {
			getPrenotatiEsame(i.next().getId());
			elencoPrenotazioniEsamiCorso.addAll(elencoPrenotazioneEsame);
		}
		return elencoPrenotazioniEsamiCorso;
	}
	
	public void modificaVoto(Long id, Hashtable<String, Object> data) throws Exception {
		System.out.println("modifica voto");
		SQLDAO sqlDAO = new SQLDAO();
		PrenotazioneEsame ptmp = this.getPrenotazioneEsame(id);
		if(ptmp.getStatus().equals("attivo")) {
			data.put("id", id);
		}

		sqlDAO.updatePrenotazioneEsame(id, data);
	}
		
	public void creazioneProgetto(Hashtable<String, Object> data) throws Exception {
		SQLDAO sqlDao = new SQLDAO();
		String acronimo = (String)data.get("acronimo");
		Long idCorso = sqlDao.getCorso(acronimo).getId();
		System.out.println("ID CORSO: "+idCorso);
		data.put("maxUploadPerStudente", Integer.valueOf((String)data.get("maxUploadPerStudente")));
		data.put("maxDimGruppo", Integer.valueOf((String)data.get("maxDimGruppo")));
		data.put("idCorso", idCorso);
		sqlDao.createAndStoreProgetto(data);
	}
}
