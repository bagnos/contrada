package it.contrada.converters;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

import javax.faces.convert.ConverterException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class GregorianCalendarConverter extends javax.faces.convert.DateTimeConverter {

	private static Log log = LogFactory
			.getLog(GregorianCalendarConverter.class);

	public GregorianCalendarConverter() {
		// TODO Auto-generated constructor stub
	}

	public Object getAsObject(FacesContext fctx, UIComponent arg1,
			String dateStr) {
		// TODO Auto-generated method stub
		
		if (dateStr.trim()=="")
		{
			return null;
		}
		
		String pattern = "dd/MM/yyyy";
		SimpleDateFormat format = new SimpleDateFormat(pattern);

		try {
			Date date = format.parse(dateStr);
			GregorianCalendar cal = new GregorianCalendar();
			cal.setTime(date);
			return cal;
		}
		// TODO Auto-generated catch block
		catch (Exception exception) {
			log.error(exception);
			throw new ConverterException("errore nella conversione della data");
		}

	}

	public String getAsString(FacesContext arg0, UIComponent arg1, Object date) {
		// TODO Auto-generated method stub
		try {
			if (date==null)
			{
				return "";
			}
			
			GregorianCalendar cal = new GregorianCalendar();
			cal.setTime((Date)date);
			String pattern = "dd/MM/yyyy";
			SimpleDateFormat format = new SimpleDateFormat(pattern);
			format.setCalendar(cal);
			return format.toString();
		} catch (Exception exception) {
			log.error(exception);
			throw new ConverterException("errore nella conversione della data");
		}

	}

}
