package it.contrada.interfaces;

import it.contrada.dto.CapDTO;
import it.contrada.exceptions.ContradaExceptionBloccante;
import it.contrada.exceptions.ContradaExceptionNonBloccante;

import java.util.List;

public interface IRicercaCap {
	public List<CapDTO> recuperaCap(int cdProvincia, int cdComune)
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante;
}
