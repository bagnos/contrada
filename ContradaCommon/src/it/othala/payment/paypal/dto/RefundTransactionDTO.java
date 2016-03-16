package it.othala.payment.paypal.dto;

import java.math.BigDecimal;

public class RefundTransactionDTO extends PayPalResponseDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6120852471523822885L;
	
	private String REFUNDTRANSACTIONID;
	private BigDecimal TOTALREFUNDEDAMT;
	private String REFUNDSTATUS;
	private String PENDINGREASON;
	private boolean pending;
	private boolean failed;
	private boolean instant;
	
	public boolean isInstant() {
		return instant;
	}
	public void setInstant(boolean instant) {
		this.instant = instant;
	}
	public boolean isFailed() {
		return failed;
	}
	public void setFailed(boolean failed) {
		this.failed = failed;
	}
	public boolean isPending() {
		return pending;
	}
	public void setPending(boolean pending) {
		this.pending = pending;
	}
	public String getREFUNDTRANSACTIONID() {
		return REFUNDTRANSACTIONID;
	}
	public void setREFUNDTRANSACTIONID(String rEFUNDTRANSACTIONID) {
		REFUNDTRANSACTIONID = rEFUNDTRANSACTIONID;
	}
	public BigDecimal getTOTALREFUNDEDAMT() {
		return TOTALREFUNDEDAMT;
	}
	public void setTOTALREFUNDEDAMT(BigDecimal tOTALREFUNDEDAMT) {
		TOTALREFUNDEDAMT = tOTALREFUNDEDAMT;
	}
	public String getREFUNDSTATUS() {
		return REFUNDSTATUS;
	}
	public void setREFUNDSTATUS(String rEFUNDSTATUS) {
		REFUNDSTATUS = rEFUNDSTATUS;
	}
	public String getPENDINGREASON() {
		return PENDINGREASON;
	}
	public void setPENDINGREASON(String pENDINGREASON) {
		PENDINGREASON = pENDINGREASON;
	}
	
	

}
