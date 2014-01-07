package it.contrada.reversecooking.dto;

import java.io.Serializable;

public class PortataDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int idPortata;
	private String txPortata;

	public int getIdPortata() {
		return idPortata;
	}

	public void setIdPortata(int idPortata) {
		this.idPortata = idPortata;
	}

	public String getTxPortata() {
		return txPortata;
	}

	public void setTxPortata(String txPortata) {
		this.txPortata = txPortata;
	}

}
