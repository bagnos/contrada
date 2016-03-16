package it.othala.paymentservice.paypal.exception;


public class PayPalPostPaymentException extends BaseException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String KEY_MESSAGE = "exception_postPayPalException";

	public PayPalPostPaymentException(String message,String errorMessage) {
		super(errorMessage);
	}
	
	public PayPalPostPaymentException(String errorMessage) {
		super(errorMessage);
	}
	
	public PayPalPostPaymentException(Throwable e,int idOrder,String dsErrore) {
		super(dsErrore,e);
		
	}


}
