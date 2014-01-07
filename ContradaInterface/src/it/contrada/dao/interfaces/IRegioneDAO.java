package it.contrada.dao.interfaces;

import it.contrada.dto.RegioneDTO;

import java.util.List;

public interface IRegioneDAO {
	public List<RegioneDTO> getRegione(String cdStato) throws Exception;
}
