package it.contrada.dto;

import java.io.Serializable;

public class ComuneDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2501312837028474138L;
	private int cdComune;
	private String dsComune;
	private int cdProvincia;
	private String cdCap;

	public int getCdComune() {
		return cdComune;
	}

	public void setCdComune(int cdComune) {
		this.cdComune = cdComune;
	}

	public String getDsComune() {
		return dsComune;
	}

	public void setDsComune(String dsComune) {
		this.dsComune = dsComune;
	}

	public int getCdProvincia() {
		return cdProvincia;
	}

	public void setCdProvincia(int cdProvincia) {
		this.cdProvincia = cdProvincia;
	}

	public String getCdCap() {
		return cdCap;
	}

	public void setCdCap(String cdCap) {
		this.cdCap = cdCap;
	}
}
