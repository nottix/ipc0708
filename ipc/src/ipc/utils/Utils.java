package ipc.utils;

import java.util.regex.Pattern;
/**
 * @version 	1.0
 * @author		Laurenziello Vincenzo
 * @author 		Notargiacomo Simone
 * @author		Scenna Fabrizio
 */
public class Utils {
	public static boolean check_data(String data) {
		boolean test1 = Pattern.compile("\\d\\d/\\d\\d/20\\d\\d").matcher(data).matches();
		String []dates = data.split("/");
		if(Integer.valueOf(dates[0]) < 1 || Integer.valueOf(dates[0]) > 31)
			return false;
		if(Integer.valueOf(dates[1]) < 1 || Integer.valueOf(dates[1]) > 12)
			return false;
		if(Integer.valueOf(dates[2]) < 2000 || Integer.valueOf(dates[2]) > 2099)
			return false;
		return test1;
	}
	/*If it returns false we have an invalid email!*/
	public static boolean check_email(String email) {
		boolean test1 = Pattern.compile("[a-zA-Z0-9_]+[.[a-zA-Z0-9]+]*@[a-zA-Z0-9_]+[.[a-zA-Z]+]+").matcher(email).matches();
		boolean test2 = Pattern.compile("^\\S+@\\S+$").matcher(email).matches();
		//boolean test3 = Pattern.compile(".+@.+\\.+").matcher(email).matches();
		boolean test4 = Pattern.compile("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$").matcher(email).matches();
		return test1 && test2 && /*test3 &&*/ test4;
	}
}
