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
import ipc.entity.*;
import ipc.control.*;
import ipc.forms.*;

/**
 * @version 	1.0
 * @author
 */
public class VisualizzaAccountAction extends Action

{
	
	private Account account;
	
	public Account getAccount() {
		return this.account;
	}

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        ActionErrors errors = new ActionErrors();
        ActionForward forward = new ActionForward(); // return value
        GestioneAccountController control = new GestioneAccountController();
        VisualizzaAccountForm cForm = (VisualizzaAccountForm)form;
        
        try {

        	if (isCancelled(request)) {
        		return mapping.findForward("main");
        	}
        	Enumeration en = request.getParameterNames();
        	while(en.hasMoreElements()) {
        		String name = (String)en.nextElement();
        		if(name.equals("radio")) {
        			System.out.println("request: "+request.getParameter(name));
        			//GestioneCorsoForm gestForm = (GestioneCorsoForm)form;
        			//gestForm.setAcronimo(request.getParameter(name));
        			String email = request.getParameter(name);
        			account = control.getAccount(email);
        			request.setAttribute("account", account);
        			cForm.setNome(account.getNome());
        			cForm.setCognome(account.getCognome());
        			cForm.setEmail(account.getEmail());
        			if(account.getTipologia().equals("studente")) {
        				cForm.setNote(account.getNoteStud());
        				cForm.setMatricola(account.getMatricola());
        			}
        			if(account.getTipologia().equals("professore")) {
        				cForm.setNote(account.getNoteProf());
        				cForm.setIsDirettore(account.getIsDirettore()?"on":"");
        				cForm.setIsGestore("");
        				//TODO: isGestore da aggiungere anche nel DB 
        				//FIXIT: questa cosa � tutta da sistemare
        				//cForm.setIsGestore(account.getIsTitolare()sGestore()?"on":"");
        			}
        			//System.out.println("forward: "+mapping.findForward("success").getPath()+"?acronimo="+request.getParameter(name));
                	//return new ActionForward(mapping.findForward("success").getPath()+"?acronimo="+request.getParameter(name), false);
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
            forward = mapping.findForward("error");

        } else {

            // Forward control to the appropriate 'success' URI (change name as desired)
            forward = mapping.findForward("success");

        }

        // Finish with
        return (forward);

    }
}
