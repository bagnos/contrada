package it.contrada.dominio.dto;

import java.io.Serializable;

public class TipoCaricaDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8877855706016517342L;
	private Integer idCarica;
	private String dsCarica;
	private int idCaricaSucc;
	private String dsCaricaSucc;
	private String txDizioneTessera;
	private Integer nrEtaMax;
	private Integer imMinimo;
	private Integer idTipoCaricaSucc;
	private Integer idTipoTessera;
	private String dsTipoTessera;
	private Integer imTessera;
	private boolean modificata;
	private Integer imMinimoNew;
	private boolean edit;
	private Integer imMinimoPrec;

	public Integer getImMinimoPrec() {
		return imMinimoPrec;
	}

	public void setImMinimoPrec(Integer imMinimoPrec) {
		this.imMinimoPrec = imMinimoPrec;
	}

	public boolean isEdit() {
		return edit;
	}

	public void setEdit(boolean edit) {
		this.edit = edit;
	}

	public Integer getImMinimoNew() {
		return imMinimoNew;
	}

	public void setImMinimoNew(Integer imMinimoNew) {
		this.imMinimoNew = imMinimoNew;
	}

	public boolean isModificata() {
		return modificata;
	}

	public void setModificata(boolean modificata) {
		this.modificata = modificata;
	}

	public Integer getImTessera() {
		return imTessera;
	}

	public void setImTessera(Integer imTessera) {
		this.imTessera = imTessera;
	}

	public int getIdCaricaSucc() {
		return idCaricaSucc;
	}

	public void setIdCaricaSucc(int idCaricaSucc) {
		this.idCaricaSucc = idCaricaSucc;
	}

	public String getDsCaricaSucc() {
		return dsCaricaSucc;
	}

	public void setDsCaricaSucc(String dsCaricaSucc) {
		this.dsCaricaSucc = dsCaricaSucc;
	}

	public Integer getIdTipoTessera() {
		return idTipoTessera;
	}

	public void setIdTipoTessera(Integer idTipoTessera) {
		this.idTipoTessera = idTipoTessera;
	}

	public String getDsTipoTessera() {
		return dsTipoTessera;
	}

	public void setDsTipoTessera(String dsTipoTessera) {
		this.dsTipoTessera = dsTipoTessera;
	}

	public String getTxDizioneTessera() {
		return txDizioneTessera;
	}

	public void setTxDizioneTessera(String txDizioneTessera) {
		this.txDizioneTessera = txDizioneTessera;
	}

	public Integer getNrEtaMax() {
		return nrEtaMax;
	}

	public void setNrEtaMax(Integer nrEtaMax) {
		this.nrEtaMax = nrEtaMax;
	}

	public Integer getImMinimo() {
		return imMinimo;
	}

	public void setImMinimo(Integer imMinimo) {
		this.imMinimo = imMinimo;
	}

	public Integer getIdTipoCaricaSucc() {
		return idTipoCaricaSucc;
	}

	public void setIdTipoCaricaSucc(Integer idTipoCaricaSucc) {
		this.idTipoCaricaSucc = idTipoCaricaSucc;
	}

	public Integer getIdCarica() {
		return idCarica;
	}

	public void setIdCarica(Integer idCarica) {
		this.idCarica = idCarica;
	}

	public String getDsCarica() {
		return dsCarica;
	}

	public void setDsCarica(String dsCarica) {
		this.dsCarica = dsCarica;
	}
}
