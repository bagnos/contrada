package it.contrada.dto;

import java.io.Serializable;

public class DistintaDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5666712427075366475L;
	private int nrDistinta;
	private int nrAnno;
	private String txOper;
	private java.sql.Timestamp tsInserimento;
	private String txUtente;
	private int imDistinta;
	private int nrTessere;
	private String user;
	private OperazioneDTO operazione;

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public OperazioneDTO getOperazione() {
		return operazione;
	}

	public void setOperazione(OperazioneDTO operazione) {
		this.operazione = operazione;
	}

	public int getNrTessere() {
		return nrTessere;
	}

	public void setNrTessere(int nrTessere) {
		this.nrTessere = nrTessere;
	}

	public int getNrDistinta() {
		return nrDistinta;
	}

	public void setNrDistinta(int nrDistinta) {
		this.nrDistinta = nrDistinta;
	}

	public int getNrAnno() {
		return nrAnno;
	}

	public void setNrAnno(int nrAnno) {
		this.nrAnno = nrAnno;
	}

	public String getTxOper() {
		return this.txOper;
	}

	public void setTxOper(String txOper) {
		this.txOper = txOper;
	}

	public java.sql.Timestamp getTsInserimento() {
		return tsInserimento;
	}

	public void setTsInserimento(java.sql.Timestamp tsInserimento) {
		this.tsInserimento = tsInserimento;
	}

	public String getTxUtente() {
		return txUtente;
	}

	public void setTxUtente(String txUtente) {
		this.txUtente = txUtente;
	}

	public int getImDistinta() {
		return imDistinta;
	}

	public void setImDistinta(int imDistinta) {
		this.imDistinta = imDistinta;
	}

}
