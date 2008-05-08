package ipc.control;
import java.util.Hashtable;
import ipc.db.SQLDAO;

public class GestioneStudenteController {
	public Boolean richiestaRegStudente(Hashtable data) throws Exception {
		Boolean ret = true;
		/*check if there is a fake*/
		String tipologia = (String) data.get("tipologia");
		if(!tipologia.equals("studente"))
			ret = false;
		Boolean isDirettore = (Boolean) data.get("isDirettore");
		if(isDirettore)
			ret = false;
		Boolean isTitolare = (Boolean) data.get("isTitolare");
		if(isTitolare)
			ret = false;
		String status = (String) data.get("status");
		if(!status.equals("pendent"))
			ret = false;
		SQLDAO sqlDAO = new SQLDAO();
		ret = sqlDAO.creazioneStudente(data);
		return ret;
	}
}
