package it.othala.payment.paypal.dto;

import java.io.Serializable;

public class IpnDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String mc_gross;
	private String mc_currency;
	private String payment_status;
	private String txn_id;
	private String custom;
	private String receiver_email;
	private String parent_txn_id;
	private String reason_code;

	public String getReason_code() {
		return reason_code;
	}

	public void setReason_code(String reason_code) {
		this.reason_code = reason_code;
	}

	public String getParent_txn_id() {
		return parent_txn_id;
	}

	public void setParent_txn_id(String parent_txn_id) {
		this.parent_txn_id = parent_txn_id;
	}

	public String getMc_gross() {
		return mc_gross;
	}

	public void setMc_gross(String mc_gross) {
		this.mc_gross = mc_gross;
	}

	public String getMc_currency() {
		return mc_currency;
	}

	public void setMc_currency(String mc_currency) {
		this.mc_currency = mc_currency;
	}

	public String getPayment_status() {
		return payment_status;
	}

	public void setPayment_status(String payment_status) {
		this.payment_status = payment_status;
	}

	public String getTxn_id() {
		return txn_id;
	}

	public void setTxn_id(String txn_id) {
		this.txn_id = txn_id;
	}

	public String getCustom() {
		return custom;
	}

	public void setCustom(String custom) {
		this.custom = custom;
	}

	public String getReceiver_email() {
		return receiver_email;
	}

	public void setReceiver_email(String receiver_email) {
		this.receiver_email = receiver_email;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("mc_gross=");
		sb.append(mc_gross != null ? mc_gross : "");
		sb.append(";mc_currency=");
		sb.append(mc_currency != null ? mc_currency : "");
		sb.append(";payment_status=");
		sb.append(payment_status != null ? payment_status : "");
		sb.append(";txn_id=");
		sb.append(txn_id != null ? txn_id : "");
		sb.append(";parent_txn_id=");
		sb.append(parent_txn_id != null ? parent_txn_id : "");
		sb.append(";custom=");
		sb.append(custom != null ? custom : "");
		sb.append(";receiver_email=");
		sb.append(receiver_email != null ? receiver_email : "");
		sb.append(";reason_code=");
		sb.append(reason_code != null ? reason_code : "");
		
		return sb.toString();

	}
}
