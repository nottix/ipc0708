package ipc.actions;

import ipc.forms.ModificaVotiForm;
import ipc.control.GestioneEsameController;

import java.util.Hashtable;

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


/**
 * @version 	1.0
 * @author
 */
public class ModificaVotiDoneAction extends Action {

    public ActionForward execute(ActionMapping mapping,
    							 ActionForm form,
    							 HttpServletRequest request,
    							 HttpServletResponse response)
            					throws Exception {

        ActionErrors errors = new ActionErrors();
        ActionForward forward = new ActionForward();
        ActionMessages messages = new ActionMessages();
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
            if(control.modificaVoto((Long)request.getSession().getAttribute("idPrenotazioneEsame"), data) == true){
            	messages.add("nome", new ActionMessage("voto.modificato.ok"));
            	request.getSession().removeAttribute("idPrenotazioneEsame");
            } else {
            	errors.add("nome", new ActionMessage("voto.modificato.no"));
            }
        } catch (Exception e) {
        	errors.add("name", new ActionError("generic.error"));
        }
        if (!errors.isEmpty()) {
            saveErrors(request, errors);
            forward = mapping.findForward("error");
        } else if(!messages.isEmpty()) {
        	saveMessages(request, messages);
        	forward = mapping.findForward("success");
        }
        return forward;
    }
}
