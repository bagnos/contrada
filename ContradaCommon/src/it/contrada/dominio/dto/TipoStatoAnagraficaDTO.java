package it.contrada.dominio.dto;

import java.io.Serializable;

public class TipoStatoAnagraficaDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4557207820551663567L;
	private int idStatoAnagrafica;
	private String dsStatoAnagrafica;

	public int getIdStatoAnagrafica() {
		return idStatoAnagrafica;
	}

	public void setIdStatoProtettore(int idStatoAnagrafica) {
		this.idStatoAnagrafica = idStatoAnagrafica;
	}

	public String getDsStatoAnagrafica() {
		return dsStatoAnagrafica;
	}

	public void setDsStatoProtettore(String dsStatoAnagrafica) {
		this.dsStatoAnagrafica = dsStatoAnagrafica;
	}

}
