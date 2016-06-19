package it.contrada.dominio.dto;

import java.io.Serializable;

public class TipoIncassoDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5438781765502536613L;
	private int idTipoIncasso;
	private String dsTipoIncasso;
	private String cdSIA;
	private String idSeda;
	private String cdIbanAccredito;
	private String denominazione;
	
	
	

	public String getCdSIA() {
		return cdSIA;
	}

	public void setCdSIA(String cdSIA) {
		this.cdSIA = cdSIA;
	}

	public String getIdSeda() {
		return idSeda;
	}

	public void setIdSeda(String idSeda) {
		this.idSeda = idSeda;
	}

	public String getCdIbanAccredito() {
		return cdIbanAccredito;
	}

	public void setCdIbanAccredito(String cdIbanAccredito) {
		this.cdIbanAccredito = cdIbanAccredito;
	}

	public String getDenominazione() {
		return denominazione;
	}

	public void setDenominazione(String denominazione) {
		this.denominazione = denominazione;
	}

	public int getIdTipoIncasso() {
		return idTipoIncasso;
	}

	public void setIdTipoIncasso(int idTipoIncasso) {
		this.idTipoIncasso = idTipoIncasso;
	}

	public String getDsTipoIncasso() {
		return dsTipoIncasso;
	}

	public void setDsTipoIncasso(String dsTipoIncasso) {
		this.dsTipoIncasso = dsTipoIncasso;
	}

}
