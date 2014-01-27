package it.contrada.interfaces;

import it.contrada.dto.AnagraficaDTO;
import it.contrada.exceptions.ContradaExceptionBloccante;
import it.contrada.exceptions.ContradaExceptionNonBloccante;

import java.util.List;

public interface IGestioneAnagrafica {
	public AnagraficaDTO aggiornaAnagrafica(AnagraficaDTO anagrafica,boolean nuovoRid) throws ContradaExceptionBloccante, ContradaExceptionNonBloccante;
	public AnagraficaDTO inserisciAnagrafica(AnagraficaDTO anagraficaDTO) throws ContradaExceptionBloccante, ContradaExceptionNonBloccante;
	public AnagraficaDTO inserisciAnagraficaConRid(AnagraficaDTO anagraficaDTO) throws ContradaExceptionBloccante, ContradaExceptionNonBloccante;
	public int aggiornaGestore(List<AnagraficaDTO> anagrafiche) throws ContradaExceptionBloccante, ContradaExceptionNonBloccante;
	public int aggiornaStatoAnagrafica(List<Integer> idAnagrafiche,int idStato) throws ContradaExceptionBloccante, ContradaExceptionNonBloccante;
	
}
  