package it.contrada.dto;

import java.io.Serializable;

public class TesseraStampataDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8083474762256218759L;
	private long idAnagrafica;
	private long idTessera;
	private int idFamiglia;
	private String cognome;
	private String nome;
	private java.sql.Date dtNascita;
	private String indirizzo;
	private String capProvincia;
	private String note;
	private int quota;
	private String carica;
	private String mail;
	private String cell;
	private String fisso;
	private String esattore;
	private String dsPagamento;
	private String recapito;
	private String intestatario;
	private String dsTipoTessera;
	private int anno;
	private String stato;
	
	

	public String getStato() {
		return stato;
	}

	public void setStato(String stato) {
		this.stato = stato;
	}

	public int getAnno() {
		return anno;
	}

	public void setAnno(int anno) {
		this.anno = anno;
	}

	public String getDsTipoTessera() {
		return dsTipoTessera;
	}

	public void setDsTipoTessera(String dsTipoTessera) {
		this.dsTipoTessera = dsTipoTessera;
	}

	public String getIntestatario() {
		return intestatario;
	}

	public void setIntestatario(String intestatario) {
		this.intestatario = intestatario;
	}

	public String getRecapito() {
		return recapito;
	}

	public void setRecapito(String recapito) {
		this.recapito = recapito;
	}

	public String getDsPagamento() {
		return dsPagamento;
	}

	public void setDsPagamento(String dsPagamento) {
		this.dsPagamento = dsPagamento;
	}

	public String getEsattore() {
		return esattore;
	}

	public void setEsattore(String esattore) {
		this.esattore = esattore;
	}

	public long getIdAnagrafica() {
		return idAnagrafica;
	}

	public void setIdAnagrafica(long idAnagrafica) {
		this.idAnagrafica = idAnagrafica;
	}

	public long getIdTessera() {
		return idTessera;
	}

	public void setIdTessera(long idTessera) {
		this.idTessera = idTessera;
	}

	public int getIdFamiglia() {
		return idFamiglia;
	}

	public void setIdFamiglia(int idFamiglia) {
		this.idFamiglia = idFamiglia;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public java.sql.Date getDtNascita() {
		return dtNascita;
	}

	public void setDtNascita(java.sql.Date dtNascita) {
		this.dtNascita = dtNascita;
	}

	public String getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	public String getCapProvincia() {
		return capProvincia;
	}

	public void setCapProvincia(String capProvincia) {
		this.capProvincia = capProvincia;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public int getQuota() {
		return quota;
	}

	public void setQuota(int quota) {
		this.quota = quota;
	}

	public String getCarica() {
		return carica;
	}

	public void setCarica(String carica) {
		this.carica = carica;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getCell() {
		return cell;
	}

	public void setCell(String cell) {
		this.cell = cell;
	}

	public String getFisso() {
		return fisso;
	}

	public void setFisso(String fisso) {
		this.fisso = fisso;
	}

}
