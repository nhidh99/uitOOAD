package helper;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javafx.util.StringConverter;

public class DateFormatHelper {

	static String pattern = "dd/MM/yyyy";

	public static StringConverter<LocalDate> getDatePickerFormatter() {
		return new StringConverter<LocalDate>() {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);

			@Override
			public LocalDate fromString(String dateString) {
				if (dateString == null)
					return null;
				return LocalDate.parse(dateString, formatter);
			}

			@Override
			public String toString(LocalDate localDate) {
				if (localDate == null)
					return "";
				return formatter.format(localDate);
			}
		};
	}

	public static Date getDate(LocalDate date) {
		return Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
	}

	public static Date getDate(String date) {
		SimpleDateFormat formatter = new SimpleDateFormat(pattern);
		try {
			return formatter.parse(date);
		} catch (ParseException e) {
			return null;
		}
	}

	public static long diffDays(Timestamp ts1, Timestamp ts2) {
		final long MILLIS_PER_DAY = 1000 * 60 * 60 * 24;
		long time1 = ts1.getTime();
		long time2 = ts2.getTime();
		time1 -= time1 % MILLIS_PER_DAY;
		time2 -= time2 % MILLIS_PER_DAY;
		return TimeUnit.DAYS.convert(time1 - time2, TimeUnit.MILLISECONDS);
	}

	public static String toString(Date date) {
		SimpleDateFormat formatter = new SimpleDateFormat(pattern);
		return formatter.format(date);
	}

	public static String toString(LocalDate localDate) {
		SimpleDateFormat formatter = new SimpleDateFormat(pattern);
		return formatter.format(getDate(localDate));
	}
}