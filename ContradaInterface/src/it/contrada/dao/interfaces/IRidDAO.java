package it.contrada.dao.interfaces;

import it.contrada.dominio.dto.TipoIncassoDTO;
import it.contrada.dominio.dto.TipoStatoRidDTO;
import it.contrada.dto.MembroRidDTO;
import it.contrada.dto.RidDTO;
import it.contrada.enumcontrada.TipoStatoRid;

import java.util.List;

public interface IRidDAO {
	public List<MembroRidDTO> getMembroPerCognome(String cognome)
			throws Exception;;

	public List<MembroRidDTO> getMembro(String cognome, String nome)
			throws Exception;

	public List<MembroRidDTO> getMembroPerCognomeParziale(String matchCognome)
			throws Exception;
	
	public List<MembroRidDTO> getMembroPerCognomeNomeParziale(String cognome,String nome)
	throws Exception;

	public List<MembroRidDTO> getMembro(int idRid) throws Exception;

	public List<TipoStatoRidDTO> getStatiRid() throws Exception;;

	public int insertRid(RidDTO ridDTO) throws Exception;;

	public int updateRid(RidDTO ridDTO) throws Exception;;

	public RidDTO getRid(int idRid) throws Exception;

	public List<RidDTO> getRidCensiti() throws Exception;
	
	public List<RidDTO> getRidPerStato(List<Integer> cdStato) throws Exception;

	public int aggiornaStatoRid(TipoStatoRid stato, TipoStatoRid statoPrec)
			throws Exception;
	
	public int aggiornaNote(int idRid, String note) throws Exception;

	public int aggiornaStatoRidPerIdRid(int idRid, TipoStatoRid stato,
			String note) throws Exception;;

	public int aggiornaStatoRidPerListRid(List<Integer> rid,
			TipoStatoRid stato, TipoStatoRid statoPrec) throws Exception;;

	public int aggiornaCoordinateBancarie(int idRid, int abi, int cab,
			String conto, String paese, int cinEuropeo, String cin, String note, String intestatario)
			throws Exception;;

	public int aggiornaStatoRidPerIdFlussoAddebito(int stato,
			long idFlussoAddebito,String note) throws Exception; 
	
	public int aggiornaNoteRidPerIdFlussoAddebito(long idFlussoAddebito,String note) throws Exception;

	public List<TipoIncassoDTO> elencaTipoIncassi() throws Exception;
	
	public RidDTO getSchedaRid(int idRid) throws Exception;
		

		
		
		

		
		
		

}
