package it.contrada.web.util;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class ConverterContrada {

	private static final String FORMAT_IT = "##0.00"; //

	private ConverterContrada() {
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

		NumberFormat formatter = new DecimalFormat(FORMAT_IT);
		String strImporto = formatter.format(importoDbl);

		return strImporto;
	}
}
