package it.contrada.dominio.dto;

import java.io.Serializable;

public class TipoIncassoDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5438781765502536613L;
	private int idTipoIncasso;
	private String dsTipoIncasso;

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
