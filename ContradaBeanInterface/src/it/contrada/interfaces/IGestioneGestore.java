package it.contrada.interfaces;

import it.contrada.dto.GestoreDTO;
import it.contrada.exceptions.ContradaExceptionBloccante;
import it.contrada.exceptions.ContradaExceptionNonBloccante;

import java.util.List;

public interface IGestioneGestore {

	public List<GestoreDTO> elencaGestori() throws ContradaExceptionNonBloccante,ContradaExceptionBloccante;
	
	public int insertGestore(GestoreDTO gestore) throws ContradaExceptionNonBloccante,ContradaExceptionBloccante;
	
	public int deleteGestore(int idGestore) throws ContradaExceptionNonBloccante,ContradaExceptionBloccante;
}
