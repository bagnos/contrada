package it.contrada.backingbeans.pagecodes;

import it.contrada.businessdelegate.GestioneRateizzazioneBD;
import it.contrada.businessdelegate.RicercaAnagraficaBD;
import it.contrada.businessdelegate.RicercaTipoRateizzazioneBD;
import it.contrada.businessdelegate.RicercaTipoTesseraBD;
import it.contrada.dominio.dto.TipoTesseraDTO;
import it.contrada.dto.AnagraficaDTO;
import it.contrada.dto.RateizzazioneDTO;
import it.contrada.enumcontrada.TipoStatoRata;
import it.contrada.exceptions.ContradaExceptionBloccante;
import it.contrada.exceptions.ContradaExceptionNonBloccante;
import it.contrada.web.enumcontrada.TipoRicercaIncassi;
import it.contrada.web.util.Configuration;
import it.contrada.web.util.Errori;
import it.contrada.web.util.RicercaAnagraficaUtil;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.TreeSet;

import javax.faces.event.ActionEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import com.icesoft.faces.component.selectinputtext.SelectInputText;

public class InterrogazioneIncassiTessere {
	private int ricerca;
	private List<SelectItem> ricerche;
	private List<RateizzazioneDTO> rateizzazioni;
	private boolean visibleInterrogazioni;
	private int incassato;
	private int incassoPrevisto;
	private String idAnagrafica;
	private Integer tessera;
	private List<SelectItem> tessereItem;
	private List<SelectItem> anagraficheItem;
	private String dsAnagrafica;
	private boolean visibleAnagraficaPopup;
	private Integer annoDa;
	private Integer annoA;
	private List<SelectItem> anni;
	private String messaggio;
	private String[] tipoTessere;

	public void setTipoTessere(String[] tipoTessere) {
		this.tipoTessere = tipoTessere;
	}

	public String[] getTipoTessere() {
		return tipoTessere;
	}

	public String getMessaggio() {
		return messaggio;
	}

	public List<SelectItem> getAnni() {
		return anni;
	}

	public void setAnni(List<SelectItem> anni) {
		this.anni = anni;
	}

	public Integer getAnnoDa() {
		return annoDa;
	}

	public void setAnnoDa(Integer annoDa) {
		this.annoDa = annoDa;
	}

	public Integer getAnnoA() {
		return annoA;
	}

	public void setAnnoA(Integer annoA) {
		this.annoA = annoA;
	}

	public List<SelectItem> getAnagraficheItem() {
		return anagraficheItem;
	}

	public void setAnagraficheItem(List<SelectItem> anagraficheItem) {
		this.anagraficheItem = anagraficheItem;
	}

	public String getDsAnagrafica() {
		return dsAnagrafica;
	}

	public void setDsAnagrafica(String dsAnagrafica) {
		this.dsAnagrafica = dsAnagrafica;
	}

	public boolean isVisibleAnagraficaPopup() {
		return visibleAnagraficaPopup;
	}

	public void setVisibleAnagraficaPopup(boolean visibleAnagraficaPopup) {
		this.visibleAnagraficaPopup = visibleAnagraficaPopup;
	}

	public void setNrTessere(int nrTessere) {
		this.nrTessere = nrTessere;
	}

	public Integer getTessera() {
		return tessera;
	}

	public void setTessera(Integer tessera) {
		this.tessera = tessera;
	}

	public List<SelectItem> getTessereItem() {
		return tessereItem;
	}

	public void setTessereItem(List<SelectItem> tessereItem) {
		this.tessereItem = tessereItem;
	}

	public String getIdAnagrafica() {
		return idAnagrafica;
	}

	public void setIdAnagrafica(String idAnagrafica) {
		this.idAnagrafica = idAnagrafica;
	}

	public int getIncassoPrevisto() {
		return incassoPrevisto;
	}

	public void setIncassoPrevisto(int incassoPrevisto) {
		this.incassoPrevisto = incassoPrevisto;
	}

	public int getIncassato() {

		return incassato;
	}

	public void setIncassato(int incassato) {
		this.incassato = incassato;
	}

	private int nrTessere;

	public int getNrTessere() {
		nrTessere = getRateizzazioni().size();
		return nrTessere;
	}

	public boolean isVisibleInterrogazioni() {
		return visibleInterrogazioni;
	}

	public void setVisibleInterrogazioni(boolean visibleInterrogazioni) {
		this.visibleInterrogazioni = visibleInterrogazioni;
	}

	public List<RateizzazioneDTO> getRateizzazioni() {
		return rateizzazioni;
	}

	public void setRateizzazioni(List<RateizzazioneDTO> rateizzazioni) {
		this.rateizzazioni = rateizzazioni;
	}

	public int getRicerca() {
		return ricerca;
	}

	public void setRicerca(int ricerca) {
		this.ricerca = ricerca;
	}

	public List<SelectItem> getRicerche() {
		return ricerche;
	}

	public void setRicerche(List<SelectItem> ricerche) {
		this.ricerche = ricerche;
	}

	public InterrogazioneIncassiTessere() throws ContradaExceptionBloccante,
			ContradaExceptionNonBloccante {
		
		
		
		
		ricerche = new ArrayList<SelectItem>();
		ricerche.add(new SelectItem(TipoRicercaIncassi.Anagrafica.getRicerca(),
				TipoRicercaIncassi.Anagrafica.toString()));
		setRicerca(TipoRicercaIncassi.Anagrafica.getRicerca());
		setVisibleInterrogazioni(false);

		prepareDDLTessere();
		initDDLAnni();

	}

