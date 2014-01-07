package it.contrada.reversecooking.dto;

import java.io.Serializable;

public class CategoriaDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5139696044504753958L;

	private String nomeImmagine;
	private int idCategoria;
	private String descrizione;

	public String getNomeImmagine() {
		return nomeImmagine;
	}

	public void setNomeImmagine(String nomeImmagine) {
		this.nomeImmagine = nomeImmagine;
	}

	public int getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(int idCategoria) {
		this.idCategoria = idCategoria;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

}
