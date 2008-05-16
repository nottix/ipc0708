package ipc.actions;

import ipc.control.*;
import ipc.forms.*;

import java.text.SimpleDateFormat;
import java.util.Hashtable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * @version 	1.0
 * @author
 */
public class CreazioneProgettoDoneAction extends Action

{

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {

    	ActionErrors errors = new ActionErrors();
        ActionForward forward = new ActionForward(); // return value
        GestioneEsameController cont = new GestioneEsameController();
        CreazioneProgettoForm cForm = (CreazioneProgettoForm)form;
        
        try {

        	Hashtable<String, Object> data = new Hashtable<String, Object>();
            data.put("acronimo", cForm.getAcronimo());
            data.put("titolo", cForm.getTitolo());
            data.put("dataConsegna", new SimpleDateFormat("MM/dd/yy").parse(cForm.getDataConsegna()));
            data.put("maxUploadPerStudente", cForm.getMaxUploadPerStudente());
        	data.put("maxDimGruppo", cForm.getMaxDimGruppo());
            if(cont.creazioneProgetto(data)) {
            	errors.add("name", new ActionError("progetto.created"));
            }
            else
            	errors.add("name", new ActionError("progetto.ncreated"));
            
            //data.put("dataEsame", new Date());
    		//data.put("aulaEsame", "Aula 1");
    		//data.put("idEsame", idEsame);

        } catch (Exception e) {

            // Report the error using the appropriate name and ID.
            errors.add("name", new ActionError("progetto.ncreated"));

        }

        // If a message is required, save the specified key(s)
        // into the request for use by the <struts:errors> tag.

        if (!errors.isEmpty()) {
            saveErrors(request, errors);

            // Forward control to the appropriate 'failure' URI (change name as desired)
            //	forward = mapping.findForward(non riuscito");

            forward = mapping.findForward("success");
        }

        // Finish with
        return (forward);

    }
}
