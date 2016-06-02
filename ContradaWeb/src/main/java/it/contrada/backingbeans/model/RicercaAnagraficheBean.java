package it.contrada.backingbeans.model;

import it.contrada.businessdelegate.RicercaAnagraficaBD;
import it.contrada.dto.AnagraficaDTO;
import it.contrada.exceptions.ContradaExceptionBloccante;
import it.contrada.exceptions.ContradaExceptionNonBloccante;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class RicercaAnagraficheBean {
	private List<AnagraficaDTO> anagrafiche;
	private int nrAnag;

	private int nrFam;
	private String nome;
	private String cognome;

	public int getNrAnag() {
		return nrAnag;
	}

	public void setNrAnag(int nrAnag) {
		this.nrAnag = nrAnag;
	}

	public int getNrFam() {
		return nrFam;
	}

	public void setNrFam(int nrFam) {
		this.nrFam = nrFam;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public List<AnagraficaDTO> getAnagrafiche() {
		return anagrafiche;
	}

	public void setAnagrafiche(List<AnagraficaDTO> anagrafiche) {
		this.anagrafiche = anagrafiche;
	}

	public List<AnagraficaDTO> ricercaAnagrafiche() throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {

		List<AnagraficaDTO> anagrafiche = new ArrayList<AnagraficaDTO>();
		if (getNrAnag() != 0) {

			AnagraficaDTO anag = RicercaAnagraficaBD
					.ricercaAnagrafica(getNrAnag());
			if (anag != null) {
				anagrafiche.add(anag);
			}

		} else if (getNrFam() != 0) {
			anagrafiche = RicercaAnagraficaBD
					.ricercaAnagraficaPerFamiglia(getNrFam());
		} else if ((getCognome() != null && getCognome().trim() != "")
				&& (getNome() != null && getNome().trim() != "")) {
			anagrafiche = RicercaAnagraficaBD.ricercaAnagraficaPerCognomeNome(
					getCognome().trim(), getNome().trim());

		} else if (getCognome() != null && !getCognome().isEmpty()) {

			anagrafiche = RicercaAnagraficaBD
					.ricercaAnagraficaPerCognome(getCognome().trim());
		} 
		
		return anagrafiche;

	}
}
