package it.contrada.dto;

import java.io.Serializable;

public class OperazioneDTO  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5200834839120264170L;
	private String user;
	private long idOperazione;
	private String dsOperazione;
	private java.sql.Timestamp tsOperazione;
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public long getIdOperazione() {
		return idOperazione;
	}
	
	public String getDsOperazione() {
		return dsOperazione;
	}
	public void setDsOperazione(String dsOperazione) {
		this.dsOperazione = dsOperazione;
	}
	public java.sql.Timestamp getTsOperazione() {
		return tsOperazione;
	}
	
	
}
