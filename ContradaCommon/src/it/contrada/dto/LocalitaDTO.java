package it.contrada.dto;

import java.io.Serializable;

public class LocalitaDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5136465653920764048L;
	private String dsLocalita;	
	private String cdCap;
	private int cdProvincia;
	private int cdComune;
	private Integer idLocalita;
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
	public Integer getIdLocalita() {
		return idLocalita;
	}
	public void setIdLocalita(Integer idLocalita) {
		this.idLocalita = idLocalita;
	}
	
	

}
