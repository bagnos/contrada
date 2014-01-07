package it.contrada.reversecooking.dto;

import java.io.Serializable;

public class UnitaMisuraDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int idUnita;
	private String txUnita;
	public int getIdUnita() {
		return idUnita;
	}
	public void setIdUnita(int idUnita) {
		this.idUnita = idUnita;
	}
	public String getTxUnita() {
		return txUnita;
	}
	public void setTxUnita(String txUnita) {
		this.txUnita = txUnita;
	}

	

}
