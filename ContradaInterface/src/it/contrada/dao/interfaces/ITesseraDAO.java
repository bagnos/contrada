package it.contrada.dao.interfaces;

import it.contrada.dominio.dto.TipoTesseraDTO;
import it.contrada.dto.TesseraDTO;
import it.contrada.dto.TesseraStampataDTO;

import java.util.List;

public interface ITesseraDAO {
	public List<TipoTesseraDTO> getTipoTessera() throws Exception;

	public List<TipoTesseraDTO> getTipoTesseraPerIncasso(int idTipoIncasso)
			throws Exception;

	public List<TipoTesseraDTO> getTipoTesseraPerAnagIncasso(long idAnag,
			int idTipoIncasso) throws Exception;

	public List<TesseraDTO> insertTessere(List<TesseraDTO> tessere)
			throws Exception;

	public int eliminaTessere(int cdAnag) throws Exception;

	public int aggiornaRidTessera(List<Integer> idTessere, int idRid)
			throws Exception;

	public int annullaRid(int idRid) throws Exception;

	public int insertTesseraStorico(int idTessera) throws Exception;
	
	public int getAnnoInCorso() throws Exception;

	public int deleteStoricoAnno(int anno) throws Exception;

	public int insertStoricoAnno(int anno) throws Exception;

	public int aggiornaTesseraStorico(int idTessera) throws Exception;

	public int aggiornaTesseraStoricoByAnagrafica(int idAnagrafica)
			throws Exception;

	public int allineaQuoteTessera(java.sql.Date dataRif) throws Exception;
	
	public List<TesseraDTO> getTessereDaAllineare (java.sql.Date dataRif) throws Exception;

	public List<TesseraDTO> getTessereRendicontabiliManualmente(
			int idAnagrafica, int anno) throws Exception;

	public List<TesseraDTO> getTessereRendicontabiliManualmente(
			int idAnagrafica, int idTipoTessera, int anno) throws Exception;

	public List<TesseraDTO> getTesserePerAnagrafica(int idAnagrafica)
			throws Exception;

	public List<TesseraDTO> getTesserePerDistinta(int anno, int distinta)
			throws Exception;

	public int eliminaTessera(int idTessera) throws Exception;

	public int eliminaTesseraStorico(int idTessera) throws Exception;

	public int aggiornaTessera(TesseraDTO tessera) throws Exception;

	public int disattivaTessera(int idTessera) throws Exception;

	public int disattivaTesseraByAnagrafica(int idAnagrafica) throws Exception;

	public int getTesseraRendicontata(int idTessera) throws Exception;

	public int aggiornaTipoTessera(List<TipoTesseraDTO> tipoTessere)
			throws Exception;

	public int aggiornaImportoTessera(List<TipoTesseraDTO> tipoTessere)
			throws Exception;

	public int aggiornaImportoTesseraStorico(List<TipoTesseraDTO> tipoTessere)
			throws Exception;

	public List<TesseraStampataDTO> getStampaTessera(int idTipoTessera,
			Integer idTipoIncasso, List<Integer> idEsattori, int anno)
			throws Exception;

	public List<TesseraStampataDTO> getStampaTessera(int idTipoTessera,
			List<Integer> idAnagrafica, int anno) throws Exception;

	public List<TesseraStampataDTO> getStampaTessera(int idTipoTessera,
			Integer idTipoIncasso, Integer idTipoCarica, int anno)
			throws Exception;

	public List<TesseraStampataDTO> getStampaTessera(int idTipoTessera,
			Integer[] idTipoIncasso, int anno) throws Exception;

	public List<TesseraStampataDTO> getStampaTesseraFineAnno(int idTipoTessera,
			Integer[] idTipoIncasso, int anno) throws Exception;

	public List<TesseraStampataDTO> getTabulato(int idTipoTessera,
			Integer idTipoIncasso, Integer idTipoCarica, int anno)
			throws Exception;

	public List<TesseraDTO> getTessereNonPagantiUltimiAnni(int idTipoTessera,
			int annoDa,int annoA) throws Exception;

	public List<TesseraDTO> getTessereNonPagantiUltimiAnni(int idTipoTessera,
			int annoDa, int tipoIncasso,int annoA,Integer tipoEsattore) throws Exception;

}
