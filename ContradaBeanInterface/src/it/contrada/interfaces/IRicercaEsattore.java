package it.contrada.interfaces;

import it.contrada.dto.EsattoreDTO;
import it.contrada.exceptions.ContradaExceptionBloccante;
import it.contrada.exceptions.ContradaExceptionNonBloccante;

import java.util.List;

public interface IRicercaEsattore {
	public List<EsattoreDTO> elencaEsattore() throws ContradaExceptionBloccante,ContradaExceptionNonBloccante;
}
