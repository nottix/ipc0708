package ipc.control;

import ipc.entity.*;

import java.util.*;

import ipc.db.*;

public class GestioneStudenteController {
	
	private List<Corso> elencoCorsiDispAttivi;
	private List<Esame> elencoEsamiDispAttivi;
	
	public List<Corso> getElencoCorsiDispAttivi() throws Exception {
		SQLDAO sqlDao = new SQLDAO();
		List<Corso> elencoCorsi = sqlDao.listCorso();
		this.elencoCorsiDispAttivi = new LinkedList<Corso>();
		Corso corso;

		Iterator<Corso> i = elencoCorsi.iterator();
		System.out.println("size: "+elencoCorsi.size());
		while(i.hasNext()) {
			corso = i.next();
			System.out.println("corso: "+corso.getAcronimo());
			if( corso.getStatus()!=null && corso.getStatus().equals("attivo") && corso.isDisponibile()) {
				this.elencoCorsiDispAttivi.add(corso);
			}
		}

		return this.elencoCorsiDispAttivi;
	}
	
	public List<Esame> getElencoEsamiDispAttivi() throws Exception {
		SQLDAO sqlDao = new SQLDAO();
		List<Esame> elencoEsami = sqlDao.listEsame();
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
	}
	
	public void iscrizioneCorso(Hashtable<String, Object> data) throws Exception {
		SQLDAO sqlDao = new SQLDAO();
		String acronimo = (String)data.get("acronimo");
		data.put("idCorso", sqlDao.getCorso(acronimo).getId());
		data.put("status", "pendent");
		sqlDao.createAndStoreIscrizioneCorso(data);
	}
	
	public void prenotazioneEsame(Hashtable<String, Object> data) throws Exception {
		SQLDAO sqlDao = new SQLDAO();
		data.put("status", "pendent");

		Esame esame = sqlDao.getEsame((Long)data.get("idEsame"));
		data.put("dataEsame", esame.getDataEsame());
		data.put("dataPrenotazione", new Date());
		data.put("presenzaEsame", false);
		data.put("status", "pendent");

		sqlDao.createAndStorePrenotazioneEsame(data);
	}
	
	public Esame getEsame(Long id) throws Exception {
		SQLDAO sqlDao = new SQLDAO();
		return sqlDao.getEsame(id);
	}
}
