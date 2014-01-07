package it.contrada.preautrid.dto;

import java.io.Serializable;

public class Record30DTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7447851426066611446L;
	private String numeroProgressivo;
	private String segmento1;
	private String segmento2;
	private String segmento3;
	private String cdFiscaleSottoscrittore;
	private String cdFiscaleIntestatario;
	private String tipoRecord = "30";

	public String getNumeroProgressivo() {
		return numeroProgressivo;
	}

	public void setNumeroProgressivo(String numeroProgressivo) {
		this.numeroProgressivo = numeroProgressivo;
	}

	public String getSegmento1() {
		return segmento1;
	}

	public void setSegmento1(String segmento1) {
		this.segmento1 = segmento1;
	}

	public String getSegmento2() {
		return segmento2;
	}

	public void setSegmento2(String segmento2) {
		this.segmento2 = segmento2;
	}

	public String getSegmento3() {
		return segmento3;
	}

	public void setSegmento3(String segmento3) {
		this.segmento3 = segmento3;
	}

	public String getCdFiscaleSottoscrittore() {
		return cdFiscaleSottoscrittore;
	}

	public void setCdFiscaleSottoscrittore(String cdFiscaleSottoscrittore) {
		this.cdFiscaleSottoscrittore = cdFiscaleSottoscrittore;
	}

	public String getCdFiscaleIntestatario() {
		return cdFiscaleIntestatario;
	}

	public void setCdFiscaleIntestatario(String cdFiscaleIntestatario) {
		this.cdFiscaleIntestatario = cdFiscaleIntestatario;
	}

	@Override
	public String toString() {
		StringBuilder rec30 = new StringBuilder();
		rec30.append(" ");
		rec30.append(tipoRecord);
		rec30.append(String.format("%7s", numeroProgressivo).replaceAll(" ",
				"0"));
		
		rec30.append(String.format("%-30s", segmento1.toUpperCase()));
		rec30.append(String.format("%-30s", segmento2.toUpperCase()));
		rec30.append(String.format("%-14s", segmento3.toUpperCase()));
		rec30.append(String.format("%16s", cdFiscaleIntestatario.toUpperCase()));
		rec30.append(String.format("%16s", cdFiscaleSottoscrittore.toString()));		
		rec30.append(String.format("%4s", " "));
		return rec30.toString();

	}

}
