package it.contrada.interfaces;

import it.contrada.dto.FlussoEsitoDTO;
import it.contrada.dto.RidDTO;
import it.contrada.enumcontrada.TipoFlusso;
import it.contrada.exceptions.ContradaExceptionBloccante;
import it.contrada.exceptions.ContradaExceptionNonBloccante;
import it.contrada.incassorid.dto.FlussoIncassoRidDTO;
import it.contrada.incassorid.dto.RicezioneFlussoIncassoRidDTO;
import it.contrada.preautrid.dto.DisposizionePreautRicezioneDTO;
import it.contrada.preautrid.dto.FlussoPreautInviatoDTO;

import java.io.File;
import java.util.List;

public interface IGestioneFlusso {
	public FlussoIncassoRidDTO preparaFlussoIncassiRid(int anno, int mese,
			int tipoIncasso, java.sql.Date dtValuta)
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante;

	public void eliminaFlussoIncassiRid(int anno, int mese, int tipoIncasso)
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante;

	public List<DisposizionePreautRicezioneDTO> riceviFlussoPreautorizzazioniRid(
			String nomeFile) throws ContradaExceptionBloccante,
			ContradaExceptionNonBloccante;

	public List<RicezioneFlussoIncassoRidDTO> riceviFlussoIncassiRid(
			String nomeFile) throws ContradaExceptionBloccante,
			ContradaExceptionNonBloccante;

	public List<RidDTO> elencaRidDaAllineare()
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante;

	public FlussoPreautInviatoDTO preparaFlussoPreautorizzazioniRid(
			List<RidDTO> rids) throws ContradaExceptionBloccante,
			ContradaExceptionNonBloccante;

	public FlussoIncassoRidDTO generaFlussoIncassiRid(int anno, int mese,
			int tipoIncasso) throws ContradaExceptionNonBloccante,
			ContradaExceptionBloccante;

	public FlussoPreautInviatoDTO generaFlussoPreautInviati(
			java.util.Date dtInvio) throws ContradaExceptionNonBloccante,
			ContradaExceptionBloccante;

	public FlussoPreautInviatoDTO generaFlussoPreautInviati(List<RidDTO> rids)
			throws ContradaExceptionNonBloccante, ContradaExceptionBloccante;

	public List<FlussoPreautInviatoDTO> getFlussoPreautorizzati(int anno)
			throws ContradaExceptionNonBloccante, ContradaExceptionBloccante;

	public int eliminaFlussoPreautorizzazioni(java.util.Date dtInvio)
			throws ContradaExceptionNonBloccante, ContradaExceptionBloccante;
	
	public FlussoEsitoDTO inserisciFlussoEsito(File file, TipoFlusso tipoFlusso) throws ContradaExceptionNonBloccante, ContradaExceptionBloccante;
}
