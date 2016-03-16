package it.othala.payment.paypal.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PayPalResponseDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String token;
	private String correlationid;
	private String okMessage;
	private String koMessage;
	
	private List<String> errorCodes;
	
	public List<String> getErrorCodes() {
		if (errorCodes==null)
		{
			errorCodes=new ArrayList<String>();
		}
		return errorCodes;
	}
	public String getOkMessage() {
		return okMessage;
	}
	public void setOkMessage(String okMessage) {
		this.okMessage = okMessage;
	}
	public String getKoMessage() {
		return koMessage;
	}
	public void setKoMessage(String koMessage) {
		this.koMessage = koMessage;
	}
	public String getCorrelationid() {
		return correlationid;
	}
	public void setCorrelationid(String correlationid) {
		this.correlationid = correlationid;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
}
