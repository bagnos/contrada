package it.contrada.dto; 

import java.io.Serializable;

public class RegioneDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8806672253500560665L;
	private int cdRegione;
	private String dsRegione;
	private String cdStato;
	public int getCdRegione() {
		return cdRegione;
	}
	public void setCdRegione(int cdRegione) {
		this.cdRegione = cdRegione;
	}
	public String getDsRegione() {
		return dsRegione;
	}
	public void setDsRegione(String dsRegione) {
		this.dsRegione = dsRegione;
	}
	public String getCdStato() {
		return cdStato;
	}
	public void setCdStato(String cdStato) {
		this.cdStato = cdStato;
	}
}
