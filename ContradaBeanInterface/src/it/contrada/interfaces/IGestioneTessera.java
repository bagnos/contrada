package it.contrada.interfaces;

import it.contrada.dominio.dto.TipoTesseraDTO;
import it.contrada.exceptions.ContradaExceptionBloccante;
import it.contrada.exceptions.ContradaExceptionNonBloccante;

import java.util.List;

public interface IGestioneTessera {
	
	public int allineaQuotaTessere(java.sql.Date dataRif) throws ContradaExceptionBloccante, ContradaExceptionNonBloccante;
	public int aggiornaTipoTessera(List<TipoTesseraDTO> tessere,boolean aggiornaTessereInCorso) throws ContradaExceptionBloccante, ContradaExceptionNonBloccante;
	public int aggiornaImportoTessera(List<TipoTesseraDTO> tessere) throws ContradaExceptionBloccante, ContradaExceptionNonBloccante;
	
}
