package it.contrada.web.util;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ConverterContrada {

	private static final String FORMAT_IT = "€ ###,##0.00"; //
	private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss ",Locale.ITALY);
	private static DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols(Locale.ITALY);
	private static NumberFormat formatter = new DecimalFormat(FORMAT_IT,otherSymbols);
	
    


	private ConverterContrada() {
	}
	
	public static String convertDateToString(Date date)
	{
		return sdf.format(date);	
	}

	public static java.sql.Date valueOf(java.util.Date dtDate) {
		return new java.sql.Date(dtDate.getTime());
	}

	public static String convertToImporto(Integer importo) {

		if (importo == null) {
			return "";
		}
		Double importoDbl = ((Integer.valueOf(importo.toString()))
				.doubleValue()) / 100;

		
		
		String strImporto = formatter.format(importoDbl);

		return strImporto;
	}
}
