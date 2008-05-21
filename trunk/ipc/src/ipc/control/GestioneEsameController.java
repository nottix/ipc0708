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
	
	public List<Corso> getElencoCorsi() {
		try {
			SQLDAO sqlDao = new SQLDAO();
			this.elencoCorsi = sqlDao.listCorso();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this.elencoCorsi;
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
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<PrenotazioneEsame> getPrenotatiEsame(Long idEsame) {
		try {
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
			getPrenotatiEsame(i.next().getId());
			elencoPrenotazioniEsamiCorso.addAll(elencoPrenotazioneEsame);
		}
		return elencoPrenotazioniEsamiCorso;
	}
	
	public Boolean modificaVoto(Long id, Hashtable<String, Object> data) {
		try {
			System.out.println("modifica voto");
			int i = 0;
			SQLDAO sqlDAO = new SQLDAO();
			//Iterator<PrenotazioneEsame> pe = sqlDAO.listPrenotazioneEsame().iterator();
			//while(pe.hasNext()) {
			//	PrenotazioneEsame ptmp = pe.next();
			PrenotazioneEsame ptmp = this.getPrenotazioneEsame(id);
				if(ptmp.getStatus().equals("attivo")) {
					i++;
				}
			if(i == 1) {
				data.put("id", id);
				return sqlDAO.updatePrenotazioneEsame(id, data);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	public Boolean creazioneProgetto(Hashtable<String, Object> data) {
		try {
			SQLDAO sqlDao = new SQLDAO();
			String acronimo = (String)data.get("acronimo");
			Long idCorso = sqlDao.getCorso(acronimo).getId();
			System.out.println("ID CORSO: "+idCorso);
			data.put("maxUploadPerStudente", Integer.valueOf((String)data.get("maxUploadPerStudente")));
			data.put("maxDimGruppo", Integer.valueOf((String)data.get("maxDimGruppo")));
			data.put("idCorso", idCorso);
			sqlDao.createAndStoreProgetto(data);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
