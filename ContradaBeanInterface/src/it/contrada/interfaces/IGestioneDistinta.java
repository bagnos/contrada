package it.contrada.interfaces;

import it.contrada.dto.DistintaDTO;
import it.contrada.exceptions.ContradaExceptionBloccante;

import java.util.List;

public interface IGestioneDistinta {

	public List<DistintaDTO> ricercaDistintaPerAnno(int anno) throws ContradaExceptionBloccante;
}
