package ipc.forms;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 * Form bean for a Struts application.
 * Users may access 4 fields on this form:
 * <ul>
 * <li>acronimo - [your comment here]
 * <li>dataEsame - [your comment here]
 * <li>dataInizio - [your comment here]
 * <li>dataFine - [your comment here]
 * </ul>
 * @version 	1.0
 * @author
 */
public class CreazioneEsameForm extends ActionForm {
	private static final long serialVersionUID = 12L;

	private String acronimo = null;
    private String dataEsame = null;
    private String dataInizio = null;
    private String dataFine = null;
    private String aule = null;

    public String getAule() {
    	return this.aule;
    }
    
    public void setAule(String a) {
    	this.aule = a;
    }
    
    /**
     * Get acronimo
     * @return String
     */
    public String getAcronimo() {
    	return acronimo;
    }

    /**
     * Set acronimo
     * @param <code>String</code>
     */
    public void setAcronimo(String a) {
    	this.acronimo = a;
    }

    /**
     * Get dataEsame
     * @return String
     */
    public String getDataEsame() {
    	return dataEsame;
    }

    /**
     * Set dataEsame
     * @param <code>String</code>
     */
    public void setDataEsame(String d) {
    	this.dataEsame = d;
    }

    /**
     * Get dataInizio
     * @return String
     */
    public String getDataInizio() {
    	return dataInizio;
    }

    /**
     * Set dataInizio
     * @param <code>String</code>
     */
    public void setDataInizio(String d) {
    	this.dataInizio = d;
    }

    /**
     * Get dataFine
     * @return String
     */
    public String getDataFine() {
    	return dataFine;
    }

    /**
     * Set dataFine
     * @param <code>String</code>
     */
    public void setDataFine(String d) {
    	this.dataFine = d;
    }

    public void reset(ActionMapping mapping, HttpServletRequest request) {
		acronimo = null;
		dataEsame = null;
		dataInizio = null;
		dataFine = null;
    }

    public ActionErrors validate(ActionMapping mapping,
    							 HttpServletRequest request) {
		ActionErrors errors = new ActionErrors();
		if((acronimo != null) && (acronimo.length() == 0))
			errors.add("acronimo", new ActionError("acronimo.error"));
		if((dataEsame != null) && (dataEsame.length() == 0))
			errors.add("dataEsame", new ActionError("data.esame.error"));
		if((dataInizio != null) && (dataInizio.length() == 0))
			errors.add("dataInizio", new ActionError("data.inizio.error"));
		if((dataFine != null) && (dataFine.length() == 0))
			errors.add("dataFine", new ActionError("data.fine.error"));
		return errors;
    }
}
