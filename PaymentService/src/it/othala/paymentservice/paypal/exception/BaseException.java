package it.othala.paymentservice.paypal.exception;

public class BaseException extends Exception {
	
private static final long serialVersionUID = 1L;
	
	

	public BaseException(String dsErrore, Throwable cause) {
		super(dsErrore, cause);
	}
	
	public BaseException(String dsErrore) {
		super(dsErrore);
	}
	
	
	
		
	public BaseException() {

		super();

	}


}
