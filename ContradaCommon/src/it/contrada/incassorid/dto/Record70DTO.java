package it.contrada.incassorid.dto;

import java.io.Serializable;

public class Record70DTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 142213163982519777L;
	private String tipoRecord="70";
	private int numeroProgressivo;
	private String codiceRiferimento;
	private String causaleStorno;
	private String CampoDisposizione;
	private String facoltaStornoAddebito;
	private String CampoDisposizione1;
	private String chiaviControllo1;
	private String chiaviControllo2;
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
	public String getCodiceRiferimento() {
		return codiceRiferimento;
	}
	public void setCodiceRiferimento(String codiceRiferimento) {
		this.codiceRiferimento = codiceRiferimento;
	}
	public String getCausaleStorno() {
		return causaleStorno;
	}
	public void setCausaleStorno(String causaleStorno) {
		this.causaleStorno = causaleStorno;
	}
	public String getCampoDisposizione() {
		return CampoDisposizione;
	}
	public void setCampoDisposizione(String campoDisposizione) {
		CampoDisposizione = campoDisposizione;
	}
	public String getFacoltaStornoAddebito() {
		return facoltaStornoAddebito;
	}
	public void setFacoltaStornoAddebito(String facoltaStornoAddebito) {
		this.facoltaStornoAddebito = facoltaStornoAddebito;
	}
	public String getCampoDisposizione1() {
		return CampoDisposizione1;
	}
	public void setCampoDisposizione1(String campoDisposizione1) {
		CampoDisposizione1 = campoDisposizione1;
	}
	public String getChiaviControllo1() {
		return chiaviControllo1;
	}
	public void setChiaviControllo1(String chiaviControllo1) {
		this.chiaviControllo1 = chiaviControllo1;
	}
	public String getChiaviControllo2() {
		return chiaviControllo2;
	}
	public void setChiaviControllo2(String chiaviControllo2) {
		this.chiaviControllo2 = chiaviControllo2;
	}
	
	@Override
	public String toString()
	{
		StringBuilder rec70 = new StringBuilder();
		rec70.append(" ");
		rec70.append(tipoRecord);
		rec70.append(String.format("%7s", numeroProgressivo).replaceAll(" ",
		"0"));
		rec70.append((String.format("%-15s",codiceRiferimento.toUpperCase())));
		rec70.append(String.format("%95s", " "));		
		return rec70.toString();
	}

}
