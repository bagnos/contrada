package it.contrada.dto;

import java.io.Serializable;

public class StradaDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2254539828200251586L;
	private int idStrada;
	private String dsStrada;
	private String dsLocalita;	
	private String cdCap;
	private int cdProvincia;
	private int cdComune;
	private Integer idLocalita;
	
	
	public Integer getIdLocalita() {
		return idLocalita;
	}
	public void setIdLocalita(Integer idLocalita) {
		this.idLocalita = idLocalita;
	}
	public int getCdProvincia() {
		return cdProvincia;
	}
	public void setCdProvincia(int cdProvincia) {
		this.cdProvincia = cdProvincia;
	}
	public int getCdComune() {
		return cdComune;
	}
	public void setCdComune(int cdComune) {
		this.cdComune = cdComune;
	}
	
	
	public int getIdStrada() {
		return idStrada;
	}
	public void setIdStrada(int idStrada) {
		this.idStrada = idStrada;
	}
	public String getDsStrada() {
		return dsStrada;
	}
	public void setDsStrada(String dsStrada) {
		this.dsStrada = dsStrada;
	}
	public String getDsLocalita() {
		return dsLocalita;
	}
	public void setDsLocalita(String dsLocalita) {
		this.dsLocalita = dsLocalita;
	}
	public String getCdCap() {
		return cdCap;
	}
	public void setCdCap(String cdCap) {
		this.cdCap = cdCap;
	}
}
