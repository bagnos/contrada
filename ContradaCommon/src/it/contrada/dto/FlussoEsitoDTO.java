package it.contrada.dto;

import java.io.Serializable;

public class FlussoEsitoDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2065753828971563448L;
	private int idFlussoEsito;
	private java.sql.Date dtDa;
	private java.sql.Date dtA;
	private String txNomeFile;
	private int tipoFlusso;
	private java.sql.Timestamp tsRicevuto;
	private int nrDisp;
	private Long imFlusso;
	
	
	
	
	public Long getImFlusso() {
		return imFlusso;
	}
	public void setImFlusso(Long imFlusso) {
		this.imFlusso = imFlusso;
	}
	public int getNrDisp() {
		return nrDisp;
	}
	public void setNrDisp(int nrDisp) {
		this.nrDisp = nrDisp;
	}
	public java.sql.Timestamp getTsRicevuto() {
		return tsRicevuto;
	}
	public void setTsRicevuto(java.sql.Timestamp tsRicevuto) {
		this.tsRicevuto = tsRicevuto;
	}
	public int getTipoFlusso() {
		return tipoFlusso;
	}
	public void setTipoFlusso(int tipoFlusso) {
		this.tipoFlusso = tipoFlusso;
	}
	public String getTxNomeFile() {
		return txNomeFile;
	}
	public void setTxNomeFile(String txNomeFile) {
		this.txNomeFile = txNomeFile;
	}
	public int getIdFlussoEsito() {
		return idFlussoEsito;
	}
	public void setIdFlussoEsito(int idFlussoEsito) {
		this.idFlussoEsito = idFlussoEsito;
	}
	public java.sql.Date getDtDa() {
		return dtDa;
	}
	public void setDtDa(java.sql.Date dtDa) {
		this.dtDa = dtDa;
	}
	public java.sql.Date getDtA() {
		return dtA;
	}
	public void setDtA(java.sql.Date dtA) {
		this.dtA = dtA;
	}
	
	
}
