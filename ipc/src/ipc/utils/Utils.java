package ipc.utils;

import java.util.regex.Pattern;

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

}
