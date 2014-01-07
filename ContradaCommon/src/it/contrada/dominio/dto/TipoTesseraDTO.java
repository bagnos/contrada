package it.contrada.dominio.dto;

import java.io.Serializable;

public class TipoTesseraDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5679633307040038561L;
	private int idTipoTessera;
	private String dsTipoTessera;
	private Integer imTessera;
	private Integer imTesseraPrec;
	private Integer idCarica;
	private boolean edit;
	private Integer imTesseraNew;
	private boolean modificata;

	public boolean isModificata() {
		return modificata;
	}

	public void setModificata(boolean modificata) {
		this.modificata = modificata;
	}

	public Integer getImTesseraNew() {
		return imTesseraNew;
	}

	public void setImTesseraNew(Integer imTesseraNew) {
		this.imTesseraNew = imTesseraNew;
	}

	public boolean isEdit() {
		return edit;
	}

	public void setEdit(boolean edit) {
		this.edit = edit;
	}

	public Integer getIdCarica() {
		return idCarica;
	}

	public void setIdCarica(Integer idCarica) {
		this.idCarica = idCarica;
	}

	public Integer getImTesseraPrec() {
		return imTesseraPrec;
	}

	public void setImTesseraPrec(Integer imTesseraPrec) {
		this.imTesseraPrec = imTesseraPrec;
	}

	public Integer getImTessera() {
		return imTessera;
	}

	public void setImTessera(Integer imTessera) {
		this.imTessera = imTessera;
	}

	public int getIdTipoTessera() {
		return idTipoTessera;
	}

	public void setIdTipoTessera(int idTipoTessera) {
		this.idTipoTessera = idTipoTessera;
	}

	public String getDsTipoTessera() {
		return dsTipoTessera;
	}

	public void setDsTipoTessera(String dsTipoTessera) {
		this.dsTipoTessera = dsTipoTessera;
	}

}
