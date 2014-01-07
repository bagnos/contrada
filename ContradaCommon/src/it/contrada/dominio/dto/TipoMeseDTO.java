package it.contrada.dominio.dto;

import java.io.Serializable;

public class TipoMeseDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3509542984258220623L;
	private int idMese;
	private String dsMese;

	public int getIdMese() {
		return idMese;
	}

	public void setIdMese(int idMese) {
		this.idMese = idMese;
	}

	public String getDsMese() {
		return dsMese;
	}

	public void setDsMese(String dsMese) {
		this.dsMese = dsMese;
	}

}
