package it.contrada.dao.interfaces;

import it.contrada.dto.GestoreDTO;

import java.util.List;

public interface IGestoreDAO {
	public List<GestoreDTO> getGestori() throws Exception;
}
