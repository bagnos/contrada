package it.othala.paymentservice.paypal.exception;




public class PayPalFundingFailureException extends BaseException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private String redirectUrl;

	public String getRedirectUrl() {
		return redirectUrl;
	}

	public PayPalFundingFailureException(String messageException,String redirectUrl) {
		super(messageException);
		this.redirectUrl=redirectUrl;
	}

}
