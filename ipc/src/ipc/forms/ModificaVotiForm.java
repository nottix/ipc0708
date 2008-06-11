package ipc.forms;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 * Form bean for a Struts application.
 * Users may access 8 fields on this form:
 * <ul>
 * <li>idStudente - [your comment here]
 * <li>dataEsame - [your comment here]
 * <li>dataPrenotazione - [your comment here]
 * <li>presenzaEsame - [your comment here]
 * <li>votoEsame - [your comment here]
 * <li>votoAccettato - [your comment here]
 * <li>esaminatore - [your comment here]
 * <li>nota - [your comment here]
 * </ul>
 *
 * @version 	1.0
 * @author		Laurenziello Vincenzo
 * @author 		Notargiacomo Simone
 * @author		Scenna Fabrizio
 */
public class ModificaVotiForm extends ActionForm {
	private static final long serialVersionUID = 21L;

	private String idStudente = null;
    private String dataEsame = null;
    private String dataPrenotazione = null;
    private String presenzaEsame = null;
    private String votoEsame = null;
    private String votoAccettato = null;
    private String esaminatore = null;
    private String nota = null;

    /**
     * Get idStudente
     * @return String
     */
    public String getIdStudente() {
    	return idStudente;
    }

    /**
     * Set idStudente
     * @param <code>String</code>
     */
    public void setIdStudente(String i) {
    	this.idStudente = i;
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
     * Get dataPrenotazione
     * @return String
     */
    public String getDataPrenotazione() {
    	return dataPrenotazione;
    }

    /**
     * Set dataPrenotazione
     * @param <code>String</code>
     */
    public void setDataPrenotazione(String d) {
    	this.dataPrenotazione = d;
    }

    /**
     * Get presenzaEsame
     * @return String
     */
    public String getPresenzaEsame() {
    	return presenzaEsame;
    }

    /**
     * Set presenzaEsame
     * @param <code>String</code>
     */
    public void setPresenzaEsame(String p) {
    	this.presenzaEsame = p;
    }

    /**
     * Get votoEsame
     * @return String
     */
    public String getVotoEsame() {
    	return votoEsame;
    }

    /**
     * Set votoEsame
     * @param <code>String</code>
     */
    public void setVotoEsame(String v) {
   		this.votoEsame = v;
    }

    /**
     * Get votoAccettato
     * @return String
     */
    public String getVotoAccettato() {
    	return votoAccettato;
    }

    /**
     * Set votoAccettato
     * @param <code>String</code>
     */
    public void setVotoAccettato(String v) {
    	this.votoAccettato = v;
    }

    /**
     * Get esaminatore
     * @return String
     */
    public String getEsaminatore() {
    	return esaminatore;
    }

    /**
     * Set esaminatore
     * @param <code>String</code>
     */
    public void setEsaminatore(String e) {
    	this.esaminatore = e;
    }

    /**
     * Get nota
     * @return String
     */
    public String getNota() {
   		return nota;
    }

    /**
     * Set nota
     * @param <code>String</code>
     */
    public void setNota(String n) {
    	this.nota = n;
    }

    public void reset(ActionMapping mapping, HttpServletRequest request) {
		idStudente = null;
		dataEsame = null;
		dataPrenotazione = null;
		presenzaEsame = null;
		votoEsame = null;
		votoAccettato = null;
		esaminatore = null;
		nota = null;
    }

    public ActionErrors validate(ActionMapping mapping,
    							 HttpServletRequest request) {

		ActionErrors errors = new ActionErrors();
	
		return errors;

    }
}
