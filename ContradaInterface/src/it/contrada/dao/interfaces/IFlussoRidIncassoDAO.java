package it.contrada.dao.interfaces;

import it.contrada.dominio.dto.TipoCasualiIncassoRidDTO;
import it.contrada.enumcontrada.TipoIncassoRid;
import it.contrada.enumcontrada.TipoMeseIncasso;
import it.contrada.incassorid.dto.IncassoRidDTO;

import java.util.List;

public interface IFlussoRidIncassoDAO {
	public int insertIncassoRid(int anno, int mese,
			int idTipoIncassoRid,java.sql.Date dtValuta) throws Exception;;

	public int existFlusso(int anno, int mese,
			int idTipoIncassoRid) throws Exception;;

	public List<IncassoRidDTO> getIncassiDaInviare(int anno,
			int mese, int idTipoIncassoRid)
			throws Exception;;

	public int insertFlussoRid(int anno, int mese,
			java.sql.Date dtValuta, int idTipoIncassoRid,
			String nomeFileFlusso, long idOperazione) throws Exception;

	public int eliminaFlussoRid(int anno, int mese,
			int idTipoIncassoRid) throws Exception;

	public int eliminaIncassoRid(int anno, int mese,
			int idTipoIncassoRid) throws Exception;

	public String getNomeFileFlusso(int anno, int mese,
			int idTipoIncassoRid) throws Exception;

	public List<TipoCasualiIncassoRidDTO> elencoCausaliIncassoRid()  throws Exception;;
	
	public int aggiornaEsitoRid(long idFlussoAddebito, java.sql.Date dtEsito, int idRidEsito, int idFlussoEsito)  throws Exception;
	
	public int aggiornaEsitoRid(long idRid, java.sql.Date dtValuta,java.sql.Date dtEsito, int idRidEsito, int idFlussoEsito)  throws Exception;
	
	public long getIdFlussoAddebito(long idRid, java.sql.Date dtValuta) throws Exception;
}