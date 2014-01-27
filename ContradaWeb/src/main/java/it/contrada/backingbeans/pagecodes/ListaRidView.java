package it.contrada.backingbeans.pagecodes;

import it.contrada.businessdelegate.RicercaRidBD;
import it.contrada.businessdelegate.RicercaTipoTesseraBD;
import it.contrada.dominio.dto.TipoStatoRidDTO;
import it.contrada.dominio.dto.TipoTesseraDTO;
import it.contrada.dto.RidDTO;
import it.contrada.exceptions.ContradaExceptionBloccante;
import it.contrada.exceptions.ContradaExceptionNonBloccante;

import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

public class ListaRidView {
	private int cdStato;
	private List<SelectItem> stati;
	private List<RidDTO> rid;
	private boolean visibleRid;
	private int nrAnagrafiche;
	private double impTotale;
	private List<Integer> idStati;
	private List<SelectItem> tipoTessere;
	private Integer tipoTessera;

	public Integer getTipoTessera() {
		return tipoTessera;
	}

	public void setTipoTessera(Integer tipoTessera) {
		this.tipoTessera = tipoTessera;
	}

	public List<SelectItem> getTipoTessere() throws ContradaExceptionBloccante,
			ContradaExceptionNonBloccante {

		if (tipoTessere == null) {

			tipoTessere = new ArrayList<SelectItem>();		

			List<TipoTesseraDTO> tipoTessereDTO = null;
			tipoTessereDTO = RicercaTipoTesseraBD.elencaTipoTessera();

			tipoTessere.add(new SelectItem("-1","-- Selezionare --"));
			for (TipoTesseraDTO tt : tipoTessereDTO) {
				tipoTessere.add(new SelectItem(tt.getIdTipoTessera(), tt
						.getDsTipoTessera()));

			}
			// protettorato
			tipoTessera = 1;
		}
		return tipoTessere;
	}

	public List<Integer> getIdStati() {
		return idStati;
	}

	public void setIdStati(List<Integer> idStati) {
		this.idStati = idStati;
	}

	public double getImpTotale() {
		return impTotale;
	}

	public int getNrAnagrafiche() {
		nrAnagrafiche = 0;
		if (rid != null) {
			nrAnagrafiche = rid.size();
		}
		return nrAnagrafiche;
	}

	public boolean isVisibleRid() {
		return visibleRid;
	}

	public int getCdStato() {
		return cdStato;
	}

	public List<RidDTO> getRid() {
		return rid;
	}

	public void setCdStato(int cdStato) {
		this.cdStato = cdStato;
	}

	public List<SelectItem> getStati() throws ContradaExceptionBloccante,
			ContradaExceptionNonBloccante {

		if (stati == null) {
			List<TipoStatoRidDTO> statiRid = RicercaRidBD.elencaStati();
			stati = new ArrayList<SelectItem>();
			for (TipoStatoRidDTO rid : statiRid) {
				stati.add(new SelectItem(rid.getIdStatoRid(), rid
						.getDsStatoRid()));
			}
		}
		return stati;
	}

	public void setStati(List<SelectItem> stati) {
		this.stati = stati;
	}

	public void cercaClick(ActionEvent e) throws ContradaExceptionBloccante,
			ContradaExceptionNonBloccante {

		if (tipoTessera==-1)
		{
			tipoTessera=null;
		}
		rid = RicercaRidBD.ricercaPerStato(idStati,tipoTessera);

		visibleRid = !rid.isEmpty();
		impTotale = 0;
		for (RidDTO ridDTO : rid) {
			impTotale += ridDTO.getQuota();
		}
	}

}
