package it.contrada.incassorid.dto;

import java.io.Serializable;

public class Record50DTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3387595782268584330L;
	private String tipoRecord = "50";
	private int numeroProgressivo;
	private String segmento1;
	private String segmento2;
	private String segmento;

	public String getSegmento() {
		segmento += segmento1 != null ? segmento1 : "";
		segmento += segmento2 != null ? segmento2 : "";
		segmento = segmento.trim();
		return segmento;
	}

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

	@Override
	public String toString() {
		StringBuilder rec50 = new StringBuilder();
		rec50.append(" ");
		rec50.append(tipoRecord);
		rec50.append(String.format("%7s", numeroProgressivo).replaceAll(" ",
				"0"));
		rec50.append(String.format("%-110s", segmento1.toUpperCase()));

		return rec50.toString();
	}
}
