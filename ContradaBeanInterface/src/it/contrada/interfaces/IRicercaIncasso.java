package it.contrada.interfaces;

import it.contrada.dominio.dto.TipoIncassoDTO;
import it.contrada.exceptions.ContradaExceptionBloccante;
import it.contrada.exceptions.ContradaExceptionNonBloccante;

import java.util.List;

public interface IRicercaIncasso {
	public List<TipoIncassoDTO> elencaTipoIncasso() throws ContradaExceptionBloccante,ContradaExceptionNonBloccante;
}
