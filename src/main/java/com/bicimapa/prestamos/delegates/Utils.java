package com.bicimapa.prestamos.delegates;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Utils {

	public static String getPrettyDate(LocalDate date) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM", new Locale("es", "CO"));
		return date.format(formatter).toUpperCase();
	}
	
	public static String getPrettyAmount(int amount) {
		int rounded = ((amount + 99) / 100 ) * 100;
		return "$" + String.format("%,d", rounded).replace(",", ".");
	}
}
