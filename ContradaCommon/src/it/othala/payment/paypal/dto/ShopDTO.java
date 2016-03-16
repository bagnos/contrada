package it.othala.payment.paypal.dto;

import java.io.Serializable;

public class ShopDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer idShop;
	private String txShop;
	private String txMail;
	public String getTxNome() {
		return txNome;
	}
	public void setTxNome(String txNome) {
		this.txNome = txNome;
	}
	public String getTxVia() {
		return txVia;
	}
	public void setTxVia(String txVia) {
		this.txVia = txVia;
	}
	public String getTxComune() {
		return txComune;
	}
	public void setTxComune(String txComune) {
		this.txComune = txComune;
	}
	public Integer getCdCap() {
		return cdCap;
	}
	public void setCdCap(Integer cdCap) {
		this.cdCap = cdCap;
	}
	public String getTxProvincia() {
		return txProvincia;
	}
	public void setTxProvincia(String txProvincia) {
		this.txProvincia = txProvincia;
	}
	public String getTxNazione() {
		return txNazione;
	}
	public void setTxNazione(String txNazione) {
		this.txNazione = txNazione;
	}
	public String getTxTel() {
		return txTel;
	}
	public void setTxTel(String txTel) {
		this.txTel = txTel;
	}
	private String txNome;
	private String txVia;
	private String txComune;
	private Integer cdCap;
	private String txProvincia;
	private String txNazione;
	private String txTel;
	
	public Integer getIdShop() {
		return idShop;
	}
	public void setIdShop(Integer idShop) {
		this.idShop = idShop;
	}
	public String getTxShop() {
		return txShop;
	}
	public void setTxShop(String txShop) {
		this.txShop = txShop;
	}
	public String getTxMail() {
		return txMail;
	}
	public void setTxMail(String txMail) {
		this.txMail = txMail;
	}



	
	
}
