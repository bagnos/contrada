package it.contrada.interfaces;

import it.contrada.dto.*;
import it.contrada.exceptions.ContradaExceptionBloccante;
import it.contrada.exceptions.ContradaExceptionNonBloccante;

import java.util.List;

public interface IRicercaProvincia {

	public List<ProvinciaDTO> recuperaPerRegione(int cdRegione) throws ContradaExceptionBloccante,ContradaExceptionNonBloccante;;;
	public List<ProvinciaDTO> recuperaPerStato(String cdIsoStato) throws ContradaExceptionBloccante,ContradaExceptionNonBloccante;
	public List<ProvinciaDTO> elenca() throws ContradaExceptionBloccante,ContradaExceptionNonBloccante;
}
