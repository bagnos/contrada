package it.contrada.dao.interfaces;

import it.contrada.dominio.dto.TipoRateizzazioneDTO;
import it.contrada.dto.IncassoAnnualeDTO;
import it.contrada.dto.ParmIncassoDTO;
import it.contrada.dto.RateizzazioneDTO;

import java.util.List;

public interface IRateizzazioneDAO {
	public List<TipoRateizzazioneDTO> getTipoRateizzazione() throws Exception;
	public List<TipoRateizzazioneDTO> getTipoRateizzazionePerTessera(int idTipoTessera) throws Exception;
	public List<TipoRateizzazioneDTO> getTipoRateizzazionePerTesseraIncasso(int idTipoTessera,int idTipoIncasso) throws Exception;
	public int insertRateizzazione(RateizzazioneDTO rateizzazione) throws Exception;	
	public List<RateizzazioneDTO> getRateizzazionePerAnagrafica(int idAnagrafica) throws Exception;
	public List<RateizzazioneDTO> getRateizzazionePerAnagrafica(int idAnagrafica,int idTipoTessera) throws Exception;
	public List<RateizzazioneDTO> getRateizzazionePerAnagrafica(int idAnagrafica,Integer[] idTipoTessera,Integer annoDa,Integer annoA) throws Exception;
	public int eliminaRateizzazione(RateizzazioneDTO rateizzazione) throws Exception;
	public int insertRateizzazioniPerFlusso(int nrAnno,int mese,int tipoIncassoRid) throws Exception;
	public int aggiornaRidFlussoAddebito (int nrAnno,int mese) throws Exception;
	public int eliminaRateizzazioniDaFlusso(int nrAnno,int mese,int tipoIncassoRid) throws Exception;
	public int aggiornaStatoRata(int idStatoRata,long idFlussoAddebito) throws Exception;
	public int aggiornaStatoRataPerPosta(int idStatoRata,String idFlussoIncassoPoste,java.sql.Date dtOperazione) throws Exception;
	public Integer getIdRidFromIdFlusso(long idFlussoAddebito) throws Exception;
	public List<RateizzazioneDTO> getRate(ParmIncassoDTO parmIncasso) throws Exception;
	public int eliminaRataPerFlussoPosta(long idFlusso) throws Exception;
	public List<IncassoAnnualeDTO> getIncassoAnnuale(int anno,List<Integer> idTipoTessera) throws Exception;
	
}
