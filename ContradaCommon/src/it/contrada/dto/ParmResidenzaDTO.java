package it.contrada.dto;

import java.io.Serializable;

public class ParmResidenzaDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1019977132242469923L;
	private Integer cdProvincia;
	private Integer cdComune;
	private String cdCap;
	private String localita;
	private Integer idStrada;
	private Integer idGestore;
	private Integer idLocalita;
	private Integer idTipoIncasso;
	private Integer idTipoCarica;
	
	
	
	public Integer getIdTipoCarica() {
		return idTipoCarica;
	}
	public void setIdTipoCarica(Integer idTipoCarica) {
		this.idTipoCarica = idTipoCarica;
	}
	public Integer getIdTipoIncasso() {
		return idTipoIncasso;
	}
	public void setIdTipoIncasso(Integer idTipoIncasso) {
		this.idTipoIncasso = idTipoIncasso;
	}
	public Integer getIdLocalita() {
		return idLocalita;
	}
	public void setIdLocalita(Integer idLocalita) {
		this.idLocalita = idLocalita;
	}
	public Integer getIdGestore() {
		return idGestore;
	}
	public void setIdGestore(Integer idGestore) {
		this.idGestore = idGestore;
	}
	public Integer getIdStrada() {
		return idStrada;
	}
	public void setIdStrada(Integer idStrada) {
		this.idStrada = idStrada;
	}
	public Integer getCdProvincia() {
		return cdProvincia;
	}
	public void setCdProvincia(Integer cdProvincia) {
		this.cdProvincia = cdProvincia;
	}
	public Integer getCdComune() {
		return cdComune;
	}
	public void setCdComune(Integer cdComune) {
		this.cdComune = cdComune;
	}
	public String getCdCap() {
		return cdCap;
	}
	public void setCdCap(String cdCap) {
		this.cdCap = cdCap;
	}
	public String getLocalita() {
		return localita;
	}
	public void setLocalita(String localita) {
		this.localita = localita;
	}
	
	

}