	public void ricercaValueChange(ValueChangeEvent event) {

	}

	public void cercaClick(ActionEvent event)
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {
		List<RateizzazioneDTO> rate = null;
		Integer[] tipoTessereInt = null;

		messaggio = null;
		if (getIdAnagrafica() == null || getIdAnagrafica().isEmpty()) {
			messaggio = Errori.ANAG_REQUIRED;
			return;
		}

		if (getTessera() == null || getTessera() == -1) {
			tipoTessereInt = null;
		} else {
			tipoTessereInt = new Integer[1];

			tipoTessereInt[0] = getTessera();

		}

		if (getAnnoDa() == null || getAnnoDa() == 0) {
			setAnnoDa(null);
		}
		if (getAnnoA() == null || getAnnoA() == 0) {
			setAnnoA(null);
		}

		rate = RicercaTipoRateizzazioneBD.ricercaRateizzazionePerAnagrafica(
				Integer.parseInt(getIdAnagrafica()), tipoTessereInt,
				getAnnoDa(), getAnnoA());

		setRateizzazioni(rate);
		/*
		setDsAnagrafica(null);
		setIdAnagrafica(null);*/
		setVisibleInterrogazioni(!rate.isEmpty());
		calolaIncassi();
	}

	private void calolaIncassi() {
		setIncassoPrevisto(0);
		setIncassato(0);
		HashMap<Integer, Object> tessere = new HashMap<Integer, Object>();
		int idTessera = 0;
		int anno = 0;

		if (getRateizzazioni().isEmpty()) {
			return;
		}

		for (RateizzazioneDTO rata : getRateizzazioni()) {

			if (rata.getTipoStatoRata() == TipoStatoRata.Pagata.getStatoRata()) {
				setIncassato(getIncassato() + rata.getImRataIncassata());
			}
			if (rata.getTipoStatoRata() == TipoStatoRata.Inviata.getStatoRata()) {
				rata.setImRataIncassata(0);
			}

			if (idTessera != rata.getIdTessera() || anno != rata.getNrAnno()) {
				setIncassoPrevisto(rata.getImRata() + getIncassoPrevisto());
			}
			idTessera = rata.getIdTessera();
			anno = rata.getNrAnno();
		}
	}

	public void eliminaRataOnClick(ActionEvent e)
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {
		String idRateizzazione = e.getComponent().getAttributes().get(
				"idRateizzazione").toString();
		RateizzazioneDTO recToDelete = null;
		for (RateizzazioneDTO rec : getRateizzazioni()) {
			if (rec.getIdRateizzazione().equalsIgnoreCase(idRateizzazione)) {
				recToDelete = rec;
				break;
			}
		}
		if (recToDelete != null) {
			GestioneRateizzazioneBD.eliminaRateizzazione(recToDelete);
			getRateizzazioni().remove(recToDelete);
		}

	}

	private void prepareDDLTessere() throws ContradaExceptionBloccante,
			ContradaExceptionNonBloccante {
		tessereItem = new ArrayList<SelectItem>();
		List<TipoTesseraDTO> tessere = RicercaTipoTesseraBD.elencaTipoTessera();
		tessereItem.add(new SelectItem(-1, ""));
		for (TipoTesseraDTO tes : tessere) {
			tessereItem.add(new SelectItem(tes.getIdTipoTessera(), tes
					.getDsTipoTessera()));
		}
	}

	public void updateListAnagrafiche(ValueChangeEvent e)
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {
		if (!e.getPhaseId().equals(PhaseId.UPDATE_MODEL_VALUES)) {
			e.setPhaseId(PhaseId.UPDATE_MODEL_VALUES);
			e.queue();
		} else {

			if (e.getNewValue() != null
					&& !e.getNewValue().toString().isEmpty()) {
				SelectInputText autoComplete = (SelectInputText) e
						.getComponent();
				if (autoComplete.getSelectedItem() != null) {
					AnagraficaDTO anagrafe = (AnagraficaDTO) autoComplete
							.getSelectedItem().getValue();
					setIdAnagrafica(Integer
							.toString(anagrafe.getIdAnagrafica()));
				} else {
					List<AnagraficaDTO> anagrafiche = RicercaAnagraficaUtil
							.getAnagraficaByAutocomplete(e.getNewValue()
									.toString());

					anagraficheItem = new ArrayList<SelectItem>();
					if (anagrafiche.isEmpty()) {
						setDsAnagrafica("");

					}
					for (AnagraficaDTO anagrafica : anagrafiche) {
						anagraficheItem.add(new SelectItem(anagrafica,
								anagrafica.getIntestatario()));

					}

				}

			}
		}

	}

	public void closeAnagraficaPopup(ActionEvent ev) {
		visibleAnagraficaPopup = false;
	}

	public void openAnagraficaPopup(ActionEvent ev) {
		visibleAnagraficaPopup = true;
	}

	private void initDDLAnni() throws NumberFormatException,
			ContradaExceptionBloccante {
		anni = new ArrayList<SelectItem>();
		SelectItem item = null;
		int baseYear = Integer.parseInt(Configuration
				.getProperty("nrAnniInterrogazioneAnagraficaIncassi"));
		int toYear = GregorianCalendar.getInstance().get(Calendar.YEAR);
		anni.add(new SelectItem(null));
		for (int i = baseYear; i <= toYear; i++) {
			item = new SelectItem(i);
			anni.add(item);
		}
	}

}
