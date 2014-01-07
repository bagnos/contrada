package it.contrada.interfaces;

import it.contrada.dominio.dto.TipoMeseDTO;
import it.contrada.enumcontrada.TipoIncassoRid;
import it.contrada.exceptions.ContradaExceptionBloccante;
import it.contrada.exceptions.ContradaExceptionNonBloccante;

import java.util.List;

public interface IRicercaMese {
	public List<TipoMeseDTO> ricercaMese(int incasso) throws ContradaExceptionBloccante,ContradaExceptionNonBloccante;
	public List<TipoMeseDTO> elencaMesi() throws ContradaExceptionBloccante,ContradaExceptionNonBloccante;
}
