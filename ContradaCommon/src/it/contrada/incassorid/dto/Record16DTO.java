package it.contrada.incassorid.dto;

import java.io.Serializable;

public class Record16DTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7839539204917193045L;
	private String tipoRecord = "16";
	private String numeroProgressivo;
	private String idCreditore;
	private String cdIbanOrdinante;
	

	public String getCdIbanOrdinante() {
		return cdIbanOrdinante;
	}

	public void setCdIbanOrdinante(String cdIbanOrdinante) {
		this.cdIbanOrdinante = cdIbanOrdinante;
	}

	public String getIdCreditore() {
		return idCreditore;
	}

	public void setIdCreditore(String idCreditore) {
		this.idCreditore = idCreditore;
	}

	

	public String getTipoRecord() {
		return tipoRecord;
	}

	public void setTipoRecord(String tipoRecord) {
		this.tipoRecord = tipoRecord;
	}

	public String getNumeroProgressivo() {
		return numeroProgressivo;
	}

	public void setNumeroProgressivo(String numeroProgressivo) {
		this.numeroProgressivo = numeroProgressivo;
	}

	
	@Override
	public String toString() {
		StringBuilder rec16 = new StringBuilder();
		rec16.append(" ");
		rec16.append(String.format("%2s", tipoRecord));
		rec16.append(String.format("%7s", numeroProgressivo).replaceAll(" ",
				"0"));
		rec16.append(String.format("%-34s", cdIbanOrdinante));
		rec16.append(String.format("%-25s", idCreditore));
		
		
		return rec16.toString();

	}
}
