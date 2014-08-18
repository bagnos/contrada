package it.contrada.incassorid.dto;

import java.io.Serializable;

public class Record17DTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */

	
	private String tipoSequenza;
	private String dtSottoscrizione;
	private String tipoRecord = "17";
	private String numeroProgressivo;
	private String cdIban;

	public String getCdIban() {
		return cdIban;
	}

	public void setCdIban(String cdIban) {
		this.cdIban = cdIban;
	}

	public String getNumeroProgressivo() {
		return numeroProgressivo;
	}

	public void setNumeroProgressivo(String numeroProgressivo) {
		this.numeroProgressivo = numeroProgressivo;
	}

	
	

	public String getTipoSequenza() {
		return tipoSequenza;
	}

	public void setTipoSequenza(String tipoSequenza) {
		this.tipoSequenza = tipoSequenza;
	}

	public String getDtSottoscrizione() {
		return dtSottoscrizione;
	}

	public void setDtSottoscrizione(String dtSottoscrizione) {
		this.dtSottoscrizione = dtSottoscrizione;
	}

	@Override
	public String toString() {
		StringBuilder rec17 = new StringBuilder();
		rec17.append(" ");		
		rec17.append(String.format("%2s", tipoRecord));
		rec17.append(String.format("%7s", numeroProgressivo).replaceAll(" ",
				"0"));
		rec17.append(String.format("%27s", cdIban));
		rec17.append(String.format("%3s", tipoSequenza));
		rec17.append(String.format("%6s", dtSottoscrizione)
				.replaceAll(" ", "0"));

		return rec17.toString();
	}
}
