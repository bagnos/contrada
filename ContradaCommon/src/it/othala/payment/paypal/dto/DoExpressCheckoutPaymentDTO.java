package it.othala.payment.paypal.dto;

import java.math.BigDecimal;


public class DoExpressCheckoutPaymentDTO extends PayPalResponseDTO {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String PAYMENTINFO_0_TRANSACTIONID;
	private String PAYMENTINFO_0_PAYMENTSTATUS;
	private String PAYMENTINFO_0_PENDINGREASON;
	private String note;	
	private boolean failedPaymenet;
	private String L_PAYMENTINFO_0_FMF;
	
	public String getL_PAYMENTINFO_0_FMF() {
		return L_PAYMENTINFO_0_FMF;
	}
	public void setL_PAYMENTINFO_0_FMF(String l_PAYMENTINFO_0_FMF) {
		L_PAYMENTINFO_0_FMF = l_PAYMENTINFO_0_FMF;
	}
	public void setFailedPaymenet(boolean failedPaymenet) {
		this.failedPaymenet = failedPaymenet;
	}
	public boolean isFailedPaymenet() {
		return failedPaymenet;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getPAYMENTINFO_0_TRANSACTIONID() {
		return PAYMENTINFO_0_TRANSACTIONID;
	}
	public void setPAYMENTINFO_0_TRANSACTIONID(String pAYMENTINFO_0_TRANSACTIONID) {
		PAYMENTINFO_0_TRANSACTIONID = pAYMENTINFO_0_TRANSACTIONID;
	}
	public String getPAYMENTINFO_0_PAYMENTSTATUS() {
		return PAYMENTINFO_0_PAYMENTSTATUS;
	}
	public void setPAYMENTINFO_0_PAYMENTSTATUS(String pAYMENTINFO_0_PAYMENTSTATUS) {
		PAYMENTINFO_0_PAYMENTSTATUS = pAYMENTINFO_0_PAYMENTSTATUS;
	}
	public String getPAYMENTINFO_0_PENDINGREASON() {
		return PAYMENTINFO_0_PENDINGREASON;
	}
	public void setPAYMENTINFO_0_PENDINGREASON(String pAYMENTINFO_0_PENDINGREASON) {
		PAYMENTINFO_0_PENDINGREASON = pAYMENTINFO_0_PENDINGREASON;
	}
	
	
	
		
	
}
