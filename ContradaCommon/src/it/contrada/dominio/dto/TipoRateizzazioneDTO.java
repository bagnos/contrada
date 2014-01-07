package it.contrada.dominio.dto;

import java.io.Serializable;

public class TipoRateizzazioneDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7268370985482158521L;
	private int idTipoRateizzazione;
	private String dsTipoRateizzazione;

	public int getIdTipoRateizzazione() {
		return idTipoRateizzazione;
	}

	public void setIdTipoRateizzazione(int idTipoRateizzazione) {
		this.idTipoRateizzazione = idTipoRateizzazione;
	}

	public String getDsTipoRateizzazione() {
		return dsTipoRateizzazione;
	}

	public void setDsTipoRateizzazione(String dsTipoRateizzazione) {
		this.dsTipoRateizzazione = dsTipoRateizzazione;
	}
}
