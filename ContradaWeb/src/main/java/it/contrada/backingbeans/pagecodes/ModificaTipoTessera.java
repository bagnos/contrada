package it.contrada.backingbeans.pagecodes;


import it.contrada.businessdelegate.GestioneTesseraBD;
import it.contrada.businessdelegate.RicercaTipoTesseraBD;
import it.contrada.dominio.dto.TipoCaricaDTO;
import it.contrada.dominio.dto.TipoTesseraDTO;
import it.contrada.exceptions.ContradaExceptionBloccante;
import it.contrada.exceptions.ContradaExceptionNonBloccante;

import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ActionEvent;

public class ModificaTipoTessera {

	private List<TipoTesseraDTO> tipoTessere;
	private List<TipoTesseraDTO> tipoTessereModificate;
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

	public List<TipoTesseraDTO> getTipoTessere() {
		return tipoTessere;
	}

	public void setTipoTessere(List<TipoTesseraDTO> tipoTessere) {
		this.tipoTessere = tipoTessere;

	}

	public boolean isVisibleColonnaModifica() {
		return visibleColonnaModifica;
	}

	public ModificaTipoTessera() throws ContradaExceptionBloccante,
			ContradaExceptionNonBloccante {

		iniziallizzaTipoTessere();
	}

	private void iniziallizzaTipoTessere() throws ContradaExceptionBloccante,
			ContradaExceptionNonBloccante {
		tipoTessere = RicercaTipoTesseraBD.elencaTipoTessera();
		tipoTessereModificate=new ArrayList<TipoTesseraDTO>();
	}

	public void modificaTesseraOnClick(ActionEvent e)
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {
		TipoTesseraDTO tesseraModificata = (TipoTesseraDTO) (e.getComponent()
				.getAttributes().get("tessera"));
		tesseraModificata.setEdit(true);
		tesseraModificata.setImTesseraNew(tesseraModificata.getImTessera());
		tesseraModificata.setImTesseraPrec(tesseraModificata.getImTessera());
		visibleColonnaModifica = true;
	}

	public void commit(ActionEvent e) {
		TipoTesseraDTO tesseraModificata = (TipoTesseraDTO) (e.getComponent()
				.getAttributes().get("tessera"));
		tesseraModificata.setModificata(true);
		tipoTessereModificate.add(tesseraModificata);
		tesseraModificata.setEdit(false);
		tesseraModificata.setImTessera(tesseraModificata.getImTesseraNew());
		visibleColonnaModifica = false;
	}

	public void cancel(ActionEvent e) {
		TipoTesseraDTO tesseraModificata = (TipoTesseraDTO) (e.getComponent()
				.getAttributes().get("tessera"));
		tesseraModificata.setEdit(false);
		visibleColonnaModifica = false;
	}

	public String confermaOnClick() throws ContradaExceptionBloccante,
			ContradaExceptionNonBloccante {

		note="";
		int rows = GestioneTesseraBD.aggiornaTipoTessera(tipoTessereModificate, applicaTessereInCorso);
				
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
