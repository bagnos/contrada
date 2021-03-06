package it.contrada.dao.interfaces;

import it.contrada.dto.ComuneDTO;
import it.contrada.dto.LocalitaDTO;
import it.contrada.dto.ProvinciaDTO;
import it.contrada.dto.StradaDTO;

import java.util.List;

public interface IStradaDAO {
	public List<StradaDTO> getStrada(String cdCap) throws Exception;
	public List<StradaDTO> getStrada(String cdCap, String matchVia) throws Exception;
	public List<StradaDTO> getStrada(String cdCap, Integer idLoc,String matchVia) throws Exception;
	public int insertStrada(StradaDTO strada) throws Exception;
	public int insertLocalita(LocalitaDTO localita) throws Exception;
	public List<LocalitaDTO> getLocalita(String cdCap, int cdProvincia, int cdComune) throws Exception;
	public int insertProvincia(ProvinciaDTO provincia) throws Exception;
	public int getMaxProvincia()  throws Exception;
	public int insertComune(ComuneDTO comune) throws Exception;
	public int getMaxComune(int cdProvincia)  throws Exception;
}
