package it.contrada.poste;

import java.io.Serializable;

public class RicezioneFlussoIncassoPostaDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8553251467389284014L;
	private long idFlussoEsito;
	private String nomeFile;
	private java.sql.Date dtEsitoDa;
	private java.sql.Date dtEsitoA;
	private int idTipoFlusso;
	private int nrDisp;
	private int impFlusso;
	public long getIdFlussoEsito() {
		return idFlussoEsito;
	}
	public void setIdFlussoEsito(long idFlussoEsito) {
		this.idFlussoEsito = idFlussoEsito;
	}
	public String getNomeFile() {
		return nomeFile;
	}
	public void setNomeFile(String nomeFile) {
		this.nomeFile = nomeFile;
	}
	public java.sql.Date getDtEsitoDa() {
		return dtEsitoDa;
	}
	public void setDtEsitoDa(java.sql.Date dtEsitoDa) {
		this.dtEsitoDa = dtEsitoDa;
	}
	public java.sql.Date getDtEsitoA() {
		return dtEsitoA;
	}
	public void setDtEsitoA(java.sql.Date dtEsitoA) {
		this.dtEsitoA = dtEsitoA;
	}
	public int getIdTipoFlusso() {
		return idTipoFlusso;
	}
	public void setIdTipoFlusso(int idTipoFlusso) {
		this.idTipoFlusso = idTipoFlusso;
	}
	public int getNrDisp() {
		return nrDisp;
	}
	public void setNrDisp(int nrDisp) {
		this.nrDisp = nrDisp;
	}
	public int getImpFlusso() {
		return impFlusso;
	}
	public void setImpFlusso(int impFlusso) {
		this.impFlusso = impFlusso;
	}
	
	

}
