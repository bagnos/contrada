package it.contrada.interfaces;

import it.contrada.dto.OperazioneDTO;
import it.contrada.exceptions.ContradaExceptionNonBloccante;

import java.util.List;

public interface IRicercaOperazione {
	
	public List<OperazioneDTO> elencaUltimeOperazioni() throws ContradaExceptionNonBloccante;
}
