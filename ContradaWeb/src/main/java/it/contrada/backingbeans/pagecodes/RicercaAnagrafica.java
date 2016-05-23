package it.contrada.backingbeans.pagecodes;

import it.contrada.backingbeans.model.RicercaAnagraficheBean;
import it.contrada.businessdelegate.RicercaAnagraficaBD;
import it.contrada.dto.AnagraficaDTO;
import it.contrada.exceptions.ContradaExceptionBloccante;
import it.contrada.exceptions.ContradaExceptionNonBloccante;
import it.contrada.web.util.Costante;
import it.contrada.web.util.FacesUtils;
import it.contrada.web.util.HelperSession;
import it.contrada.web.util.LoadBundleLanguage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpServletRequest;

import com.icesoft.faces.component.ext.RowSelectorEvent;

public class RicercaAnagrafica {
	private int nrAnag;
	private int nrAnagSel;
	private int nrFam;
	private String nome;
	private String cognome;

	private boolean visibleAnagrafiche;
	private boolean visibleModifica;
	private AnagraficaDTO anagrafica = null;
	private boolean visibleMessageNoAnag;
	private String noDataFound;
	private AnagraficaDTO anagSel;
	private RicercaAnagraficheBean ricercaAnagraficheBean;

	public RicercaAnagraficheBean getRicercaAnagraficheBean() {
		return ricercaAnagraficheBean;
	}

	public void setRicercaAnagraficheBean(
			RicercaAnagraficheBean ricercaAnagraficheBean) {
		this.ricercaAnagraficheBean = ricercaAnagraficheBean;
	}

	public String getNoDataFound() {
		return noDataFound;
	}

	public void setNoDataFound(String noDataFound) {
		this.noDataFound = noDataFound;
	}

	public boolean isVisibleMessageNoAnag() {
		return visibleMessageNoAnag;
	}

	public void setVisibleMessageNoAnag(boolean visibleMessageNoAnag) {
		this.visibleMessageNoAnag = visibleMessageNoAnag;
	}

	public AnagraficaDTO getAnagrafica() {
		return anagrafica;
	}

	public void setAnagrafica(AnagraficaDTO anagrafica) {
		this.anagrafica = anagrafica;
	}

	public int getNrAnagSel() {
		return nrAnagSel;
	}

	public void setNrAnagSel(int nrAnagSel) {
		this.nrAnagSel = nrAnagSel;
	}

	public boolean isVisibleModifica() {
		visibleModifica = getAnagrafica() != null;
		return visibleModifica;
	}

	public boolean isVisibleAnagrafiche() {
		visibleAnagrafiche = (ricercaAnagraficheBean.getAnagrafiche() != null && !ricercaAnagraficheBean
				.getAnagrafiche().isEmpty());
		return visibleAnagrafiche;
	}

	public int getNrAnag() {
		return nrAnag;
	}

	public void setNrAnag(int nrAnag) {
		this.nrAnag = nrAnag;
	}

	public int getNrFam() {
		return nrFam;
	}

	public void setNrFam(int nrFam) {
		this.nrFam = nrFam;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public List<AnagraficaDTO> getAnagrafiche() {
		return ricercaAnagraficheBean.getAnagrafiche();
	}

	public void setAnagrafiche(List<AnagraficaDTO> anagrafcihe) {
		ricercaAnagraficheBean.setAnagrafiche(anagrafcihe);
	}

	public String modificaAnagraficaOnClick() {
		// invocare la pagina di modifica
		HelperSession.putInRequest(Costante.SESSION_ANAG_DTO, anagSel);
		HelperSession.putInRequest(Costante.PARM_MOD_ANAG, "true");
		return "MODIFICA_ANAGRAFICA";
	}

	public void reset(ActionEvent e)
	{
		setAnagrafiche(null);
		setVisibleMessageNoAnag(false);
		visibleModifica=false;
		nrAnag=0;
		nrFam=0;
		cognome=null;
		nome=null;
		
	}
	
	public void ricecaAnagraficaOnClick(ActionEvent e)
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {
		AnagraficaDTO anagrafica = null;
		List<AnagraficaDTO> anagrafiche = null;
		setAnagrafiche(null);
		setVisibleMessageNoAnag(true);

		if (getNrAnag() != 0) {

			anagrafica = RicercaAnagraficaBD.ricercaAnagrafica(getNrAnag());
			if (anagrafica != null) {
				anagrafiche = new ArrayList<AnagraficaDTO>();
				anagrafiche.add(anagrafica);
			}

		} else if (getNrFam() != 0) {
			anagrafiche = RicercaAnagraficaBD
					.ricercaAnagraficaPerFamiglia(getNrFam());
		} else if ((getCognome() != null && getCognome().trim() != "")
				&& (getNome() != null && getNome().trim() != "")) {
			anagrafiche = RicercaAnagraficaBD.ricercaAnagraficaPerCognomeNome(
					cognome.trim(), nome.trim());

		} else if (cognome != null && !cognome.isEmpty()) {

			anagrafiche = RicercaAnagraficaBD
					.ricercaAnagraficaPerCognome(cognome.trim());
		} else {
			// nessuna ricerca selezionata
			String errore = "Riempire almeno un campo di ricerca";
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, errore,
							errore));
			setVisibleMessageNoAnag(false);
			return;
		}

		if (anagrafiche != null && !(anagrafiche.isEmpty())) {
			setAnagrafiche(anagrafiche);
			setVisibleMessageNoAnag(false);
		}

	}

	public void rowSelectionListener(RowSelectorEvent e) throws IOException {
		if (e != null) {

			Map<String, Object> atrs = e.getComponent().getAttributes();
			anagSel = (AnagraficaDTO) FacesUtils
					.getValueExpression("idAnag", e);
			HelperSession.putInRequest(Costante.SESSION_ANAG_DTO, anagSel);
			HelperSession.putInRequest(Costante.PARM_MOD_ANAG, "true");
			FacesUtils.navigationToView("MODIFICA_ANAGRAFICA");

			// setAnagrafica(idAnagSel);
		}
		/*
		 * if (!e.getPhaseId().equals(PhaseId.UPDATE_MODEL_VALUES)) {
		 * e.setPhaseId(PhaseId.UPDATE_MODEL_VALUES); e.queue(); } else { int
		 * row = e.getRow(); setAnagrafica(anagrafiche.get(row)); }
		 */
	}

	public void selezionaAnagraficaOnClick(ActionEvent e) throws IOException {
		AnagraficaDTO anagrafica = (AnagraficaDTO) e.getComponent()
				.getAttributes().get("anagrafica");
		HelperSession.putInRequest(Costante.SESSION_ANAG_DTO, anagrafica);
		HelperSession.putInRequest(Costante.PARM_MOD_ANAG, "true");

		FacesUtils.navigationToView("MODIFICA_ANAGRAFICA");
		// FacesUtils.redirectToUrl("InsAnagrafica.iface");
	}

	public RicercaAnagrafica() {
		setNoDataFound(LoadBundleLanguage.getMessage("DATI_NON_PRESENTI"));
		
		

	}
}
