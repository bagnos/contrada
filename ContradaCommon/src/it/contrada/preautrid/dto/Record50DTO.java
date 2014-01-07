package it.contrada.preautrid.dto;

import java.io.Serializable;

public class Record50DTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5163874457072322669L;
	private String numeroProgressivo; 
	private String riferimentiOriginari;
	
	public String getNumeroProgressivo() {
		return numeroProgressivo;
	}
	public void setNumeroProgressivo(String numeroProgressivo) {
		this.numeroProgressivo = numeroProgressivo;
	}
	public String getRiferimentiOriginari() {
		return riferimentiOriginari;
	}
	public void setRiferimentiOriginari(String riferimentiOriginari) {
		this.riferimentiOriginari = riferimentiOriginari;
	}
	
	
	
}
