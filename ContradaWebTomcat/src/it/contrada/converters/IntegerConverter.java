package it.contrada.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

public class IntegerConverter implements Converter {

	private static final Integer ZERO_VALUE=Integer.valueOf(0);
	private static final int INT_ZERO_VALUE=0;
	
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String value) {
		// TODO Auto-generated method stub
		if (value != null && value.trim().toString()!="") {
			return Integer.parseInt(value.trim());
		} else {
			return INT_ZERO_VALUE;
		}
	}

	public String getAsString(FacesContext arg0, UIComponent arg1,
			Object valueObject) {
		// TODO Auto-generated method stub
		if (valueObject == null || ((Integer)valueObject).compareTo(ZERO_VALUE)==INT_ZERO_VALUE) {
			return "";
		} else {
			return ((Integer)valueObject).toString();
		}
	}

}
