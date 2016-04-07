package it.contrada.backingbeans.model;

import it.contrada.dto.AnagraficaDTO;

import java.util.List;

public class RicercaAnagraficheBean {
	private List<AnagraficaDTO> anagrafiche;

	public List<AnagraficaDTO> getAnagrafiche() {
		return anagrafiche;
	}

	public void setAnagrafiche(List<AnagraficaDTO> anagrafiche) {
		this.anagrafiche = anagrafiche;
	}
}
