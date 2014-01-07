package it.contrada.dto;

import java.io.Serializable;

public class FamigliaDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2796151945446601833L;
	private Integer idFamiglia;
	private Integer idCapoFamiglia;

	
	public Integer getIdFamiglia() {
		return idFamiglia;
	}
	public void setIdFamiglia(Integer idFamiglia) {
		this.idFamiglia = idFamiglia;
	}
	public Integer getIdCapoFamiglia() {
		return idCapoFamiglia;
	}
	public void setIdCapoFamiglia(Integer idCapoFamiglia) {
		this.idCapoFamiglia = idCapoFamiglia;
	}

}
