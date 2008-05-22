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
import java.util.*;
import java.text.*;

/**
 * @version 	1.0
 * @author
 */
public class CreazioneEsameDoneAction extends Action

{

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        ActionErrors errors = new ActionErrors();
        ActionForward forward = new ActionForward(); // return value
        GestioneCorsoController cont = new GestioneCorsoController();
        CreazioneEsameForm cForm = (CreazioneEsameForm)form;
        
        try {

        	Hashtable<String, Object> data = new Hashtable<String, Object>();
            data.put("acronimo", cForm.getAcronimo());
            data.put("dataInizioPeriodoPrenotazione", new SimpleDateFormat("MM/dd/yy").parse(cForm.getDataInizio()));
            data.put("dataFinePeriodoPrenotazione", new SimpleDateFormat("MM/dd/yy").parse(cForm.getDataFine()));
            data.put("dataEsame", new SimpleDateFormat("MM/dd/yy").parse(cForm.getDataEsame()));
        	data.put("auleEsame", cForm.getAule());
        	System.out.println("aulaEsame: "+cForm.getAule());
            cont.creazioneEsame(data);

        } catch (Exception e) {

            // Report the error using the appropriate name and ID.
            errors.add("name", new ActionError("esame.ncreated"));

        }

        // If a message is required, save the specified key(s)
        // into the request for use by the <struts:errors> tag.

        if (!errors.isEmpty()) {
            saveErrors(request, errors);
            forward = mapping.findForward("error");
        }
        else {
        	request.getSession().removeAttribute("acronimo");
        	forward = mapping.findForward("success");
        }

        // Finish with
        return (forward);

    }
}
