package ipc.control;

import ipc.entity.Corso;
import ipc.entity.Esame;
import ipc.db.SQLDAO;

import java.util.Date;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;


public class GestioneStudenteController {
	
	private List<Corso> elencoCorsiDispAttivi;
	private List<Esame> elencoEsamiDispAttivi;
	
	public List<Corso> getElencoCorsiDispAttivi() {
		try {
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
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<Esame> getElencoEsamiDispAttivi() {
		try {
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
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Boolean iscrizioneCorso(Hashtable<String, Object> data) {
		try {
			SQLDAO sqlDao = new SQLDAO();
			String acronimo = (String)data.get("acronimo");
			data.put("idCorso", sqlDao.getCorso(acronimo).getId());
			data.put("status", "pendent");
			return sqlDao.createAndStoreIscrizioneCorso(data) != null;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public Boolean prenotazioneEsame(Hashtable<String, Object> data) {
		try {
			SQLDAO sqlDao = new SQLDAO();
			data.put("status", "pendent");

			Esame esame = sqlDao.getEsame((Long)data.get("idEsame"));
			data.put("dataEsame", esame.getDataEsame());
			data.put("dataPrenotazione", new Date());
			data.put("presenzaEsame", false);
			data.put("status", "pendent");

			return sqlDao.createAndStorePrenotazioneEsame(data) != null;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public Esame getEsame(Long id) {
		try {
			SQLDAO sqlDao = new SQLDAO();
			return sqlDao.getEsame(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
