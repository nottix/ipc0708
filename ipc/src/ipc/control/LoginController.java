/**
 * 
 */
package ipc.control;

import ipc.db.SQLDAO;
import ipc.entity.Account;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author Simone Notargiacomo
 *
 */
public class LoginController {

	public Boolean login(String email, String password) {
		Account unAccount = null;
		SQLDAO sqlDAO = new SQLDAO();
		try {
			System.out.println("AAAAAAA: "+email);
			unAccount = sqlDAO.getAccount(email);
			System.out.println(unAccount==null?"nullo":"non nullo");
			System.out.println("email: "+unAccount.getEmail());
		} catch (Exception e) {}
		String passDB = unAccount.getPassword();
		String passEnc = convertToMD5(password);
		if(passEnc.equals(passDB))
			return Boolean.TRUE;
		else
			return Boolean.FALSE;
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
