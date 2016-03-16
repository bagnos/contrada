package it.othala.payment.paypal.dto;

public class SetExpressCheckoutDTO extends PayPalResponseDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String redirectUrl;

	public String getRedirectUrl() {
		return redirectUrl;
	}

	public void setRedirectUrl(String redirectUrl) {
		this.redirectUrl = redirectUrl;
	}

}
