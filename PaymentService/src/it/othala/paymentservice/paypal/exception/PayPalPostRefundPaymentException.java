package it.othala.paymentservice.paypal.exception;


public class PayPalPostRefundPaymentException extends BaseException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String KEY_MESSAGE = "exception_postPayPalRefundException";

	public PayPalPostRefundPaymentException(String message,String errorMessage) {
		super(message);
	}
	
	public PayPalPostRefundPaymentException(String errorMessage) {
		super(errorMessage);
	}
	
	public PayPalPostRefundPaymentException(Throwable e,String idRefund,String dsErrore) {
		super(dsErrore,e);
		
	}


}
