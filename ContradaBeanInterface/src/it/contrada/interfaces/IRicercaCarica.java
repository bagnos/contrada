package it.contrada.interfaces;

import it.contrada.dominio.dto.TipoCaricaDTO;
import it.contrada.dto.CaricaTesseraDTO;
import it.contrada.exceptions.ContradaExceptionBloccante;
import it.contrada.exceptions.ContradaExceptionNonBloccante;

import java.util.List;

public interface IRicercaCarica {
	public List<CaricaTesseraDTO> ricercaPerTipoTessera(int idTipoTessera) throws ContradaExceptionBloccante,ContradaExceptionNonBloccante;;
	public List<TipoCaricaDTO> elencaCariche() throws ContradaExceptionBloccante,ContradaExceptionNonBloccante;;
}
