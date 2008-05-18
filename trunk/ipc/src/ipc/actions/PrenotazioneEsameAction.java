package ipc.actions;

import ipc.control.GestioneStudenteController;

import java.util.Enumeration;
import ipc.entity.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import ipc.forms.*;

/**
 * @version 	1.0
 * @author
 */
public class PrenotazioneEsameAction extends Action

{

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {

    	ActionErrors errors = new ActionErrors();
        ActionForward forward = new ActionForward(); // return value
        GestioneStudenteController control = new GestioneStudenteController();
        PrenotazioneEsameForm cForm = (PrenotazioneEsameForm)form;

        try {
        	if (isCancelled(request)) {
        		return mapping.findForward("main");
        	}

        	Enumeration en = request.getParameterNames();
        	while(en.hasMoreElements()) {
        		String name = (String)en.nextElement();
        		if(name.equals("radio")) {
        			System.out.println("request: "+request.getParameter(name));
        			HttpSession session = request.getSession();
        			session.setAttribute("idEsame", request.getParameter(name));
        			
        			Esame esame = control.getEsame(Long.valueOf(request.getParameter(name)));
        			cForm.setDataEsame(esame.getDataEsame().toString());
        			cForm.setAuleEsame(esame.getAuleEsame());
        			cForm.setDataInizioPeriodoPrenotazione(esame.getDataInizioPeriodoPrenotazione().toString());
        			cForm.setDataFinePeriodoPrenotazione(esame.getDataFinePeriodoPrenotazione().toString());
        		}
        	}
        }
        catch (Exception e) {

            // Report the error using the appropriate name and ID.
            errors.add("name", new ActionError("generic.error"));

        }

        // If a message is required, save the specified key(s)
        // into the request for use by the <struts:errors> tag.

        if (!errors.isEmpty()) {
            saveErrors(request, errors);

            // Forward control to the appropriate 'failure' URI (change name as desired)
            forward = mapping.findForward("error");

        }
        else {
        	forward = mapping.findForward("success");
        }

        // Finish with
        return (forward);

    }
}
