package it.contrada.dominio.dto;

import java.io.Serializable;

public class TipoRecapitoDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4982391634349918838L;
	private int idTipoRecapito;
	private String dsTipoRecapito;

	public int getIdTipoRecapito() {
		return idTipoRecapito;
	}

	public void setIdTipoRecapito(int idTipoRecapito) {
		this.idTipoRecapito = idTipoRecapito;
	}

	public String getDsTipoRecapito() {
		return dsTipoRecapito;
	}

	public void setDsTipoRecapito(String dsTipoRecapito) {
		this.dsTipoRecapito = dsTipoRecapito;
	}
}
