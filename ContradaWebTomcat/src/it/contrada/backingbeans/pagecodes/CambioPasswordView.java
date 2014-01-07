package it.contrada.backingbeans.pagecodes;

import it.contrada.businessdelegate.GestioneUtenteBD;
import it.contrada.exceptions.ContradaExceptionBloccante;
import it.contrada.exceptions.ContradaExceptionNonBloccante;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import com.icesoft.faces.webapp.http.common.Request;

public class CambioPasswordView {
	
	private String vecchiaPassword;
	private String nuovaPassword;
	private String confermaNuovaPassword;
	private String utente;
	private String note=null;
	
	
	public String getNote() {
		return note;
	}
	public String getVecchiaPassword() {
		return vecchiaPassword;
	}
	public void setVecchiaPassword(String vecchiaPassword) {
		this.vecchiaPassword = vecchiaPassword;
	}
	public String getNuovaPassword() {
		return nuovaPassword;
	}
	public void setNuovaPassword(String nuovaPassword) {
		this.nuovaPassword = nuovaPassword;
	}
	public String getConfermaNuovaPassword() {
		return confermaNuovaPassword;
	}
	public void setConfermaNuovaPassword(String confermaNuovaPassword) {
		this.confermaNuovaPassword = confermaNuovaPassword;
	}
	public String getUtente() {
		return utente;
	}
	public void setUtente(String utente) {
		this.utente = utente;
	}
	
	public void cambiaPassword(ActionEvent ev) throws  ContradaExceptionBloccante
	{
		try {
			note="OPERAZIONE ESEGUITA CORRETTAMENTE";
			utente=FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal().getName();
			GestioneUtenteBD.cambiaPassword(utente, vecchiaPassword, nuovaPassword, confermaNuovaPassword);
		} catch (ContradaExceptionBloccante e) {
			// TODO Auto-generated catch block
			throw e;
		} catch (ContradaExceptionNonBloccante e) {
			// TODO Auto-generated catch block
			
			note=e.getMessage();
		}
	}

}
