package helper;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javafx.util.StringConverter;

public class DateFormatHelper {
	public static StringConverter<LocalDate> getDatePickerFormatter(String pattern) {
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
}