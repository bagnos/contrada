package it.contrada.backingbeans.pagecodes;

import it.contrada.businessdelegate.RicercaAnagraficaBD;
import it.contrada.businessdelegate.RicercaTipoTesseraBD;
import it.contrada.dominio.dto.TipoTesseraDTO;
import it.contrada.dto.AnagraficaDTO;
import it.contrada.exceptions.ContradaExceptionBloccante;
import it.contrada.exceptions.ContradaExceptionNonBloccante;
import it.contrada.web.util.Configuration;
import it.contrada.web.util.LoadBundleLanguage;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

public class ListaFazzoletti {

	private List<AnagraficaDTO> anags;
	private String note;
	private int nrAnagrafiche;
	private boolean visibleAnagrafiche;
	private int annoDa;
	private List<SelectItem> annoItems;

	public List<SelectItem> getAnnoItems() {
		return annoItems;
	}

	public void setAnnoDa(int annoDa) {
		this.annoDa = annoDa;
	}

	public int getAnnoDa() {
		return annoDa;
	}

	public boolean isVisibleAnagrafiche() {
		return visibleAnagrafiche;
	}

	public int getNrAnagrafiche() {
		return nrAnagrafiche;
	}

	public String getNote() {
		return note;
	}

	public List<AnagraficaDTO> getAnags() {
		return anags;
	}

	public ListaFazzoletti() throws NumberFormatException,
			ContradaExceptionBloccante, ContradaExceptionNonBloccante {

		initDDLAnni();
	}

	private void initDDLAnni() throws NumberFormatException,
			ContradaExceptionBloccante {
		GregorianCalendar gCalendar = new GregorianCalendar();
		int annoInCorso = gCalendar.get(GregorianCalendar.YEAR);
		int anniVotazione = Integer.parseInt(Configuration
				.getProperty("anniVotazionePrecedenti"));
		annoItems = new ArrayList<SelectItem>();

		for (int i = 0; i <= anniVotazione; i++) {
			annoItems.add(new SelectItem(Integer.valueOf(annoInCorso - i),
					Integer.toString(annoInCorso - i)));
			if (i == 1) {
				// come default mettiamo l'anno precedente
				annoDa = annoInCorso - i;
			}
		}

	}

	public void confermaOnClick(ActionEvent e)
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {
		visibleAnagrafiche = false;
		nrAnagrafiche = 0;
		note = "";

		anags = RicercaAnagraficaBD.elencoFazzoletti(annoDa);
		visibleAnagrafiche = !anags.isEmpty();

		if (!visibleAnagrafiche) {
			note = LoadBundleLanguage.getMessage("anagraficheNonPresenti");
		} else {
			nrAnagrafiche = anags.size();
		}

	}

}
