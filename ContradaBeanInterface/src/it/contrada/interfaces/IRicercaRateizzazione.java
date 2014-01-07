package it.contrada.interfaces;

import it.contrada.dominio.dto.TipoRateizzazioneDTO;
import it.contrada.dominio.dto.TipoTesseraDTO;
import it.contrada.dto.IncassoAnnualeDTO;
import it.contrada.dto.ParmIncassoDTO;
import it.contrada.dto.RateizzazioneDTO;
import it.contrada.exceptions.ContradaExceptionBloccante;
import it.contrada.exceptions.ContradaExceptionNonBloccante;

import java.util.List;

public interface IRicercaRateizzazione {
	public List<TipoRateizzazioneDTO> elencaTipoRateizzazione() throws ContradaExceptionBloccante,ContradaExceptionNonBloccante;
	public List<TipoRateizzazioneDTO> ricercaPerTessera(int idTipoTessera) throws ContradaExceptionBloccante,ContradaExceptionNonBloccante;
	public List<TipoRateizzazioneDTO> ricercaPerTesseraIncasso(int idTipoTessera,int idTipoIncasso) throws ContradaExceptionBloccante,ContradaExceptionNonBloccante;
	public List<RateizzazioneDTO> ricercaPerAnagrafica(int idAnagrafica) throws ContradaExceptionBloccante,ContradaExceptionNonBloccante;
	public List<RateizzazioneDTO> ricercaPerAnagrafica(int idAnagrafica,int idTipoTessera) throws ContradaExceptionBloccante,ContradaExceptionNonBloccante;
	public List<RateizzazioneDTO> ricercaPerAnagrafica(int idAnagrafica,Integer[] idTipoTessera,Integer annoDa,Integer annoA) throws ContradaExceptionBloccante,ContradaExceptionNonBloccante;
	public List<RateizzazioneDTO> ricercaRate(ParmIncassoDTO parmIncasso) throws ContradaExceptionBloccante,ContradaExceptionNonBloccante;
	public List<RateizzazioneDTO> ricercaRateByAnagrafica(ParmIncassoDTO parmIncasso) throws ContradaExceptionBloccante,ContradaExceptionNonBloccante;
	public List<IncassoAnnualeDTO> recuperaIncassoAnnuale(int anno,List<Integer> idTipoTessera) throws ContradaExceptionBloccante,ContradaExceptionNonBloccante;
}
