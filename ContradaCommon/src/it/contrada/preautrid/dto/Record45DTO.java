package it.contrada.preautrid.dto;

import java.io.Serializable;

public class Record45DTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -677236110705219302L;
	private String numeroProgressivo;
	private String cab;
	private String contoAddebito;
	private String codiceIndividuale;
	private String codiceAzienda;
	private String tipoCodiceIndividuale;
	private String abi;
	private String codicePaese;
	private String checkDigit;
	private String cin;
	public String getNumeroProgressivo() {
		return numeroProgressivo;
	}
	public void setNumeroProgressivo(String numeroProgressivo) {
		this.numeroProgressivo = numeroProgressivo;
	}
	public String getCab() {
		return cab;
	}
	public void setCab(String cab) {
		this.cab = cab;
	}
	public String getContoAddebito() {
		return contoAddebito;
	}
	public void setContoAddebito(String contoAddebito) {
		this.contoAddebito = contoAddebito;
	}
	public String getCodiceIndividuale() {
		return codiceIndividuale;
	}
	public void setCodiceIndividuale(String codiceIndividuale) {
		this.codiceIndividuale = codiceIndividuale;
	}
	public String getCodiceAzienda() {
		return codiceAzienda;
	}
	public void setCodiceAzienda(String codiceAzienda) {
		this.codiceAzienda = codiceAzienda;
	}
	public String getTipoCodiceIndividuale() {
		return tipoCodiceIndividuale;
	}
	public void setTipoCodiceIndividuale(String tipoCodiceIndividuale) {
		this.tipoCodiceIndividuale = tipoCodiceIndividuale;
	}
	public String getAbi() {
		return abi;
	}
	public void setAbi(String abi) {
		this.abi = abi;
	}
	public String getCodicePaese() {
		return codicePaese;
	}
	public void setCodicePaese(String codicePaese) {
		this.codicePaese = codicePaese;
	}
	public String getCheckDigit() {
		return checkDigit;
	}
	public void setCheckDigit(String checkDigit) {
		this.checkDigit = checkDigit;
	}
	public String getCin() {
		return cin;
	}
	public void setCin(String cin) {
		this.cin = cin;
	}
	
	
}
