package it.contrada.util;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

public class BaseUtil {

	private static final String FORMAT_IT = "€ ###,##0.00"; //
	private static NumberFormat nf = null;
	private static DecimalFormat formatterIT;

	private static DecimalFormat getFormatterIT() {
		if (formatterIT==null)
		{
			nf = NumberFormat.getNumberInstance(new Locale("it"));
			formatterIT = (DecimalFormat) nf;
			formatterIT.applyPattern(FORMAT_IT);
		}
		return formatterIT;
	}

	public BaseUtil() {
		
	}

	public static String formatImporto(Integer imp) {
		if (imp==null)
		{
			return "";
		}
		Double importo = imp.doubleValue() / 100;

		String strImporto = getFormatterIT().format(importo);
		return strImporto;

	}
}
