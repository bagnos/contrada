package it.contrada.dto;

import java.io.Serializable;

public class MembroFamigliaDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6374146607454312191L;
	private int idFamiglia;
	private String nome;
	private String cognome;
	private boolean capoFamiglia;
	private int idAnag;
	private String dsCapoFamiglia;
	private String nominativo;
	private java.util.Date dtNascita;	
	private String nrCivico;
	private int idStrada;
	private String dsStrada;		
	private String cdCap;
	private int cdProvincia;
	private int cdComune;
	private Integer idLocalita;
	private String cdIsoStato;
	

	public String getCdIsoStato() {
		return cdIsoStato;
	}

	public void setCdIsoStato(String cdIsoStato) {
		this.cdIsoStato = cdIsoStato;
	}

	public int getIdStrada() {
		return idStrada;
	}

	public void setIdStrada(int idStrada) {
		this.idStrada = idStrada;
	}

	public String getDsStrada() {
		return dsStrada;
	}

	public void setDsStrada(String dsStrada) {
		this.dsStrada = dsStrada;
	}

	public String getCdCap() {
		return cdCap;
	}

	public void setCdCap(String cdCap) {
		this.cdCap = cdCap;
	}

	public int getCdProvincia() {
		return cdProvincia;
	}

	public void setCdProvincia(int cdProvincia) {
		this.cdProvincia = cdProvincia;
	}

	public int getCdComune() {
		return cdComune;
	}

	public void setCdComune(int cdComune) {
		this.cdComune = cdComune;
	}

	public Integer getIdLocalita() {
		return idLocalita;
	}

	public void setIdLocalita(Integer idLocalita) {
		this.idLocalita = idLocalita;
	}

	public String getNrCivico() {
		return nrCivico;
	}

	public void setNrCivico(String nrCivico) {
		this.nrCivico = nrCivico;
	}

	

	public java.util.Date getDtNascita() {
		return dtNascita;
	}

	public void setDtNascita(java.util.Date dtNascita) {
		this.dtNascita = dtNascita;
	}

	public String getNominativo() {
		return nominativo;
	}

	public void setNominativo(String nominativo) {
		this.nominativo = nominativo;
	}

	public String getDsCapoFamiglia() {
		return dsCapoFamiglia;
	}

	public void setDsCapoFamiglia(String dsCapoFamiglia) {
		this.dsCapoFamiglia = dsCapoFamiglia;
	}

	public int getIdAnag() {
		return idAnag;
	}

	public void setIdAnag(int idAnag) {
		this.idAnag = idAnag;
	}

	public boolean isCapoFamiglia() {
		return capoFamiglia;
	}

	public void setCapoFamiglia(boolean capoFamiglia) {
		this.capoFamiglia = capoFamiglia;
	}

	public int getIdFamiglia() {
		return idFamiglia;
	}

	public void setIdFamiglia(int idFamiglia) {
		this.idFamiglia = idFamiglia;
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

}
