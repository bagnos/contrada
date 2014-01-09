package it.contrada.backingbeans.pagecodes;

import it.contrada.businessdelegate.RicercaAnagraficaBD;
import it.contrada.dto.AnagraficaDTO;
import it.contrada.exceptions.ContradaExceptionBloccante;
import it.contrada.exceptions.ContradaExceptionNonBloccante;

import java.util.List;

public class ElencaIndirizzoPostale {

	private List<AnagraficaDTO> anagrafiche;
	private int nrAnagrafiche;

	public int getNrAnagrafiche() {
		if (anagrafiche != null && !(anagrafiche.isEmpty())) {
			nrAnagrafiche = anagrafiche.size();
		} else {
			nrAnagrafiche = 0;
		}
		return nrAnagrafiche;
	}

	public List<AnagraficaDTO> getAnagrafiche() {
		return anagrafiche;
	}

	public ElencaIndirizzoPostale() throws ContradaExceptionBloccante,
			ContradaExceptionNonBloccante {
		// TODO Auto-generated constructor stub
		anagrafiche = RicercaAnagraficaBD.elencaIndirizzoAnagraficaProncipale();
	}

}
