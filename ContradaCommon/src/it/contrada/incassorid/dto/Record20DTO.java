package it.contrada.incassorid.dto;

import java.io.Serializable;

public class Record20DTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1199146491688814302L;
	private String tipoRecord = "20";
	private int numeroProgressivo;
	private String segmento1;
	private String segmento2;
	private String segmento3;
	private String segmento;
	

	public String getSegmento() {
		segmento+=segmento1!=null?segmento1:"";
		segmento+=segmento2!=null?segmento2:"";
		segmento+=segmento3!=null?segmento3:"";
		segmento=segmento.trim();
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

	public String getSegmento3() {
		return segmento3;
	}

	public void setSegmento3(String segmento3) {
		this.segmento3 = segmento3;
	}

	@Override
	public String toString() {
		StringBuilder rec20 = new StringBuilder();
		rec20.append(" ");
		rec20.append(tipoRecord);
		rec20.append(String.format("%7s", numeroProgressivo).replaceAll(" ",
				"0"));
		rec20.append(String.format("%-30s", segmento1.toUpperCase()));
		rec20.append(String.format("%-30s", segmento2.toUpperCase()));
		rec20.append(String.format("%-30s", segmento3.toUpperCase()));
		rec20.append(String.format("%20s", ""));
		return rec20.toString();

	}

}
