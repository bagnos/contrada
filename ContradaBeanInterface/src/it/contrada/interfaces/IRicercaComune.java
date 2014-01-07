package it.contrada.interfaces;

import it.contrada.dto.*;
import it.contrada.exceptions.ContradaExceptionBloccante;
import it.contrada.exceptions.ContradaExceptionNonBloccante;

import java.util.List;

public interface IRicercaComune {
	public List<ComuneDTO> recuperaPerProvincia(int cdProvincia) throws ContradaExceptionBloccante,ContradaExceptionNonBloccante;;
	public List<ComuneDTO> ricercaPerStato(String cdIsoStato) throws ContradaExceptionBloccante,ContradaExceptionNonBloccante;
}
