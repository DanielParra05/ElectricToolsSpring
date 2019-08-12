package com.spring.electric.tools.converters;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @author Jose Daniel Parra
 *
 */
public class DateConverter {

	/**
	 * Convertidor de fechas
	 * 
	 * @param dateToConvert
	 * @return
	 */
	public static LocalDate convertToLocalDate(String dateToConvert) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/d");
		String date = dateToConvert.replace('-', '/');
		// convert String to LocalDate
		LocalDate localDate = LocalDate.parse(date, formatter);
		return localDate;
	}
	
}
