package it.contrada.dao.interfaces;

import it.contrada.dto.ComuneDTO;

import java.util.List;

public interface IComuneDAO {
	public List<ComuneDTO> getComune(int cdProvincia)  throws Exception;
	public List<ComuneDTO> getComune(String cdStato)  throws Exception;
}
