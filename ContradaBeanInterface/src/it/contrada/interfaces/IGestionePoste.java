package it.contrada.interfaces;

import it.contrada.exceptions.ContradaExceptionBloccante;
import it.contrada.exceptions.ContradaExceptionNonBloccante;
import it.contrada.poste.FlussoIncassoPostaDTO;
import it.contrada.poste.IncassoPostaDTO;
import it.contrada.poste.RendicontazioneIncassoPostaDTO;

import java.util.List;

public interface IGestionePoste {
	public FlussoIncassoPostaDTO produciFlussiIncassoPoste(int anno,
			java.sql.Date dtScadenza, List<Integer> tipoTessere, String nomefile,List<Integer> tipoIncasso,List<Integer> tessereManuali)
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante;

	

	public FlussoIncassoPostaDTO confermaInvioFlussiIncassoPoste(
			FlussoIncassoPostaDTO flusso) throws ContradaExceptionBloccante,
			ContradaExceptionNonBloccante;

	public List<FlussoIncassoPostaDTO> ricercaFlussoPostalePerAnno(int anno)
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante;

	public void eliminaFlusso(long idFlusso) throws ContradaExceptionBloccante,
			ContradaExceptionNonBloccante;

	public List<RendicontazioneIncassoPostaDTO> rendicontaFlussoPoste(
			String pathFile) throws ContradaExceptionBloccante,
			ContradaExceptionNonBloccante;
}
