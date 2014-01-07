package it.contrada.interfaces;

import it.contrada.dto.FamigliaDTO;
import it.contrada.exceptions.ContradaExceptionBloccante;
import it.contrada.exceptions.ContradaExceptionNonBloccante;

public interface IGestioneFamiglia {
	public FamigliaDTO inserisciFamglia(int idCapoFamiglia) throws ContradaExceptionBloccante, ContradaExceptionNonBloccante;
	public void aggiornaFamiglia(FamigliaDTO famiglia) throws ContradaExceptionBloccante, ContradaExceptionNonBloccante;
	
}
