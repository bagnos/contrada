package it.contrada.backingbeans.pagecodes;

import it.contrada.businessdelegate.GestioneCaricaBD;
import it.contrada.businessdelegate.RicercaCaricaBD;
import it.contrada.dominio.dto.TipoCaricaDTO;
import it.contrada.exceptions.ContradaExceptionBloccante;
import it.contrada.exceptions.ContradaExceptionNonBloccante;

import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ActionEvent;

public class ModificaQuotaCariche {

	private List<TipoCaricaDTO> organigramma;
	private List<TipoCaricaDTO> organigrammaModificato;
	private boolean visibleColonnaModifica;
	private boolean applicaTessereInCorso=true;
	private String note;

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public boolean isApplicaTessereInCorso() {
		return applicaTessereInCorso;
	}

	public void setApplicaTessereInCorso(boolean applicaTessereInCorso) {
		this.applicaTessereInCorso = applicaTessereInCorso;
	}

	public List<TipoCaricaDTO> getOrganigramma() {
		return organigramma;
	}

	public void setOrganigramma(List<TipoCaricaDTO> organigramma) {
		this.organigramma = organigramma;

	}

	public boolean isVisibleColonnaModifica() {
		return visibleColonnaModifica;
	}

	public ModificaQuotaCariche() throws ContradaExceptionBloccante,
			ContradaExceptionNonBloccante {

		iniziallizzaOrganigramma();
	}

	private void iniziallizzaOrganigramma() throws ContradaExceptionBloccante,
			ContradaExceptionNonBloccante {
		organigramma = RicercaCaricaBD.elencaCariche();
		organigrammaModificato=new ArrayList<TipoCaricaDTO>();

	}

	public void modificaTesseraOnClick(ActionEvent e)
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {
		TipoCaricaDTO caricaModificata = (TipoCaricaDTO) (e.getComponent()
				.getAttributes().get("carica"));
		caricaModificata.setEdit(true);
		caricaModificata.setImMinimoNew(caricaModificata.getImMinimo());
		visibleColonnaModifica = true;
	}

	public void commit(ActionEvent e) {
		TipoCaricaDTO caricaModificata = (TipoCaricaDTO) (e.getComponent()
				.getAttributes().get("carica"));
		caricaModificata.setModificata(true);
		organigrammaModificato.add(caricaModificata);
		caricaModificata.setEdit(false);
		caricaModificata.setImMinimo(caricaModificata.getImMinimoNew());
		visibleColonnaModifica = false;
	}

	public void cancel(ActionEvent e) {
		TipoCaricaDTO caricaModificata = (TipoCaricaDTO) (e.getComponent()
				.getAttributes().get("carica"));
		caricaModificata.setEdit(false);
		visibleColonnaModifica = false;
	}

	public String confermaOnClick() throws ContradaExceptionBloccante,
			ContradaExceptionNonBloccante {

		note="";
		int rows = GestioneCaricaBD.aggiornaCarica(organigrammaModificato,
				applicaTessereInCorso);
		if (rows > 0) {
			note = "operazione eseguita correttamente";
		}
		else
		{
			note = "nessuna modifica effettuata";
		}
		return null;
	}

}
