package it.contrada.backingbeans.pagecodes;

import it.contrada.businessdelegate.RicercaRidBD;
import it.contrada.dominio.dto.TipoStatoRidDTO;
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
		List<Integer> stati = new ArrayList<Integer>();
		stati.add(cdStato);
		rid = RicercaRidBD.ricercaPerStato(stati);
		
		visibleRid = !rid.isEmpty();
		impTotale=0;
		for (RidDTO ridDTO : rid) {
			impTotale+=ridDTO.getQuota();
		}
	}

}
