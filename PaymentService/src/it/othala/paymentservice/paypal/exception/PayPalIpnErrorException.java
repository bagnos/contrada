package it.othala.paymentservice.paypal.exception;


public class PayPalIpnErrorException extends BaseException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PayPalIpnErrorException(String errorMessage) {
		super(errorMessage);
	}

}
