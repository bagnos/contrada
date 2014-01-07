package it.contrada.reversecooking.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RicettaDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8419733853914224017L;
	private int idRicetta;
	private String categoria;
	private Integer nrCalorie;
	private Integer nrMinutiCottura;
	private Integer nrPersone;
	private String titolo;
	private String difficolta;
	private Integer nrMinutiPreparazione;
	private int nrMachIngredienti;

	private String nomeImmagine;
	private int nrIngredientiCercati;
	private String portata;
	private List<IngredienteDTO> ingredienti;
	private String elencoIngredienti;
	private String preparazione;

	private Integer idPortata;
	private Integer idCategoria;
	private Integer idDifficolta;

	public Integer getIdPortata() {
		return idPortata;
	}

	public void setIdPortata(Integer idPortata) {
		this.idPortata = idPortata;
	}

	public Integer getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(Integer idCategoria) {
		this.idCategoria = idCategoria;
	}

	public Integer getIdDifficolta() {
		return idDifficolta;
	}

	public void setIdDifficolta(Integer idDifficolta) {
		this.idDifficolta = idDifficolta;
	}

	public String getElencoIngredienti() {

		return elencoIngredienti;
	}

	public void setElencoIngredienti(String elencoIngredienti) {
		this.elencoIngredienti = elencoIngredienti;
	}

	public String getPreparazione() {
		return preparazione;
	}

	public void setPreparazione(String preparazione) {
		this.preparazione = preparazione;
	}

	public List<IngredienteDTO> getIngredienti() {
		if (ingredienti == null) {
			ingredienti = new ArrayList<IngredienteDTO>();
		}
		return ingredienti;
	}

	public void setIngredienti(List<IngredienteDTO> ingredienti) {
		this.ingredienti = ingredienti;
	}

	public String getPortata() {
		return portata;
	}

	public void setPortata(String portata) {
		this.portata = portata;
	}

	public int getNrIngredientiCercati() {
		return nrIngredientiCercati;
	}

	public void setNrIngredientiCercati(int nrIngredientiCercati) {
		this.nrIngredientiCercati = nrIngredientiCercati;
	}

	public String getNomeImmagine() {
		return nomeImmagine;
	}

	public void setNomeImmagine(String nomeImmagine) {
		this.nomeImmagine = nomeImmagine;
	}

	public int getIdRicetta() {
		return idRicetta;
	}

	public void setIdRicetta(int idRicetta) {
		this.idRicetta = idRicetta;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public Integer getNrCalorie() {
		return nrCalorie;
	}

	public void setNrCalorie(Integer nrCalorie) {
		this.nrCalorie = nrCalorie;
	}

	public Integer getNrMinutiCottura() {
		return nrMinutiCottura;
	}

	public void setNrMinutiCottura(Integer nrMinutiCottura) {
		this.nrMinutiCottura = nrMinutiCottura;
	}

	public Integer getNrPersone() {
		return nrPersone;
	}

	public void setNrPersone(Integer nrPersone) {
		this.nrPersone = nrPersone;
	}

	public String getTitolo() {
		return titolo;
	}

	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	public String getDifficolta() {
		return difficolta;
	}

	public void setDifficolta(String difficolta) {
		this.difficolta = difficolta;
	}

	public Integer getNrMinutiPreparazione() {
		return nrMinutiPreparazione;
	}

	public void setNrMinutiPreparazione(Integer nrMinutiPreparazione) {
		this.nrMinutiPreparazione = nrMinutiPreparazione;
	}

	public int getNrMachIngredienti() {
		return nrMachIngredienti;
	}

	public void setNrMachIngredienti(int nrMachIngredienti) {
		this.nrMachIngredienti = nrMachIngredienti;
	}

}
