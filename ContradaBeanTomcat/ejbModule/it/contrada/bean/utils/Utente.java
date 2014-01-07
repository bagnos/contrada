package it.contrada.bean.utils;

import javax.faces.context.FacesContext;

public class Utente {

	public static String getUser() {
		return FacesContext.getCurrentInstance().getExternalContext()
				.getUserPrincipal().getName();
	}
}
