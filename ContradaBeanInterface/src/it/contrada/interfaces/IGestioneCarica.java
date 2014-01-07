package it.contrada.interfaces;

import it.contrada.dominio.dto.TipoCaricaDTO;
import it.contrada.exceptions.ContradaExceptionBloccante;
import it.contrada.exceptions.ContradaExceptionNonBloccante;

import java.util.List;

public interface IGestioneCarica {

	public int aggiornaCarica(List<TipoCaricaDTO> cariche,boolean aggiornaTessereInCorso)  throws ContradaExceptionBloccante, ContradaExceptionNonBloccante;
}
