package it.contrada.interfaces;

import it.contrada.dto.RidDTO;
import it.contrada.enumcontrada.TipoIncassoRid;
import it.contrada.exceptions.ContradaExceptionBloccante;
import it.contrada.exceptions.ContradaExceptionNonBloccante;
import it.contrada.incassorid.dto.FlussoIncassoRidDTO;

public interface IGestioneRid {
	public RidDTO inserisciRid(RidDTO ridDTO) throws ContradaExceptionBloccante, ContradaExceptionNonBloccante;
	public void aggiornaRid(RidDTO ridDTO) throws ContradaExceptionBloccante, ContradaExceptionNonBloccante;
	public RidDTO inserisciRidConMembri(RidDTO ridDTO) throws ContradaExceptionBloccante, ContradaExceptionNonBloccante;
	
}
