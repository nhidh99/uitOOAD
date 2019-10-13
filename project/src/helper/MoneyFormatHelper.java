package helper;

import java.text.DecimalFormat;

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
}