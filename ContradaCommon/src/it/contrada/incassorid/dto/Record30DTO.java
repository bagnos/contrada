package it.contrada.incassorid.dto;

import java.io.Serializable;

public class Record30DTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -9165570203834476380L;
	private String tipoRecord = "30";
	private int numeroProgressivo;
	private String segmento1;
	private String segmento2;
	private String segmento3;

	public String getTipoRecord() {
		return tipoRecord;
	}

	public void setTipoRecord(String tipoRecord) {
		this.tipoRecord = tipoRecord;
	}

	public int getNumeroProgressivo() {
		return numeroProgressivo;
	}

	public void setNumeroProgressivo(int numeroProgressivo) {
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

	@Override
	public String toString() {
		StringBuilder rec30 = new StringBuilder();
		rec30.append(" ");
		rec30.append(tipoRecord);
		rec30.append(String.format("%7s", numeroProgressivo).replaceAll(" ",
				"0"));
		if (segmento1 == null) {
			segmento1 = "";
		}
		rec30.append(String.format("%-30s", segmento1.toUpperCase()));
		if (segmento2 == null) {
			segmento2 = "";
		}
		rec30.append(String.format("%-30s", segmento2.toUpperCase()));
		rec30.append(String.format("%30s", " "));
		rec30.append(String.format("%16s", " "));
		rec30.append(String.format("%4s", " "));
		return rec30.toString();

	}

}
