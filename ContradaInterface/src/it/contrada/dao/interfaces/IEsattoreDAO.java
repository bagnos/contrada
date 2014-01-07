package it.contrada.dao.interfaces;

import it.contrada.dto.EsattoreDTO;

import java.util.List;

public interface IEsattoreDAO {
	public List<EsattoreDTO> getEsattore() throws Exception;
}
