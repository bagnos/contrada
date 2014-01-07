package it.contrada.exceptions;

public class ContradaExceptionNonBloccante extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ContradaExceptionNonBloccante(String dsErrore, Throwable cause)
	{
		super(dsErrore,cause);
	}
	
	public ContradaExceptionNonBloccante(String dsErrore)
	{
		
		super(dsErrore);
		
	}
	
}
