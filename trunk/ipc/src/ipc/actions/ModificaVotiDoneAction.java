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
import ipc.forms.*;
import ipc.entity.*;
import java.util.*;

/**
 * @version 	1.0
 * @author
 */
public class ModificaVotiDoneAction extends Action

{

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        ActionErrors errors = new ActionErrors();
        ActionForward forward = new ActionForward(); // return value
        GestioneEsameController control = new GestioneEsameController();
        ModificaVotiForm cForm = (ModificaVotiForm)form;

        try {

            Hashtable<String, Object> data = new Hashtable<String, Object>();
            data.put("votoEsame", cForm.getVotoEsame());
            data.put("votoAccettato", cForm.getVotoAccettato().equals("on")?true:false);
            System.out.println("voto accettato: "+cForm.getVotoAccettato());
            if(cForm.getPresenzaEsame()!=null) {
            	data.put("presenzaEsame", cForm.getPresenzaEsame().equals("on")?true:false);
            	System.out.println("presenza: "+cForm.getPresenzaEsame());
            }
            if(cForm.getEsaminatore()!=null) {
            	data.put("esaminatore", cForm.getEsaminatore());
            }
            if(cForm.getNota()!=null)
            	data.put("nota", cForm.getNota());
            if(control.modificaVoto((Long)request.getSession().getAttribute("idPrenotazioneEsame"), data)) {
            	System.out.println("ok voto");
            }
            else {
            	errors.add("name", new ActionError("voto.nmodificato"));
            	System.out.println("non mod");
            }

        } catch (Exception e) {

            // Report the error using the appropriate name and ID.
            errors.add("name", new ActionError("id"));

        }

        // If a message is required, save the specified key(s)
        // into the request for use by the <struts:errors> tag.

        if (!errors.isEmpty()) {
            saveErrors(request, errors);

            // Forward control to the appropriate 'failure' URI (change name as desired)
            forward = mapping.findForward("error");

        } else {

            // Forward control to the appropriate 'success' URI (change name as desired)
            forward = mapping.findForward("success");
            request.getSession().removeAttribute("idPrenotazioneEsame");

        }

        // Finish with
        return (forward);

    }
}
