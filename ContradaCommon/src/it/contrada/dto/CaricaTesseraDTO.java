package it.contrada.dto;

import java.io.Serializable;

public class CaricaTesseraDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2764170407951732239L;
	private int idCarica;
	private String dsCarica;

	public int getIdCarica() {
		return idCarica;
	}

	public void setIdCarica(int idCarica) {
		this.idCarica = idCarica;
	}

	public String getDsCarica() {
		return dsCarica;
	}

	public void setDsCarica(String dsCarica) {
		this.dsCarica = dsCarica;
	}

	public int getImTessera() {
		return imTessera;
	}

	public void setImTessera(int imTessera) {
		this.imTessera = imTessera;
	}

	private int imTessera;

}
