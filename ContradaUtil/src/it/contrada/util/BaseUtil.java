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
	
	public static String formatIban(String cdPaese,String checkDigit,String nrCin,String abi,String cab,String conto)
	{
		String iban=String.format("%2s%2s%1s%5s%5s%12s", cdPaese,checkDigit,nrCin,abi,cab,conto);
		iban=iban.replaceAll(" ", "0");
		return iban;
		
		
	}
}
