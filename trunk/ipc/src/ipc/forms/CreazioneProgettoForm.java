package ipc.forms;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 * Form bean for a Struts application.
 * Users may access 5 fields on this form:
 * <ul>
 * <li>acronimo - [your comment here]
 * <li>titolo - [your comment here]
 * <li>dataConsegna - [your comment here]
 * <li>maxUploadPerStudente - [your comment here]
 * <li>maxDimGruppo - [your comment here]
 * </ul>
 *
 * @version 	1.0
 * @author		Laurenziello Vincenzo
 * @author 		Notargiacomo Simone
 * @author		Scenna Fabrizio
 */
public class CreazioneProgettoForm extends ActionForm {
	private static final long serialVersionUID = 9L;

	private String acronimo = null;
    private String titolo = null;
    private String dataConsegna = null;
    private String maxUploadPerStudente = null;
    private String maxDimGruppo = null;

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
     * Get titolo
     * @return String
     */
    public String getTitolo() {
    	return titolo;
    }

    /**
     * Set titolo
     * @param <code>String</code>
     */
    public void setTitolo(String t) {
    	this.titolo = t;
    }

    /**
     * Get dataConsegna
     * @return String
     */
    public String getDataConsegna() {
    	return dataConsegna;
    }

    /**
     * Set dataConsegna
     * @param <code>String</code>
     */
    public void setDataConsegna(String d) {
    	this.dataConsegna = d;
    }

    /**
     * Get maxUploadPerStudente
     * @return String
     */
    public String getMaxUploadPerStudente() {
    	return maxUploadPerStudente;
    }

    /**
     * Set maxUploadPerStudente
     * @param <code>String</code>
     */
    public void setMaxUploadPerStudente(String m) {
    	this.maxUploadPerStudente = m;
    }

    /**
     * Get maxDimGruppo
     * @return String
     */
    public String getMaxDimGruppo() {
    	return maxDimGruppo;
    }

    /**
     * Set maxDimGruppo
     * @param <code>String</code>
     */
    public void setMaxDimGruppo(String m) {
    	this.maxDimGruppo = m;
    }

    public void reset(ActionMapping mapping, HttpServletRequest request) {
		acronimo = null;
		titolo = null;
		dataConsegna = null;
		maxUploadPerStudente = null;
		maxDimGruppo = null;
    }

    public ActionErrors validate(ActionMapping mapping,
    							 HttpServletRequest request) {

		ActionErrors errors = new ActionErrors();
		if((acronimo != null) && (acronimo.length() == 0))
			errors.add("acronimo", new ActionError("acronimo.error"));
		if((titolo != null) && (titolo.length() == 0))
			errors.add("titolo", new ActionError("titolo.error"));
		if((maxUploadPerStudente!=null) && (maxUploadPerStudente.length()==0))
			errors.add("maxUploadPerStudente", new ActionError("generic.error"));
		if((maxDimGruppo!=null) && (maxDimGruppo.length()==0))
			errors.add("maxDimGruppo", new ActionError("generic.error"));

		return errors;

    }
}
