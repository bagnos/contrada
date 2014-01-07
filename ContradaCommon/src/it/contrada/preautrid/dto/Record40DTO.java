package it.contrada.preautrid.dto;

import java.io.Serializable;

public class Record40DTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4622784985427969856L;
	private String numeroProgressivo;
	private String intestetarioConto;
	private String cap;
	private String localita;
	private String ragioneSociale;
	private String indirizzo;
	private String tipoRecord = "40";

	public String getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	public String getNumeroProgressivo() {
		return numeroProgressivo;
	}

	public void setNumeroProgressivo(String numeroProgressivo) {
		this.numeroProgressivo = numeroProgressivo;
	}

	public String getIntestetarioConto() {
		return intestetarioConto;
	}

	public void setIntestetarioConto(String intestetarioConto) {
		this.intestetarioConto = intestetarioConto;
	}

	public String getCap() {
		return cap;
	}

	public void setCap(String cap) {
		this.cap = cap;
	}

	public String getLocalita() {
		return localita;
	}

	public void setLocalita(String localita) {
		this.localita = localita;
	}

	public String getRagioneSociale() {
		return ragioneSociale;
	}

	public void setRagioneSociale(String ragioneSociale) {
		this.ragioneSociale = ragioneSociale;
	}

	@Override
	public String toString() {
		StringBuilder rec40 = new StringBuilder();
		rec40.append(" ");
		rec40.append(tipoRecord);
		rec40.append(String.format("%7s", numeroProgressivo).replaceAll(" ",
				"0"));
		rec40.append(String.format("%-30s", indirizzo.toUpperCase()));
		rec40.append(String.format("%5s", cap));
		rec40.append(String.format("%-25s", localita.toUpperCase()));
		rec40.append(String.format("%-50s", intestetarioConto.toUpperCase()));

		return rec40.toString();
	}
}
