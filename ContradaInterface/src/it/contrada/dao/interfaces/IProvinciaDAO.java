package it.contrada.dao.interfaces;

import it.contrada.dto.ProvinciaDTO;

import java.util.List;

public interface IProvinciaDAO {
	public List<ProvinciaDTO> getProvincia(int cdRegione) throws Exception;
	public List<ProvinciaDTO> getProvinciaPerStato(String cdIsoStato) throws Exception;
	public List<ProvinciaDTO> getAll() throws Exception;
}
