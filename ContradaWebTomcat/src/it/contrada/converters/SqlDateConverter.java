package it.contrada.converters;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.DateTimeConverter;

public class SqlDateConverter extends DateTimeConverter {

	public Object getAsObject(FacesContext arg0, UIComponent arg1, String value) {
		// TODO Auto-generated method stub
		// ricevo la stringa in formato dd/MM/yyyy
		if (value == null || value.trim().equalsIgnoreCase("")) {
			return value;
		}

		String pattern = "dd/MM/yyyy";
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		java.util.Date utilDate;
		try {
			utilDate = format.parse(value);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());

		return sqlDate;
	}

	public String getAsString(FacesContext arg0, UIComponent arg1, Object value) {
		// TODO Auto-generated method stub
		if (value == null || value=="") {
			return "";
		}
		
		
		String pattern = "dd/MM/yyyy";
		java.sql.Date date = (java.sql.Date) value;
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		return format.format(date);

	}

}
