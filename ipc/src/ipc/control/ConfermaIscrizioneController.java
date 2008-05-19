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
	
	public List<Esame> getEsamiCorso(String acronimo) {
		try {
			SQLDAO sqlDAO = new SQLDAO();
			Long idCorso = sqlDAO.getCorso(acronimo).getId();
			Iterator<Esame> tmp = sqlDAO.listEsame().iterator();
			elencoEsami = new LinkedList<Esame>();
			while(tmp.hasNext()) {
				Esame e = tmp.next();
				if(e.getIdCorso() == idCorso)
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
			SQLDAO sqlDAO = new SQLDAO();
			this.getEsamiCorso(acronimo);
			Iterator<PrenotazioneEsame> tmp = sqlDAO.listPrenotazioneEsame().iterator();
			while(tmp.hasNext()) {
				PrenotazioneEsame pe = tmp.next();
				Iterator<Esame> e = this.elencoEsami.iterator();
				while(e.hasNext())
					if(pe.getIdEsame() == e.next().getId())
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
			SQLDAO sqlDAO = new SQLDAO();
			Long idCorso = sqlDAO.getCorso(acronimo).getId();
			Iterator<IscrizioneCorso> iscrizioniCorso = sqlDAO.listIscrizioneCorso().iterator();
			elencoIC = new LinkedList<IscrizioneCorso>();
			while(iscrizioniCorso.hasNext()) {
				IscrizioneCorso tmp = iscrizioniCorso.next();
				if(tmp.getIdCorso() == idCorso && tmp.getStatus().equals("pendent"))
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
	
	public Boolean confermaIscrizioneCorso(Long id) {
		try {
			SQLDAO sqlDao = new SQLDAO();
//			Corso corso = sqlDao.getCorso(acronimo);
//			Long idCorso = corso.getId();
//			Hashtable<String, Object> data = new Hashtable<String, Object>();
//			data.put("status", "attivo");
//			return sqlDao.updateIscrizioneCorso(idCorso, data);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}
}
