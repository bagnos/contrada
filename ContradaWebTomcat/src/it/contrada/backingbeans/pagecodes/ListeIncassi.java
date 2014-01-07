package it.contrada.backingbeans.pagecodes;

import it.contrada.businessdelegate.RicercaEsattoreBD;
import it.contrada.businessdelegate.RicercaIncassoBD;
import it.contrada.businessdelegate.RicercaTipoRateizzazioneBD;
import it.contrada.businessdelegate.RicercaTipoTesseraBD;
import it.contrada.dominio.dto.TipoIncassoDTO;
import it.contrada.dominio.dto.TipoTesseraDTO;
import it.contrada.dto.EsattoreDTO;
import it.contrada.dto.ParmIncassoDTO;
import it.contrada.dto.RateizzazioneDTO;
import it.contrada.enumcontrada.TipoIncasso;
import it.contrada.exceptions.ContradaExceptionBloccante;
import it.contrada.exceptions.ContradaExceptionNonBloccante;
import it.contrada.web.util.Configuration;
import it.contrada.web.util.Costante;
import it.contrada.web.util.LoadBundleLanguage;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.ResourceBundle;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import com.icesoft.faces.context.effects.JavascriptContext;

import sun.security.action.GetLongAction;

public class ListeIncassi {

	private List<SelectItem> ricerche;
	private List<SelectItem> anni;
	private List<SelectItem> pagamenti;
	private boolean totale;
	private List<SelectItem> tipoTessere;
	private ParmIncassoDTO parm;
	List<RateizzazioneDTO> rate;
	private boolean renderPagamenti;
	private int nrAnagrafiche;
	private Double importo;
	private Double importoIncassato;
	private String valueNonPresenti;
	private String[] tipoTessereSelected;
	private boolean raggruppaAnag;
	private boolean renderEsattori;
	private List<SelectItem> esattori;

	public List<SelectItem> getEsattori() {
		return esattori;
	}

	public boolean isRenderEsattori() {
		return renderEsattori;
	}

	public boolean isRaggruppaAnag() {
		return raggruppaAnag;
	}

	public void setRaggruppaAnag(boolean raggruppaAnag) {
		this.raggruppaAnag = raggruppaAnag;
	}

	public String[] getTipoTessereSelected() {
		return tipoTessereSelected;
	}

	public void setTipoTessereSelected(String[] tipoTessereSelected) {
		this.tipoTessereSelected = tipoTessereSelected;
	}

	public String getValueNonPresenti() {
		return valueNonPresenti;
	}

	public void setValueNonPresenti(String valueNonPresenti) {
		this.valueNonPresenti = valueNonPresenti;
	}

	public int getNrAnagrafiche() {
		return nrAnagrafiche;
	}

	public void setNrAnagrafiche(int nrAnagrafiche) {
		this.nrAnagrafiche = nrAnagrafiche;
	}

	public Double getImporto() {
		return importo;
	}

	public void setImporto(Double importo) {
		this.importo = importo;
	}

	public Double getImportoIncassato() {
		return importoIncassato;
	}

	public void setImportoIncassato(Double importoIncassato) {
		this.importoIncassato = importoIncassato;
	}

	public boolean isRenderPagamenti() {
		return renderPagamenti;
	}

	public void setRenderPagamenti(boolean renderPagamenti) {
		this.renderPagamenti = renderPagamenti;
	}

	public List<RateizzazioneDTO> getRate() {
		return rate;
	}

	public void setRate(List<RateizzazioneDTO> rate) {
		this.rate = rate;
	}

	public ParmIncassoDTO getParm() {
		return parm;
	}

	public void setParm(ParmIncassoDTO parm) {
		this.parm = parm;
	}

	public List<SelectItem> getPagamenti() {
		return pagamenti;
	}

	public void setPagamenti(List<SelectItem> pagamenti) {
		this.pagamenti = pagamenti;
	}

	public List<SelectItem> getTipoTessere() {
		return tipoTessere;
	}

	public void setTipoTessere(List<SelectItem> tipoTessere) {
		this.tipoTessere = tipoTessere;
	}

	public boolean isTotale() {
		return totale;
	}

	public void setTotale(boolean totale) {
		this.totale = totale;
	}

	public List<SelectItem> getAnni() {
		return anni;
	}

	public void setAnni(List<SelectItem> anni) {
		this.anni = anni;
	}

	public List<SelectItem> getRicerche() {
		return ricerche;
	}

	public void setRicerche(List<SelectItem> ricerche) {
		this.ricerche = ricerche;
	}

	public ListeIncassi() throws ContradaExceptionBloccante,
			ContradaExceptionNonBloccante {
		parm = new ParmIncassoDTO();
		initDDLRicerche();
		initDDLAnni();
		initDDLTipoTessere();
		initDDLEsattori();
		setRenderPagamenti(false);
		renderEsattori = false;
		// initDDLTipoPagamento();
		
		/*
		JavascriptContext.addJavascriptCall(FacesContext.getCurrentInstance(),"Ice.onSendReceive('miaPortlet:form:tipoIncasso', function() {"+
	            "alert('sendCallback!'); document.getElementById('document:body').disabled=true;"+
	        "}, function() {"+			     
	       //"document.getElementById('document:body').disabled=false;"+
	       //"alert('receiveCallback!');"+
	      "});");*/
	}

