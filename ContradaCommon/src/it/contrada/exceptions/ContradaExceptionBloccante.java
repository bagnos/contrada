package it.contrada.exceptions;

public class ContradaExceptionBloccante extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ContradaExceptionBloccante(String dsErrore, Throwable cause)
	{
		super(dsErrore,cause);
	}
	
	public ContradaExceptionBloccante(String dsErrore)
	{
		
		super(dsErrore);
		
	}
	
	
}
