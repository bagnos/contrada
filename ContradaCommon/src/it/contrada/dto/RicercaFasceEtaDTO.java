package it.contrada.dto;

import java.io.Serializable;

public class RicercaFasceEtaDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8396970050053148240L;
	private Integer eta;
	private Integer mese;
	private String sesso;
	private java.sql.Date dtDa;
	private java.sql.Date dtA;
	public Integer getEta() {
		return eta;
	}
	public void setEta(Integer eta) {
		this.eta = eta;
	}
	public Integer getMese() {
		return mese;
	}
	public void setMese(Integer mese) {
		this.mese = mese;
	}
	public String getSesso() {
		return sesso;
	}
	public void setSesso(String sesso) {
		this.sesso = sesso;
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
