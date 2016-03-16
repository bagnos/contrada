package it.othala.paymentservice.paypal.exception;



public class PayPalPaymentRefusedException extends BaseException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public PayPalPaymentRefusedException(String stato) {
		/*String prd=StringUtils.collectionToCommaDelimitedString(productsNoStocked);*/
		
		super(stato);
		// TODO Auto-generated constructor stub
		
	}

}
