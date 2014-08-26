package it.contrada.incassorid.dto;

import java.io.Serializable;

public class Record40DTO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5555275471468318954L;
	private String tipoRecord = "40";
	private int numeroProgressivo;
	private String indirizzo;
	private String cap;
	private String comuneProv;

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

	public String getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	public String getCap() {
		return cap;
	}

	public void setCap(String cap) {
		this.cap = cap;
	}

	public String getComuneProv() {
		return comuneProv;
	}

	public void setComuneProv(String comuneProv) {
		this.comuneProv = comuneProv;
	}

	@Override
	public String toString() {
		StringBuilder rec40 = new StringBuilder();
		rec40.append(" ");
		rec40.append(tipoRecord);		
		rec40.append(String.format("%7s", numeroProgressivo).replaceAll(" ",
				"0"));
		rec40.append(String.format("%-30s", indirizzo.toUpperCase()));
		rec40.append(String.format("%5s", cap.toUpperCase()));
		rec40.append(String.format("%-25s", comuneProv.toUpperCase()));
		//rec40.append(String.format("%66s", ""));
		

		return rec40.toString();
	}

}
