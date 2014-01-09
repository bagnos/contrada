package it.contrada.backingbeans.pagecodes;


import it.contrada.businessdelegate.GestioneAnagraficaBD;
import it.contrada.businessdelegate.RicercaEsattoreBD;
import it.contrada.businessdelegate.RicercaIncassoBD;
import it.contrada.businessdelegate.RicercaTipoTesseraBD;
import it.contrada.dominio.dto.TipoIncassoDTO;
import it.contrada.dominio.dto.TipoTesseraDTO;
import it.contrada.dto.EsattoreDTO;
import it.contrada.dto.TesseraDTO;
import it.contrada.enumcontrada.TipoStatoAnagrafica;
import it.contrada.exceptions.ContradaExceptionBloccante;
import it.contrada.exceptions.ContradaExceptionNonBloccante;
import it.contrada.web.util.Configuration;
import it.contrada.web.util.Costante;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletRequest;

public class RicercaTessereNonPaganti {

	private List<TesseraDTO> tessere;
	private int tipoTessera;
	private int annoDa;
	private List<SelectItem> tipoTessere;
	private List<SelectItem> anni;
	private boolean visibleAnagrafiche;
	private int nrTessere;
	private boolean selTutti;
	private String note;
	private int tipoIncasso;
	private List<SelectItem> tipoIncassi;
	private int annoA;
	private Integer idEsattore;
	private List<SelectItem> esattori;
	private boolean renderEsattori;
	private int impTotale;

	public int getImpTotale() {
		return impTotale;
	}

	public boolean isRenderEsattori() {
		return renderEsattori;
	}

	public Integer getIdEsattore() {
		return idEsattore;
	}

	public void setIdEsattore(Integer idEsattore) {
		this.idEsattore = idEsattore;
	}

	public List<SelectItem> getEsattori() {
		return esattori;
	}

	public void setEsattori(List<SelectItem> esattori) {
		this.esattori = esattori;
	}

	public int getAnnoA() {
		return annoA;
	}

	public void setAnnoA(int annoA) {
		this.annoA = annoA;
	}

	public int getTipoIncasso() {
		return tipoIncasso;
	}

	public void setTipoIncasso(int tipoIncasso) {
		this.tipoIncasso = tipoIncasso;
	}

	public List<SelectItem> getTipoIncassi() {
		return tipoIncassi;
	}

	public String getNote() {
		return note;
	}

	public boolean isSelTutti() {
		return selTutti;
	}

	public void setSelTutti(boolean selTutti) {
		this.selTutti = selTutti;
	}

	public int getNrTessere() {
		return nrTessere;
	}

	public boolean isVisibleAnagrafiche() {
		return visibleAnagrafiche;
	}

	public List<SelectItem> getTipoTessere() {
		return tipoTessere;
	}

	public List<SelectItem> getAnni() {
		return anni;
	}

	public int getTipoTessera() {
		return tipoTessera;
	}

	public void setTipoTessera(int tipoTessera) {
		this.tipoTessera = tipoTessera;
	}

	public int getAnnoDa() {
		return annoDa;
	}

	public void setAnnoDa(int annoDa) {
		this.annoDa = annoDa;
	}

	public List<TesseraDTO> getTessere() {
		return tessere;
	}

	public RicercaTessereNonPaganti() throws NumberFormatException,
			ContradaExceptionBloccante, ContradaExceptionNonBloccante {
		initDDLAnni();
		initDDLTessere();
		initDDLTipoIncasso();
		initDDLEsattori();
		
		
	}

	public void ricercaListaNonPaganti(ActionEvent a)
			throws ContradaExceptionNonBloccante, ContradaExceptionBloccante {

		impTotale=0;
		
		if (annoA - annoDa < 0) {
			note = "Attenzione: l'anno successivo deve essere maggiore dell'anno precedente";
			return;
		}

		if (tipoIncasso == -1) {
			tessere = RicercaTipoTesseraBD.ricercaTessereNonPagantiUltimiAnni(
					tipoTessera, annoDa, annoA);
		} else {
			if (idEsattore==null || idEsattore == 0) {
				idEsattore = null;
			}
			tessere = RicercaTipoTesseraBD.ricercaTessereNonPagantiUltimiAnni(
					tipoTessera, annoDa, tipoIncasso, annoA, idEsattore);
		}
		for(TesseraDTO tes:tessere)
		{
			impTotale+=tes.getQuota();
		}
		
		// imposto in sessione le anagrafiche per la stampa pdf da servlet
		((HttpServletRequest) FacesContext.getCurrentInstance()
				.getExternalContext().getRequest()).getSession().setAttribute(
				Costante.INSOLUTI_PDF_SESSION, tessere);
		((HttpServletRequest) FacesContext.getCurrentInstance()
				.getExternalContext().getRequest()).getSession().setAttribute(
				Costante.INSOLUTI_PDF_ANNO_DA_SESSION, getAnnoDa());
		
		((HttpServletRequest) FacesContext.getCurrentInstance()
				.getExternalContext().getRequest()).getSession().setAttribute(
				Costante.INSOLUTI_PDF_ANNO_A_SESSION, getAnnoA());

		visibleAnagrafiche = !tessere.isEmpty();
		nrTessere = tessere.size();
	}

