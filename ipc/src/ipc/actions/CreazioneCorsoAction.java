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
        Hashtable<String, Object> data = null;
        HashSet<String> collaboratori = new HashSet<String>();
        HashSet<String> titolari = new HashSet<String>();
        CreazioneCorsoForm creazioneCorsoForm = (CreazioneCorsoForm)form;
        
        try {
        	data = new Hashtable<String, Object>();
        	int titolareCounter = 1;
        	int collaboratoreCounter = 1;
        	Enumeration en = request.getParameterNames();
        	while(en.hasMoreElements()) {
        		String name = (String)en.nextElement();
        		System.out.println("param: "+name);
        		if (request.getParameter(name) != null && request.getParameter(name).trim().equals("on")) {
        			System.out.println(name+" e' stato checkato");
        			//String value = request.getParameter(name);
        			System.out.println("titolare"+titolareCounter+": "+name.substring(name.indexOf("-")+1));
        			if(name.indexOf("titolare")>=0) {
        				System.out.println("ok");
        				//data.put("titolare"+titolareCounter, name.substring(name.indexOf("-")+1));
        				titolari.add(name.substring(name.indexOf("-")+1));
        				titolareCounter++;
        			}
        			if(name.indexOf("collaboratore")>=0) {
        				collaboratori.add(name.substring(name.indexOf("-")+1));
        				//data.put("collaboratore"+collaboratoreCounter, name.substring(name.indexOf("-")+1));
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
				if(!creazioneCorsoController.creazioneCorso(data))
					errors.add("name", new ActionError("generic.error"));
				else
					errors.add("name", new ActionError("corso.created"));
        	}
        	elencoProfessori = creazioneCorsoController.getElencoAccountProfessori();
        	System.out.println("elencosize: "+elencoProfessori.size());
        	request.setAttribute("elencoProfessori", elencoProfessori);

        } catch (Exception e) {

            // Report the error using the appropriate name and ID.
        	e.printStackTrace();
            errors.add("name", new ActionError("generic.error"));

        }

        // If a message is required, save the specified key(s)
        // into the request for use by the <struts:errors> tag.

        if (!errors.isEmpty()) {
            saveErrors(request, errors);

            // Forward control to the appropriate 'failure' URI (change name as desired)

        }
        forward = mapping.findForward("success");

        // Finish with
        return (forward);

    }
}
