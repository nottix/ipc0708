package ipc.control;
import java.util.Date;
import java.util.Hashtable;
import java.util.Random;
import java.util.List;

import ipc.entity.*;
import ipc.db.SQLDAO;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.math.BigInteger;


/**
 * @author vinx
 ********************
 *    Promemoria
 ********************
 * Attenzione a quando gestisci lo status di una qualsiasi entita'!!!!
 * Lo status puo' avere 2 o 3 valori a seconda dell'entita', ossia:
 * - pendent
 * - enabled
 * - disabled
 * Controllare, aggiornare o inserire _sempre_ ed unicamente uno 
 * di questi valori!!!
 */
public class Test {
	public static void insertTestAccount() {
		SQLDAO sqlDAO = new SQLDAO();
		Hashtable<String, Object> data = new Hashtable<String, Object>();
		data.put("nome", "sempronio");
		data.put((String)"cognome", (String) "caio");
		data.put("email", "sempronio@caio.it");
		data.put("password", convertToMD5("prova"));
		data.put("status", "pending");
		data.put("tipologia", "studente");
		data.put("isDirettore", Boolean.FALSE);
		data.put("matricola", "000000");
		data.put("isTitolare", Boolean.FALSE);
		data.put("canUpload", Boolean.FALSE);
		data.put("noteUpload", "");
		data.put("noteStud", "");
		data.put("noteProf", "");
		try {
			sqlDAO.createAndStoreAccount(data);
		} catch(Exception e) {}
	}
	
	public static Long insertTestInfoEsame(Long idEsame) {
		Long id = null;
		System.err.println("ID ESAMEEEEEEEEEEEEEEEEEEEEEEE: " + idEsame);
		SQLDAO sqlDAO = new SQLDAO();
		Hashtable<String, Object> data = new Hashtable<String, Object>();
		data.put("dataEsame", new Date());
		data.put("aulaEsame", "Aula 1");
		data.put("idEsame", idEsame);
		try {
			sqlDAO.createAndStoreInfoEsame(data);
		} catch(Exception e) {}
		data.clear();
		data.put("dataEsame", new Date());
		data.put("aulaEsame", "Aula 2");
		data.put("idEsame", idEsame);
		try {
			id = sqlDAO.createAndStoreInfoEsame(data);
		} catch(Exception e) {}
		return id;
	}
	
	public static void updateTestInfoEsame(Long idInfoEsame) {
		boolean ret = false;
		SQLDAO sqlDAO = new SQLDAO();
		System.err.println("ID INFO ESAME: " + idInfoEsame);
		Hashtable<String, Object> data = new Hashtable<String, Object>();
		data.put("aulaEsame", "Aula 3");
		try {
			ret = sqlDAO.updateInfoEsame(idInfoEsame, data);
		} catch (Exception e) {}
		if(ret == true) {
			System.out.println("update ok");
		} else {
			System.out.println("update no");
		}
	}
	
	public static Long insertTestEsame() {
		Long id = null;
		SQLDAO sqlDAO = new SQLDAO();
		Hashtable<String, Object> data = new Hashtable<String, Object>();
		data.put("dataInizioPeriodoPrenotazione", new Date());
		data.put("dataFinePeriodoPrenotazione", new Date());
		try {
			id = sqlDAO.createAndStoreEsame(data);
		} catch (Exception e) {}
		return id;
	}
	
	public static Long insertTestEsame2() {
		Long id = null;
		SQLDAO sqlDAO = new SQLDAO();
		Hashtable<String, Object> data = new Hashtable<String, Object>();
		data.put("dataInizioPeriodoPrenotazione", new Date());
		data.put("dataFinePeriodoPrenotazione", new Date());
		try {
			id = sqlDAO.createAndStoreEsame(data);
		} catch (Exception e) {}
		return id;
	}
	
	public static void validate(String email, String password) {
		Account unAccount = null;
		SQLDAO sqlDAO = new SQLDAO();
		try {
			unAccount = sqlDAO.getAccount(email);
		} catch (Exception e) {}
		String passDB = unAccount.getPassword();
		String passEnc = convertToMD5(password);
		if(passEnc.equals(passDB))
			System.err.println("SIIIIIIIIIIIIII");
		else
			System.err.println("NOOOOOOOOOOOOOOOOO");
	}
	
	public static void selectInfoEsame() {
		SQLDAO sqlDAO = new SQLDAO();
		String table = "InfoEsame";
		String suffix = "ie";
		String []expLogic = {"or"};
		Hashtable<String, Comparable> data = new Hashtable<String, Comparable>();
		data.put("idEsame", 1);
		data.put("aulaEsame", "Aula 1");
		List lie = sqlDAO.queryWrapper(table, suffix, expLogic, data);
		for(int i = 0; i < lie.size(); i++) {
			InfoEsame ie = (InfoEsame) lie.get(i);
			System.err.println(ie.getIdEsame() + "\t" + ie.getAulaEsame() + "\t" + ie.getDataEsame());
		}
	}
	
	public static void main(String []args) {
		insertTestAccount();
//		Long exam = insertTestEsame();
//		Long exam2 = insertTestEsame2();
//		insertTestInfoEsame(exam2);
//		Long examInfo = insertTestInfoEsame(exam);
//		validate("sempronio@caio.it", "prova");
//		validate("sempronio@caio.it", "provaaaa");
//		System.out.println(generatePassword());
//		System.out.println(generatePassword());
//		System.out.println(generatePassword());
//		System.out.println(generatePassword());
//		System.out.println(generatePassword());
//		System.out.println(generatePassword());
//		selectInfoEsame();
//		updateTestInfoEsame(examInfo);	
//		selectInfoEsame();
	}
	
	public static String generatePassword() {
		String alfabeto = "0987654321poiuytrewqlhgfdsamvcxzPOIUYTREWQLKJHGFDSAMBVCXZ";
		String str = "";
		Random r = new Random();
		for (int i=0; i < 8; i++)
			str += alfabeto.toCharArray()[r.nextInt(alfabeto.length())];
		return str;
	}
	
	public static String convertToMD5(String password) {
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("MD5");
		} catch(NoSuchAlgorithmException nsae) {
			System.exit(1);
		}
		md.update(password.getBytes());
		return (new BigInteger(1,md.digest())).toString(16);
	}
}
