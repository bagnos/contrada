package it.contrada.dao.interfaces;


import it.contrada.dto.AnagraficaDTO;
import it.contrada.spinoff.dto.ComuneDTO;
import it.contrada.spinoff.dto.ComuneNewDTO;
import it.contrada.spinoff.dto.ProtettoreDTO;
import it.contrada.spinoff.dto.ViaDTO;

import java.util.List;

public interface ISpinOffDAO {
	public List<ProtettoreDTO> getProtettori() throws Exception;
	public ViaDTO getVia(long cdVia) throws Exception;
	public int getCapProv(String cdCap,int idProvincia) throws Exception;
	public int getCapByComProv(int idProvincia,int idComune,String cdCap) throws Exception;
	public int insertCapOld(long cap,long progCap,String localita,String comune,String provincia) throws Exception;
	public Integer getMaxCapProg(long cap) throws Exception;
	public String getCapByComProv(int idProvincia,int idComune) throws Exception;
	
	
	public ComuneDTO getComune(long Cap,long  ProgCap) throws Exception;
	public Integer getStrada(String dsStrada,String localita,String capPost, long cdProvincia, long cdComune) throws Exception;
	
	public List<ComuneNewDTO> getComune(String dsComune, long Provincia) throws Exception;
	public Integer getProvincia(String dsProvincia) throws Exception;
	public AnagraficaDTO inserisciAnagrafica(AnagraficaDTO anagraficaDTO) throws Exception ;
	/**
	 * 
	 * @param cdCapNew
	 * @param capProgNew
	 * @param cdCapOld
	 * @param capProg
	 * @return
	 * @throws Exception
	 */
	public int updateCapProtettori(String cdCapNew,int capProgNew,String cdCapOld,int capProg) throws Exception ;
	public int updateCapComune(String cdCapNew,String cdCapOld,int capProg) throws Exception ;
}