	private void initDDLAnni() throws NumberFormatException,
			ContradaExceptionBloccante {
		anni = new ArrayList<SelectItem>();
		SelectItem item = null;
		int rangeYear = Integer.parseInt(Configuration
				.getProperty("nrAnniReportIncassi"));
		int year = GregorianCalendar.getInstance().get(Calendar.YEAR);
		for (int i = 0; i <= rangeYear; i++) {
			item = new SelectItem(year - i);
			anni.add(item);
		}
	}

	private void initDDLRicerche() throws ContradaExceptionBloccante,
			ContradaExceptionNonBloccante {
		ricerche = new ArrayList<SelectItem>();
		List<TipoIncassoDTO> tipoIncassi = RicercaIncassoBD.elencaTipoIncasso();
		ricerche.add(new SelectItem(null, LoadBundleLanguage
				.getMessage(Costante.LANGUAGE_VUOTO)));
		for (TipoIncassoDTO tt : tipoIncassi) {
			ricerche.add(new SelectItem(tt.getIdTipoIncasso(), tt
					.getDsTipoIncasso()));
		}
		parm.setTipoIncasso(tipoIncassi.get(0).getIdTipoIncasso());
	}

	private void initDDLTipoTessere() throws ContradaExceptionBloccante,
			ContradaExceptionNonBloccante {
		tipoTessere = new ArrayList<SelectItem>();

		List<TipoTesseraDTO> tipoTessereDTO = null;
		if (parm.getTipoIncasso() != null) {
			tipoTessereDTO = RicercaTipoTesseraBD
					.ricercaTipoTesseraPerIncasso(parm.getTipoIncasso());
		} else {
			tipoTessereDTO = RicercaTipoTesseraBD.elencaTipoTessera();
		}

		for (TipoTesseraDTO tt : tipoTessereDTO) {
			tipoTessere.add(new SelectItem(tt.getIdTipoTessera(), tt
					.getDsTipoTessera()));
		}
		parm.setTipoTessera(tipoTessereDTO.get(0).getIdTipoTessera());
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
	}

	public void cercaClick(ActionEvent e) throws ContradaExceptionBloccante,
			ContradaExceptionNonBloccante {
		if (parm.getTipoIncasso() == null || parm.getTipoIncasso() == 0) {
			parm.setTipoIncasso(null);
		}
		if (parm.getTipoPagamento() == null || parm.getTipoPagamento() == 0) {
			parm.setTipoPagamento(null);
		}
		
		

		parm.setTipoTessere(new ArrayList<Integer>());
		parm.getTipoTessere().add(parm.getTipoTessera());

		rate = null;
		parm.setAnnoA(parm.getAnnoDa());
		if (raggruppaAnag) {
			// raggruppare le anagrafiche per incasso
			rate = RicercaTipoRateizzazioneBD.ricercaRateByAnagrafica(parm);

		} else {
			rate = RicercaTipoRateizzazioneBD.ricercaRate(parm);
		}

		nrAnagrafiche = 0;
		importo = 0d;
		importoIncassato = 0d;

		if (rate.isEmpty()) {
			setValueNonPresenti(LoadBundleLanguage
					.getMessage("DATI_NON_PRESENTI"));
		} else {
			setValueNonPresenti("");
		}

		for (RateizzazioneDTO rata : rate) {
			nrAnagrafiche++;
			importo += rata.getImRata();
			importoIncassato += rata.getImRataIncassata();
		}
		importo = importo / 100;
		importoIncassato = importoIncassato / 100;
		setRenderPagamenti(!rate.isEmpty());
	}

	public void tipoIncassoValueChange(ValueChangeEvent ev) {

		if (ev.getNewValue() != null) {
			if (Integer.parseInt(ev.getNewValue().toString()) == Costante.ID_TIPO_INCASSO_ESATTORE
					&& parm.getTipoTessera() == Costante.TIPO_TESSERA_PROTETTORATO) {
				// tipo incasso esattore e tipo tessera protettorato facciamo
				// vedere l'esattori
				renderEsattori = true;
				parm.setIdEsattore(Integer.parseInt(esattori.get(0).getValue()
						.toString()));
			} else {
				renderEsattori = false;
				parm.setIdEsattore(null);
			}
		}
		
		//JavascriptContext.addJavascriptCall(FacesContext.getCurrentInstance(),"alert('METTO DISABLED A FALSE');document.body.disabled=false;");
	}

	public void tipoTesseraValueChange(ValueChangeEvent ev) {

		if (ev.getNewValue() != null) {
			if (Integer.parseInt(ev.getNewValue().toString()) == Costante.TIPO_TESSERA_PROTETTORATO
					&& parm.getTipoIncasso() == TipoIncasso.ESATTORE
							.getIncasso()) {
				// tipo incasso esattore e tipo tessera protettorato facciamo
				// vedere l'esattori
				renderEsattori = true;
				parm.setIdEsattore(Integer.parseInt(esattori.get(0).getValue()
						.toString()));
			} else {
				renderEsattori = false;
				parm.setIdEsattore(null);
			}
		}
	}
}
