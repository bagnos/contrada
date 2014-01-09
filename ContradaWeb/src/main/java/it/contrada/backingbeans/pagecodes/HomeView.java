package it.contrada.backingbeans.pagecodes;

import it.contrada.businessdelegate.RicercaOperazioneBD;
import it.contrada.dto.OperazioneDTO;
import it.contrada.exceptions.ContradaExceptionNonBloccante;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HomeView {

	private List<OperazioneDTO> operazioni;
	private boolean visibleOperazioni;

	public boolean isVisibleOperazioni() {
		return visibleOperazioni;
	}

	public List<OperazioneDTO> getOperazioni()
			throws ContradaExceptionNonBloccante {
		operazioni = RicercaOperazioneBD.elencaUltimeOperazioni();
		visibleOperazioni = operazioni != null && !operazioni.isEmpty();
		return operazioni;
	}

	public String logout() throws IOException {
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext ec = context.getExternalContext();

		final HttpServletRequest request = (HttpServletRequest) ec.getRequest();
		
		
		request.getSession(false).invalidate();
		
		return "/Home.jspx?faces-redirect=true"; 

	}
	
	public HomeView() {
		
		FacesContext.getCurrentInstance().getViewRoot().setLocale(new Locale("it","IT")); 
	}

}
