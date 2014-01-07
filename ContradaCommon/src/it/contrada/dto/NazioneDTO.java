package it.contrada.dto;

import java.io.Serializable;

public class NazioneDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3323599184772091038L;
	private String cdNazione;
	private String dsNazione;
	public String getCdNazione() {
		return cdNazione;
	}
	public void setCdNazione(String cdNazione) {
		this.cdNazione = cdNazione;
	}
	public String getDsNazione() {
		return dsNazione;
	}
	public void setDsNazione(String dsNazione) {
		this.dsNazione = dsNazione;
	}
}
