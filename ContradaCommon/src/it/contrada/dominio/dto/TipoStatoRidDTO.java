package it.contrada.dominio.dto;

import java.io.Serializable;

public class TipoStatoRidDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1031859976897507229L;
	private int idStatoRid;
	private String dsStatoRid;

	public int getIdStatoRid() {
		return idStatoRid;
	}

	public void setIdStatoRid(int idStatoRid) {
		this.idStatoRid = idStatoRid;
	}

	public String getDsStatoRid() {
		return dsStatoRid;
	}

	public void setDsStatoRid(String dsStatoRid) {
		this.dsStatoRid = dsStatoRid;
	}

}
