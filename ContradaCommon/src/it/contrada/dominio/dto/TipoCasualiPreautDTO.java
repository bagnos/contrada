package it.contrada.dominio.dto;

import java.io.Serializable;

public class TipoCasualiPreautDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5668009961396995260L;
	private int cdCausale;
	private String dsCausale;
	private Integer idStatoRidSucc;
	private boolean aggiornaCoordinate;
	
	
	public boolean isAggiornaCoordinate() {
		return aggiornaCoordinate;
	}
	public void setAggiornaCoordinate(boolean aggiornaCoordinate) {
		this.aggiornaCoordinate = aggiornaCoordinate;
	}
	public Integer getIdStatoRidSucc() {
		return idStatoRidSucc;
	}
	public void setIdStatoRidSucc(Integer idStatoRidSucc) {
		this.idStatoRidSucc = idStatoRidSucc;
	}
	
	public int getCdCausale() {
		return cdCausale;
	}
	public void setCdCausale(int cdCausale) {
		this.cdCausale = cdCausale;
	}
	public String getDsCausale() {
		return dsCausale;
	}
	public void setDsCausale(String dsCausale) {
		this.dsCausale = dsCausale;
	}
	
}
