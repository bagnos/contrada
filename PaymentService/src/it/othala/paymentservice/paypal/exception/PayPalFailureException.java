package it.othala.paymentservice.paypal.exception;


public class PayPalFailureException extends BaseException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public PayPalFailureException(String errorMessage) {
		super(errorMessage);
	}

}
