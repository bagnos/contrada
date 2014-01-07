package it.contrada.dto;

import java.io.Serializable;

public class EsattoreDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7422435668141332741L;
	private int idEsattore;
	private String dsEsattore;
	public int getIdEsattore() {
		return idEsattore;
	}
	public void setIdEsattore(int idEsattore) {
		this.idEsattore = idEsattore;
	}
	public String getDsEsattore() {
		return dsEsattore;
	}
	public void setDsEsattore(String dsEsattore) {
		this.dsEsattore = dsEsattore;
	}

}
