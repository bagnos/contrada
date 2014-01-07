package it.contrada.dto;

import java.io.Serializable;

public class CapDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5080294546805099703L;
	private String cdCap;
	private int cdProv;
	private int cdComune;
	private String dsCap;

	public String getDsCap() {
		return dsCap;
	}

	public void setDsCap(String dsCap) {
		this.dsCap = dsCap;
	}

	public String getCdCap() {
		return cdCap;
	}

	public void setCdCap(String cdCap) {
		this.cdCap = cdCap;
	}

	public int getCdProv() {
		return cdProv;
	}

	public void setCdProv(int cdProv) {
		this.cdProv = cdProv;
	}

	public int getCdComune() {
		return cdComune;
	}

	public void setCdComune(int cdComune) {
		this.cdComune = cdComune;
	}
}
