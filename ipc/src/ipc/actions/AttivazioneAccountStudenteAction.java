package ipc.actions;

import ipc.entity.Account;
import ipc.control.GestioneAccountController;

import java.util.List;
import java.util.Enumeration;

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
public class AttivazioneAccountStudenteAction extends Action

{

	private List<Account> elencoAccountStudenti;
	
	public List<Account> getElencoAccountStudenti() {
		return this.elencoAccountStudenti;
	}
	
    public ActionForward execute(ActionMapping mapping, 
    							 ActionForm form, 
    							 HttpServletRequest request, 
    							 HttpServletResponse response)
            					throws Exception {
        ActionErrors errors = new ActionErrors();
        ActionForward forward = new ActionForward();
        GestioneAccountController gestioneAccountController = new GestioneAccountController();
        try {
        	Enumeration en = request.getParameterNames();
        	while(en.hasMoreElements()) {
        		String name = (String)en.nextElement();
        		if (request.getParameter(name) != null && request.getParameter(name).trim().equals("on")) {
        			System.out.println(name+" e' stato checkato");
        			gestioneAccountController.abilitaAccountStudente(name);
        		}
        	}
        	elencoAccountStudenti = gestioneAccountController.getElencoAccountStudentiPendent();
        	request.setAttribute("elencoAccountStudenti", elencoAccountStudenti);
        } catch (Exception e) {
            // Report the error using the appropriate name and ID.
            errors.add("name", new ActionError("id"));
        }

        // If a message is required, save the specified key(s)
        // into the request for use by the <struts:errors> tag.

        if (!errors.isEmpty()) {
            saveErrors(request, errors);
            // TODO: E se sto coso sbarella che si fa?
        } else {
            // Forward control to the appropriate 'success' URI (change name as desired)
        	forward = mapping.findForward("init");

        }
        return forward;
    }
}
