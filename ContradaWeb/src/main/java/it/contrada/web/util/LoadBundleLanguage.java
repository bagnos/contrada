package it.contrada.web.util;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.ResourceBundle;

import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

public class LoadBundleLanguage {

	private static ResourceBundle bundle = null;

	static {
		bundle = ResourceBundle.getBundle("it.contrada.resources.language",
				FacesContext.getCurrentInstance().getViewRoot().getLocale());

	}

	private LoadBundleLanguage() {
	}

	public static String getMessage(String key) {
		// Look up the requested message text
		return bundle.getString(key);
		
	}
}
