package it.contrada.converters;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

public class ImportoConverter implements Converter {

	
	private final String FORMAT_IT = "€ ###,##0.00"; //
	private final String ERROR_MESSAGE = "importo errato";
	private NumberFormat nf = null;
	private DecimalFormat formatterIT;

	public ImportoConverter() {
		nf = NumberFormat.getNumberInstance(new Locale("it"));
		formatterIT = (DecimalFormat) nf;
		formatterIT.applyPattern(FORMAT_IT);
	}

	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		// TODO Auto-generated method stub

		try {

			Double dblImporto = null;

			Integer intImporto;

			if (arg2 == null) {
				return null;
			}

			if (!arg2.contains(",")) {
				// si motlipica per cento
				arg2 += ",00";
			}
			
			if (!arg2.contains("€")) {
				// si motlipica per cento
				arg2 = "€ " + arg2;
			}

			/*
			arg2 = arg2.replaceAll(",", "");
			arg2 =arg2.replaceAll("\\.+","");
			arg2=arg2.replace("€", "");*/

			dblImporto = formatterIT.parse(arg2.trim()).doubleValue();
			dblImporto=dblImporto*100;

			return Integer.valueOf(dblImporto.intValue());
		} catch (Exception e) {
			throw new ConverterException(new FacesMessage(
					FacesMessage.SEVERITY_ERROR, ERROR_MESSAGE, ERROR_MESSAGE));
		}
	}

	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		// TODO Auto-generated method stub
		try {
			if (arg2 != null) {
				Double importo = ((Integer.valueOf(arg2.toString()))
						.doubleValue()) / 100;

				String strImporto = formatterIT.format(importo);
				return strImporto;
			} else {
				return null;
			}
		} catch (Exception e) {
			throw new ConverterException(new FacesMessage(
					FacesMessage.SEVERITY_ERROR, ERROR_MESSAGE, ERROR_MESSAGE));
		}

	}

}
