package it.contrada.dto;

import java.io.Serializable;

public class Contradaiolo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2096835129215307001L;
	private int idAnagrafica;
	private String cdFiscale;
	private int idFamiglia;
	private String Nome;
	private String cognome;
	private String sesso;

	public int getIdAnagrafica() {
		return idAnagrafica;
	}

	public void setIdAnagrafica(int idAnagrafica) {
		this.idAnagrafica = idAnagrafica;
	}

	public String getCdFiscale() {
		return cdFiscale;
	}

	public void setCdFiscale(String cdFiscale) {
		this.cdFiscale = cdFiscale;
	}

	public int getIdFamiglia() {
		return idFamiglia;
	}

	public void setIdFamiglia(int idFamiglia) {
		this.idFamiglia = idFamiglia;
	}

	public String getNome() {
		return Nome;
	}

	public void setNome(String nome) {
		Nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getSesso() {
		return sesso;
	}

	public void setSesso(String sesso) {
		this.sesso = sesso;
	}

}
