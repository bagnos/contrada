package it.othala.paymentservice.paypal.exception;


public class PayPalException extends BaseException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String KEY_MESSAGE = "exception_payPalException";

	
	
	public PayPalException(String errorMessage) {
		super(errorMessage);
	}
	
	public PayPalException(Throwable e) {
		super(KEY_MESSAGE,e);
	}


}
