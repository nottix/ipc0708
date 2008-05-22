package ipc.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import ipc.control.*;
import ipc.entity.*;
import java.util.*;
import ipc.forms.*;
import java.text.*;

/**
 * @version 	1.0
 * @author
 */
public class CreazioneCorsoAction extends Action {

	private List<Account> elencoProfessori = null;
	
	public List<Account> getElencoProfessori() {
		return this.elencoProfessori;
	}
	
    public ActionForward execute(ActionMapping mapping, 
    							 ActionForm form, 
    							 HttpServletRequest request, 
    							 HttpServletResponse response)
            					throws Exception {

        ActionErrors errors = new ActionErrors();
        ActionForward forward = new ActionForward(); // return value
        CreazioneCorsoController creazioneCorsoController = new CreazioneCorsoController();
        Hashtable<String, Object> data = new Hashtable<String, Object>();
        HashSet<String> collaboratori = new HashSet<String>();
        HashSet<String> titolari = new HashSet<String>();
        CreazioneCorsoForm creazioneCorsoForm = (CreazioneCorsoForm)form;
        
        try {
        	int titolareCounter = 1;
        	int collaboratoreCounter = 1;
        	Enumeration en = request.getParameterNames();
        	while(en.hasMoreElements()) {
        		String name = (String)en.nextElement();
        		if (request.getParameter(name) != null && request.getParameter(name).trim().equals("on")) {
        			if(name.indexOf("titolare")>=0) {
        				titolari.add(name.substring(name.indexOf("-")+1));
        				titolareCounter++;
        			}
        			if(name.indexOf("collaboratore")>=0) {
        				collaboratori.add(name.substring(name.indexOf("-")+1));
        				collaboratoreCounter++;
        			}
        			
        		}
        	}
        	
        	if(titolareCounter>1) {
        		if(collaboratoreCounter>1)
        			data.put("elencoCollaboratori", collaboratori);
        		data.put("elencoTitolari", titolari);
				data.put("nome", creazioneCorsoForm.getNome());
				data.put("acronimo", creazioneCorsoForm.getAcronimo());
				data.put("descrizione", creazioneCorsoForm.getDescrizione());
				data.put("dataApertura", new SimpleDateFormat("MM/dd/yy").parse(creazioneCorsoForm.getDataApertura()));
				data.put("dataChiusura", new SimpleDateFormat("MM/dd/yy").parse(creazioneCorsoForm.getDataChiusura()));
				creazioneCorsoController.creazioneCorso(data);
        	}
        	elencoProfessori = creazioneCorsoController.getElencoAccountProfessori();
        	request.setAttribute("elencoProfessori", elencoProfessori);

        } catch (Exception e) {
            errors.add("name", new ActionError("generic.error"));
        }
        
        if (!errors.isEmpty()) {
            saveErrors(request, errors);

        }
        forward = mapping.findForward("success");

        // Finish with
        return (forward);

    }
}
