package ipc.actions;

import java.util.Enumeration;

import ipc.control.GestioneAccountController;
import ipc.forms.ModificaAccountStudenteForm;
import ipc.entity.Account;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

public class ModificaAccountStudenteSelezionatoAction extends Action {

	private Account account;
	
	public Account getAccount() {
		return account;
	}
	
    public ActionForward execute(ActionMapping mapping, 
    							 ActionForm form,
    							 HttpServletRequest request,
    							 HttpServletResponse response)
            					throws Exception {
    	System.out.println(request.getAttribute("email") + " ok " + request.getParameter("email"));
   		
        ActionErrors errors = new ActionErrors();
        ActionForward forward = new ActionForward();
        ActionMessages messages = new ActionMessages();
        GestioneAccountController gestioneAccountController = new GestioneAccountController();
        ModificaAccountStudenteForm modAcc = (ModificaAccountStudenteForm)form;
        try {
        	Enumeration en = request.getParameterNames();
        	if(en.hasMoreElements() == false) {
        		errors.add("nome", new ActionError("radio.button.error"));
        	} else {
        		while(en.hasMoreElements()) {
        			String name = (String)en.nextElement();
        			if(name.equals("radio")) {
        				account = gestioneAccountController.getAccountStudente(request.getParameter(name));
        		        if(account == null) {
        		        	errors.add("nome", new ActionError("account.studente.no"));
        		        } else {
        		        	System.out.println("ok");
        		        	messages.add("nome", new ActionMessage("account.studente.ok"));
        		        	modAcc.setNome(account.getNome());
        		        	modAcc.setCognome(account.getCognome());
        		        	modAcc.setMatricola(account.getMatricola());
        		        	modAcc.setEmail(account.getEmail());
        		        	modAcc.setNote(account.getNoteStud());	
        		        }
        			}
        		}
        	}
        	
        } catch (Exception e) {
            errors.add("name", new ActionError("generic.error"));
        }
        if (!errors.isEmpty()) {
            saveErrors(request, errors);
            forward = mapping.findForward("error");
        } else if(!messages.isEmpty()) {
        	saveMessages(request, messages);
            forward = mapping.findForward("init");
        }
        return forward;
    }
}
