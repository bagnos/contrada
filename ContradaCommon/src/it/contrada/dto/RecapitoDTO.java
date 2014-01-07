package it.contrada.dto;

import java.io.Serializable;

public class RecapitoDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7139894784448131310L;
	private String txMail;
	private String txCell;
	private String txFisso;
	
	public String getTxMail() {
		return txMail;
	}
	public void setTxMail(String txMail) {
		this.txMail = txMail;
	}
	public String getTxCell() {
		return txCell;
	}
	public void setTxCell(String txCell) {
		this.txCell = txCell;
	}
	public String getTxFisso() {
		return txFisso;
	}
	public void setTxFisso(String txFisso) {
		this.txFisso = txFisso;
	}
	
	

}
