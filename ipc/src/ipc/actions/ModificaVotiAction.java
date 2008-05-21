package ipc.actions;

import java.util.Enumeration;

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

/**
 * @version 	1.0
 * @author
 */
public class ModificaVotiAction extends Action

{

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        ActionErrors errors = new ActionErrors();
        ActionForward forward = new ActionForward(); // return value
        GestioneEsameController control = new GestioneEsameController();

        try {

        	if (isCancelled(request)) {
        		return mapping.findForward("main");
        	}
        	Enumeration en = request.getParameterNames();
        	while(en.hasMoreElements()) {
        		String name = (String)en.nextElement();
        		if(name.equals("radio")) {
        			PrenotazioneEsame prenotazione = control.getPrenotazioneEsame(Long.valueOf(request.getParameter(name)));
        			ModificaVotiForm cForm = (ModificaVotiForm)form;
        			cForm.setIdStudente(prenotazione.getIdStudente());
        			cForm.setDataEsame(prenotazione.getDataEsame().toString());
        			cForm.setDataPrenotazione(prenotazione.getDataPrenotazione().toString());
        			if(prenotazione.getEsaminatore()!=null)
        				cForm.setEsaminatore(prenotazione.getEsaminatore());
        			if(prenotazione.getNota()!=null)
        				cForm.setNota(prenotazione.getNota());
        			if(prenotazione.getPresenzaEsame()!=null)
        				cForm.setPresenzaEsame(prenotazione.getPresenzaEsame()?"on":"off");
        			if(prenotazione.getVotoAccettato()!=null)
        				cForm.setVotoAccettato(prenotazione.getVotoAccettato()?"on":"off");
        			if(prenotazione.getVotoEsame()!=null)
        				cForm.setVotoEsame(prenotazione.getVotoEsame());
        				
        			request.getSession().setAttribute("idPrenotazioneEsame", Long.valueOf(request.getParameter(name)));
        		}
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
            //	forward = mapping.findForward(non riuscito");

        } else {

            // Forward control to the appropriate 'success' URI (change name as desired)
            forward = mapping.findForward("success");

        }

        // Finish with
        return (forward);

    }
}
