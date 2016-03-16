package it.othala.payment.paypal.dto;

import java.io.Serializable;
import java.util.List;

public class RichiestaIncassoDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String user;
	private  List<TesseraPaymentDTO> tessere;
	
	private String returnUrl;
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
	public List<TesseraPaymentDTO> getTessere() {
		return tessere;
	}
	public void setTessere(List<TesseraPaymentDTO> tessere) {
		this.tessere = tessere;
	}

}
