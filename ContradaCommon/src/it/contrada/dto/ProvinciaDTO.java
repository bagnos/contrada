package it.contrada.dto;

import java.io.Serializable;

public class ProvinciaDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1223901999192942382L;
	private int cdProvincia;
	private String dsProvincia;
	private String cdSiglaProv;
	private int cdRegione;
	private String cdIsoStato;
	private String cdCap;
	
	public String getCdCap() {
		return cdCap;
	}

	public void setCdCap(String cdCap) {
		this.cdCap = cdCap;
	}

	public String getCdIsoStato() {
		return cdIsoStato;
	}

	public void setCdIsoStato(String cdIsoStato) {
		this.cdIsoStato = cdIsoStato;
	}

	public int getCdProvincia() {
		return cdProvincia;
	}

	public void setCdProvincia(int cdProvincia) {
		this.cdProvincia = cdProvincia;
	}

	public String getDsProvincia() {
		return dsProvincia;
	}

	public void setDsProvincia(String dsProvincia) {
		this.dsProvincia = dsProvincia;
	}

	public String getCdSiglaProv() {
		return cdSiglaProv;
	}

	public void setCdSiglaProv(String cdSiglaProv) {
		this.cdSiglaProv = cdSiglaProv;
	}

	public int getCdRegione() {
		return cdRegione;
	}

	public void setCdRegione(int cdRegione) {
		this.cdRegione = cdRegione;
	}

}
