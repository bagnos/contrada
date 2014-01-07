package it.contrada.dominio.dto;

import java.io.Serializable;

public class TipoCasualiIncassoRidDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -545464471636137530L;
	private int cdCausale;
	private String dsCausale;
	private Integer idStatoRidSucc;
	private Integer idStatoRataSucc;

	public Integer getIdStatoRataSucc() {
		return idStatoRataSucc;
	}

	public void setIdStatoRataSucc(Integer idStatoRataSucc) {
		this.idStatoRataSucc = idStatoRataSucc;
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
