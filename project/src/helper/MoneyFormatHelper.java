package helper;

import java.text.DecimalFormat;
import java.text.ParseException;

public class MoneyFormatHelper {
	public static String format(Integer value, String unit) {
		DecimalFormat formatter = new DecimalFormat("#,###");
		String output = formatter.format(value) + " " + unit;
		return output;
	}
	
	public static String format(Integer value) {
		DecimalFormat formatter = new DecimalFormat("#,###");
		String output = formatter.format(value);
		return output;
	}
	
	public static Integer fromString(String str) {
		DecimalFormat formatter = new DecimalFormat("#,###");
		Integer output;
		try {
			output = formatter.parse(str.split(" ")[0]).intValue();
		} catch (ParseException e) {
			output = null;
		}
		return output;
	}
}