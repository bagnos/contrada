package it.contrada.backingbeans.pagecodes;

import it.contrada.businessdelegate.RicercaTipoRateizzazioneBD;
import it.contrada.businessdelegate.RicercaTipoTesseraBD;
import it.contrada.dominio.dto.TipoTesseraDTO;
import it.contrada.dto.IncassoAnnualeDTO;
import it.contrada.exceptions.ContradaExceptionBloccante;
import it.contrada.exceptions.ContradaExceptionNonBloccante;
import it.contrada.web.util.Configuration;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import com.icesoft.faces.context.effects.JavascriptContext;

public class ListaIncassoAnnuale {

	private int tipoTessera;
	private int anno;
	private List<SelectItem> tipoTessere;
	private List<SelectItem> anni;
	private boolean visibleIncassi;
	private String note;
	private List<IncassoAnnualeDTO> incassi;
	private int totQuota = 0;
	private int totIncassato = 0;
	private List<TipoTesseraDTO> tessere = null;
	private double totPerc;

	public double getTotPerc() {
		return totPerc;
	}

	public int getTotQuota() {
		return totQuota;
	}

	public int getTotIncassato() {
		return totIncassato;
	}

	public List<IncassoAnnualeDTO> getIncassi() {
		return incassi;
	}

	public String getNote() {
		return note;
	}

	public boolean isVisibleIncassi() {
		return visibleIncassi;
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

	public int getAnno() {
		return anno;
	}

	public void setAnno(int anno) {
		this.anno = anno;
	}

	public ListaIncassoAnnuale() throws NumberFormatException,
			ContradaExceptionBloccante, ContradaExceptionNonBloccante {
		initDDLAnni();
		initDDLTessere();
		
	}

	public void ricercaIncassi(ActionEvent a)
			throws ContradaExceptionNonBloccante, ContradaExceptionBloccante {
		List<Integer> tipoTessere = new ArrayList<Integer>();
		tipoTessere.add(tipoTessera);

		incassi = RicercaTipoRateizzazioneBD.recuperaIncassoAnnuale(anno,
				tipoTessere);
		totQuota = 0;
		totIncassato = 0;
		for (IncassoAnnualeDTO incasso : incassi) {
			totQuota += incasso.getQuota();
			totIncassato += incasso.getIncassato();
		}
		if (totQuota != 0) {
			totPerc = (double) totIncassato / (double) totQuota;
			totPerc = totPerc * 100;
		} else {
			totPerc = 0;
		}
		totPerc = new BigDecimal(totPerc, new MathContext(4, RoundingMode.UP))
				.doubleValue();
		visibleIncassi = !incassi.isEmpty();

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

		anno = Integer.parseInt(anni.get(0).getValue().toString());

	}

	private void initDDLTessere() throws ContradaExceptionBloccante,
			ContradaExceptionNonBloccante {
		tipoTessere = new ArrayList<SelectItem>();
		tessere = RicercaTipoTesseraBD.elencaTipoTessera();
		for (TipoTesseraDTO tes : tessere) {
			tipoTessere.add(new SelectItem(tes.getIdTipoTessera(), tes
					.getDsTipoTessera()));
		}
		tipoTessera = Integer
				.parseInt(tipoTessere.get(0).getValue().toString());

	}

}
