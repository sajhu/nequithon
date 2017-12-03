package com.bicimapa.prestamos.repoository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Utils {

	public static String getPrettyDate(LocalDate date) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM", new Locale("es", "CO"));
		return date.format(formatter).toUpperCase();
	}
}
