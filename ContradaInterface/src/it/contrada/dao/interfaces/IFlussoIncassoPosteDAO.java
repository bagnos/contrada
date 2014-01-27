package it.contrada.dao.interfaces;

import it.contrada.poste.FlussoIncassoPostaDTO;
import it.contrada.poste.IncassoPostaDTO;
import it.contrada.poste.RendicontazioneIncassoPostaDTO;

import java.util.List;

public interface IFlussoIncassoPosteDAO {

	public List<IncassoPostaDTO> getFlussiIncassoPosta(int anno, java.sql.Date dtScadenza,List<Integer> tipoTessere,List<Integer> tipoIncasso,List<Integer> tessereManuali) throws Exception;
	public int insertIncassoPosta(List<IncassoPostaDTO> incassi) throws Exception;
	public int insertFlussoIncassoPosta(FlussoIncassoPostaDTO flusso) throws Exception;
	public int eliminaIncassiPosta(long idFlusso) throws Exception;
	public int eliminaFlussoIncassoPosta(long idFlusso) throws Exception;
	public int insertRendicontazioneFlussi(RendicontazioneIncassoPostaDTO bollettino) throws Exception;
	public List<FlussoIncassoPostaDTO> getFlussoPostalePerAnno(int anno) throws Exception;
	public int aggiornaRendicontazioneFlusso(RendicontazioneIncassoPostaDTO esito) throws Exception;
}

