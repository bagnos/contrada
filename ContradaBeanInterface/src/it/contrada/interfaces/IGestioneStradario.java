package it.contrada.interfaces;

import it.contrada.dto.CapDTO;
import it.contrada.dto.LocalitaDTO;
import it.contrada.dto.StradaDTO;
import it.contrada.exceptions.ContradaExceptionBloccante;

public interface IGestioneStradario {
	public int inserisciStrada(StradaDTO strada) throws ContradaExceptionBloccante;
	public int inserisciLocalita(LocalitaDTO localita) throws ContradaExceptionBloccante;
	public int inserisciCap(CapDTO cap) throws ContradaExceptionBloccante;
}
