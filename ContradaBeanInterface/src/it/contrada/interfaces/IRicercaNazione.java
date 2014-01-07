package it.contrada.interfaces;

import it.contrada.dto.NazioneDTO;
import it.contrada.exceptions.ContradaExceptionBloccante;
import it.contrada.exceptions.ContradaExceptionNonBloccante;

import java.util.List;

public interface IRicercaNazione {
public List<NazioneDTO> elencaNazione() throws ContradaExceptionBloccante,ContradaExceptionNonBloccante;
}
