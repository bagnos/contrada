package it.contrada.exceptions;

public class ContradaException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	

	public ContradaException(String dsErrore, Throwable cause) {
		super(dsErrore, cause);
	}

	public ContradaException(String dsErrore) {

		super(dsErrore);

	}

}