	public void eliminaSelezionati(ActionEvent a)
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {
		note = null;
		List<Integer> idAnagrafiche = new ArrayList<Integer>();
		if (tessere != null) {
			for (TesseraDTO tes : tessere) {
				if (tes.isModificata()) {
					idAnagrafiche.add(tes.getIdAnag());
				}
			}
			if (!idAnagrafiche.isEmpty()) {
				GestioneAnagraficaBD.aggiornaStatoAnagrafica(idAnagrafiche,
						TipoStatoAnagrafica.Cessata.getStatoAnagrafica());
				note = String.format("Cessate %d anagrafiche", idAnagrafiche
						.size());
				if (tipoIncasso == -1) {
					tessere = RicercaTipoTesseraBD
							.ricercaTessereNonPagantiUltimiAnni(tipoTessera,
									annoDa, annoA);
				} else {
					if (idEsattore == 0) {
						idEsattore = null;
					}
					tessere = RicercaTipoTesseraBD
							.ricercaTessereNonPagantiUltimiAnni(tipoTessera,
									annoDa, tipoIncasso, annoA, idEsattore);
				}
			} else {
				note = "Nessuna anagrafica selezionata";
			}

		}
	}

	public void selectAll(ValueChangeEvent event) {
		if (!event.getPhaseId().equals(PhaseId.UPDATE_MODEL_VALUES)) {
			event.setPhaseId(PhaseId.UPDATE_MODEL_VALUES);
			event.queue();
		} else {

			if (tessere != null) {
				for (TesseraDTO tes : tessere) {
					tes.setModificata(selTutti);
				}

			}
		}
	}

	private void initDDLAnni() throws NumberFormatException,
			ContradaExceptionBloccante {
		GregorianCalendar gCalendar = new GregorianCalendar();
		int annoInCorso = gCalendar.get(GregorianCalendar.YEAR);
		anni = new ArrayList<SelectItem>();

		int anniRendicontabili = Integer.parseInt(Configuration
				.getProperty("anniTessereNonPagate"));
		for (int i = 0; i <= anniRendicontabili; i++) {

			anni.add(new SelectItem(Integer.valueOf(annoInCorso - i), Integer
					.toString(annoInCorso - i)));
		}

		annoDa = Integer.parseInt(anni.get(0).getValue().toString());
		annoA = annoDa;

	}

	private void initDDLTessere() throws ContradaExceptionBloccante,
			ContradaExceptionNonBloccante {
		tipoTessere = new ArrayList<SelectItem>();
		List<TipoTesseraDTO> tessere = RicercaTipoTesseraBD.elencaTipoTessera();
		for (TipoTesseraDTO tes : tessere) {
			tipoTessere.add(new SelectItem(tes.getIdTipoTessera(), tes
					.getDsTipoTessera()));
		}
		tipoTessera = Integer
				.parseInt(tipoTessere.get(0).getValue().toString());

	}

	private void initDDLTipoIncasso() throws ContradaExceptionBloccante,
			ContradaExceptionNonBloccante {
		tipoIncassi = new ArrayList<SelectItem>();
		List<TipoIncassoDTO> incassi = RicercaIncassoBD.elencaTipoIncasso();
		if (incassi != null && !incassi.isEmpty()) {
			tipoIncassi.add(new SelectItem(-1, "-- Tutti --"));
			for (TipoIncassoDTO incasso : incassi) {
				tipoIncassi.add(new SelectItem(incasso.getIdTipoIncasso(),
						incasso.getDsTipoIncasso()));
			}
		}
		tipoIncasso = -1;

	}

	private void initDDLEsattori() throws ContradaExceptionBloccante,
			ContradaExceptionNonBloccante {
		esattori = new ArrayList<SelectItem>();

		List<EsattoreDTO> esattoriDTO = null;
		esattoriDTO = RicercaEsattoreBD.elenca();
		esattori.add(new SelectItem(0, "Tutti"));
		for (EsattoreDTO es : esattoriDTO) {
			esattori
					.add(new SelectItem(es.getIdEsattore(), es.getDsEsattore()));
		}
		idEsattore = null;
	}

	public void tipoIncassoValueChange(ValueChangeEvent ev) {
		idEsattore = null;
		if (ev.getNewValue() != null) {
			if (Integer.parseInt(ev.getNewValue().toString()) == Costante.ID_TIPO_INCASSO_ESATTORE
					&& tipoTessera == Costante.TIPO_TESSERA_PROTETTORATO) {
				// tipo incasso esattore e tipo tessera protettorato facciamo
				// vedere l'esattori
				renderEsattori = true;

			} else {
				renderEsattori = false;

			}
		}
	}

}
