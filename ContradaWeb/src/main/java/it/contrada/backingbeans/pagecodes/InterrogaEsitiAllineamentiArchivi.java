package it.contrada.backingbeans.pagecodes;

import it.contrada.businessdelegate.RicercaCaricaBD;
import it.contrada.businessdelegate.RicercaFlussoBD;
import it.contrada.dominio.dto.TipoCasualiPreautDTO;
import it.contrada.dto.Contradaiolo;

import it.contrada.exceptions.ContradaExceptionBloccante;
import it.contrada.exceptions.ContradaExceptionNonBloccante;
import it.contrada.preautrid.dto.RidEsitoPreautDTO;
import it.contrada.web.util.ContradaSelectItem;
import it.contrada.web.util.Costante;
import it.contrada.web.util.Errori;

import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

public class InterrogaEsitiAllineamentiArchivi {

	private java.util.Date dtDa;
	private java.util.Date dtA;
	private Integer nrRid;
	private List<RidEsitoPreautDTO> esiti;
	private List<TipoCasualiPreautDTO> causali;
	private boolean visibleEsiti;
	private int causale;
	private List<SelectItem> causaliItem = null;
	private String note;

	public String getNote() {
		return note;
	}

	private List<RidEsitoPreautDTO> esitiConFiltro = null;

	public List<RidEsitoPreautDTO> getEsitiConFiltro() {
		return esitiConFiltro;
	}

	public List<SelectItem> getCausaliItem() {
		return causaliItem;
	}

	public int getCausale() {
		return causale;
	}

	public void setCausale(int causale) {
		this.causale = causale;
	}

	public void setVisibleEsiti(boolean visibleEsiti) {
		this.visibleEsiti = visibleEsiti;
	}

	public boolean isVisibleEsiti() {
		if (esiti == null || esiti.isEmpty()) {
			visibleEsiti = false;
		} else {
			visibleEsiti = true;
		}
		return visibleEsiti;

	}

	public java.util.Date getDtDa() {
		return dtDa;
	}

	public void setDtDa(java.util.Date dtDa) {
		this.dtDa = dtDa;
	}

	public java.util.Date getDtA() {
		return dtA;
	}

	public void setDtA(java.util.Date dtA) {
		this.dtA = dtA;
	}

	public Integer getNrRid() {
		return nrRid;
	}

	public void setNrRid(Integer nrRid) {
		this.nrRid = nrRid;
	}

	public InterrogaEsitiAllineamentiArchivi()
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {
		// TODO Auto-generated constructor stub
		causali = RicercaFlussoBD.elencaCausaliPreaut();
		causaliItem = ContradaSelectItem.valueCausaliItemOf(causali);
	}

	public void cercaClick(ActionEvent av) throws ContradaExceptionBloccante,
			ContradaExceptionNonBloccante {
		note = "";
		if (getNrRid() == null || getNrRid().intValue() == 0) {
			// rid non impostato
			if ((getDtA() != null && getDtDa() != null)
					&& (getDtDa().compareTo(getDtA()) < 0)) {
				// data impostata
				esiti = RicercaFlussoBD.ricercaEsitoPreautPerData(dtDa, dtA);
			} else {
				throw new ContradaExceptionNonBloccante(
						Errori.NESSUN_CRITERIO_DI_RICERCA_IMPOSTATO);
			}
		} else {
			if ((getDtA() != null && getDtDa() != null)
					&& (getDtDa().compareTo(getDtA()) < 0)) {
				esiti = RicercaFlussoBD.ricercaEsito(getNrRid(), dtDa, dtA);
			} else {

				esiti = RicercaFlussoBD.ricercaEsitoPreautPerRid(nrRid);
			}
		}
		esitiConFiltro = esiti;
		if (esiti.isEmpty()) {
			note = "Attenzione, nessun esito trovato.";
		}
	}

	public void causaliOnChange(ValueChangeEvent ev) {
		int causale = Integer.parseInt(ev.getNewValue().toString());
		esitiConFiltro = new ArrayList<RidEsitoPreautDTO>();
		if (causale == Costante.CD_CAUSALE_ESITI_TUTTI) {
			esitiConFiltro=esiti;
		} else {
			for (RidEsitoPreautDTO ridEsito : esiti) {
				if (ridEsito.getCdEsito() == causale) {
					esitiConFiltro.add(ridEsito);
				}
			}
		}
	}

}
