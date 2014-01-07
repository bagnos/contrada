package it.contrada.preautrid.dto;

import java.io.Serializable;

public class RidEsitoPreautDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7000272858734153791L;
	private int idRid;
	private java.util.Date dtEsito;
	private int cdEsito;
	private String dsEsito;
	private String nomeFile;
	private String iban;
	
	
	
	public String getIban() {
		return iban;
	}
	public void setIban(String iban) {
		this.iban = iban;
	}
	public String getNomeFile() {
		return nomeFile;
	}
	public void setNomeFile(String nomeFile) {
		this.nomeFile = nomeFile;
	}
	public int getIdRid() {
		return idRid;
	}
	public void setIdRid(int idRid) {
		this.idRid = idRid;
	}
	public java.util.Date getDtEsito() {
		return dtEsito;
	}
	public void setDtEsito(java.util.Date dtEsito) {
		this.dtEsito = dtEsito;
	}
	public int getCdEsito() {
		return cdEsito;
	}
	public void setCdEsito(int cdEsito) {
		this.cdEsito = cdEsito;
	}
	public String getDsEsito() {
		return dsEsito;
	}
	public void setDsEsito(String dsEsito) {
		this.dsEsito = dsEsito;
	}
	
	
}
