package it.contrada.interfaces;

import it.contrada.dto.RegioneDTO;
import it.contrada.exceptions.ContradaExceptionBloccante;
import it.contrada.exceptions.ContradaExceptionNonBloccante;

import java.util.List;

public interface IRicercaRegione {
	public List<RegioneDTO> recuperaPerNazione(String cdStato) throws ContradaExceptionBloccante,ContradaExceptionNonBloccante;;
}
