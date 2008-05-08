package ipc.control;
import java.util.Hashtable;
import ipc.db.SQLDAO;

public class GestioneStudenteController {
	public Boolean richiestaRegStudente(Hashtable data) throws Exception {
		Boolean ret = true;
		System.out.println("1");
		String tipologia = (String) data.get("tipologia");
		if(!tipologia.equals("studente"))
			return false;
		System.out.println("2");
		Boolean isDirettore = (Boolean) data.get("isDirettore");
		if(isDirettore!=null && isDirettore)
			return false;
		System.out.println("3");
		Boolean isTitolare = (Boolean) data.get("isTitolare");
		if(isTitolare!=null && isTitolare)
			return false;
		String status = (String) data.get("status");
		if(!status.equals("pendent"))
			return false;
		System.out.println("Prima sql");
		SQLDAO sqlDAO = new SQLDAO();
		System.out.println("Dopo sql");
		ret = sqlDAO.creazioneStudente(data);
		return ret;
	}
}
