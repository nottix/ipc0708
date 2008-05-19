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
	
	public List<Corso> getElencoCorsi() {
		try {
			SQLDAO sqlDao = new SQLDAO();
			this.elencoCorsi = sqlDao.listCorso();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this.elencoCorsi;
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
	
	public Boolean aggiornaVoto(String email, String voto) {
		try {
			int i = 0;
			Long idPrenotazioneEsame = -1L;
			SQLDAO sqlDAO = new SQLDAO();
			Iterator<PrenotazioneEsame> pe = sqlDAO.listPrenotazioneEsame().iterator();
			while(pe.hasNext()) {
				PrenotazioneEsame ptmp = pe.next();
				if(ptmp.getIdStudente().equals(email) && ptmp.getStatus().equals("attivo")) {
					idPrenotazioneEsame = ptmp.getId();
					i++;
				}
			}
			if(!idPrenotazioneEsame.equals(-1L) && i == 1) {
				Hashtable<String,String> data = new Hashtable<String, String>();
				data.put("idStudente", email);
				data.put("votoEsame", voto);
				return sqlDAO.updatePrenotazioneEsame(idPrenotazioneEsame, data);
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
