package ipc.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
public class RegistrazioneStudenteAction extends Action {
	public ActionForward execute(ActionMapping mapping, 
								 ActionForm form,
								 HttpServletRequest request,
								 HttpServletResponse response)
    					 throws Exception {
		ActionForward forward = null;

		forward = mapping.findForward("success");
		//forward = mapping.findForward("failure");
        return forward;
	}
}
