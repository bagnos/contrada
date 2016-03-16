package it.othala.payment.paypal.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

public class OrderDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private BigDecimal imOrdine;
	private List<TesseraPaymentDTO> cart;
	
	
		private String idTransaction;
	private String pendingReason;
	private BigDecimal imItemOrdine;
	private int idOrder;
	private String user;
	private String returnUrl;
	private String stato;
	public String getStato() {
		return stato;
	}

	public void setStato(String stato) {
		this.stato = stato;
	}

	public String getReturnUrl() {
		return returnUrl;
	}

	public void setReturnUrl(String returnUrl) {
		this.returnUrl = returnUrl;
	}
	
	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public void setCart(List<TesseraPaymentDTO> cart) {
		this.cart = cart;
	}

	public int getIdOrder() {
		return idOrder;
	}

	public void setIdOrder(int idOrder) {
		this.idOrder = idOrder;
	}

	public BigDecimal getImItemOrdine() {
		return imItemOrdine;
	}

	public void setImItemOrdine(BigDecimal ImItemOrdine) {
		this.imItemOrdine = ImItemOrdine;
	}

	public String getPendingReason() {
		return pendingReason;
	}

	public void setPendingReason(String pendingReason) {
		this.pendingReason = pendingReason;
	}

	public String getIdTransaction() {
		return idTransaction;
	}

	public void setIdTransaction(String idTransaction) {
		this.idTransaction = idTransaction;
	}

	public List<TesseraPaymentDTO> getCart() {
		return cart;
	}

	public BigDecimal getImOrdine() {
		return imOrdine;
	}

	public void setImOrdine(BigDecimal imOrdine) {
		this.imOrdine = imOrdine;
	}

}
