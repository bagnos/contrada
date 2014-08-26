package it.contrada.preautrid.dto;

import java.io.Serializable;

public class Record70DTO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1607319780521300470L;
	private String numeroProgressivo;
	private String codiceRiferimento;
	private String carattereSpeciale;
	private String numeroRate;
	private String importoMassimoRata;
	private String scadenzaPrimaRata;
	private String scadenzaUltimaRata;
	private String descrizione;
	private String facoltaStorno;
	private String codiceDivisa;
	private String tipoRecord = "70";

	public String getNumeroProgressivo() {
		return numeroProgressivo;
	}

	public void setNumeroProgressivo(String numeroProgressivo) {
		this.numeroProgressivo = numeroProgressivo;
	}

	public String getCodiceRiferimento() {
		return codiceRiferimento;
	}

	public void setCodiceRiferimento(String codiceRiferimento) {
		this.codiceRiferimento = codiceRiferimento;
	}

	public String getCarattereSpeciale() {
		return carattereSpeciale;
	}

	public void setCarattereSpeciale(String carattereSpeciale) {
		this.carattereSpeciale = carattereSpeciale;
	}

	public String getNumeroRate() {
		return numeroRate;
	}

	public void setNumeroRate(String numeroRate) {
		this.numeroRate = numeroRate;
	}

	public String getImportoMassimoRata() {
		return importoMassimoRata;
	}

	public void setImportoMassimoRata(String importoMassimoRata) {
		this.importoMassimoRata = importoMassimoRata;
	}

	public String getScadenzaPrimaRata() {
		return scadenzaPrimaRata;
	}

	public void setScadenzaPrimaRata(String scadenzaPrimaRata) {
		this.scadenzaPrimaRata = scadenzaPrimaRata;
	}

	public String getScadenzaUltimaRata() {
		return scadenzaUltimaRata;
	}

	public void setScadenzaUltimaRata(String scadenzaUltimaRata) {
		this.scadenzaUltimaRata = scadenzaUltimaRata;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getFacoltaStorno() {
		return facoltaStorno;
	}

	public void setFacoltaStorno(String facoltaStorno) {
		this.facoltaStorno = facoltaStorno;
	}

	public String getCodiceDivisa() {
		return codiceDivisa;
	}

	public void setCodiceDivisa(String codiceDivisa) {
		this.codiceDivisa = codiceDivisa;
	}

	@Override
	public String toString() {
		StringBuilder rec70 = new StringBuilder();
		rec70.append(" ");
		rec70.append(tipoRecord);
		rec70.append(String.format("%7s", numeroProgressivo).replaceAll(" ",
				"0"));
		rec70.append(String.format("%15s", codiceRiferimento).replaceAll(" ",
				"0"));
		rec70.append(carattereSpeciale);
		rec70.append(String.format("%4s", numeroRate).replaceAll(" ", "0"));
		rec70.append(String.format("%9s", importoMassimoRata).replaceAll(" ",
				"0"));
		rec70.append(String.format("%6s", scadenzaPrimaRata).replaceAll(" ",
				"0"));
		rec70.append(String.format("%6s", scadenzaUltimaRata).replaceAll(" ",
				"0"));
		rec70.append(String.format("%44s", ""));
		rec70.append(facoltaStorno);
		rec70.append(codiceDivisa);
		rec70.append(String.format("%23s", ""));

		return rec70.toString();
	}
	
	public String toStringRichiestaSeda() {
		StringBuilder rec70 = new StringBuilder();
		rec70.append(" ");
		rec70.append(tipoRecord);
		rec70.append(String.format("%7s", numeroProgressivo).replaceAll(" ",
				"0"));
		rec70.append(String.format("%15s", codiceRiferimento).replaceAll(" ",
				"0"));		
		//rec70.append(String.format("%-96s", ""));

		return rec70.toString();
	}

}
