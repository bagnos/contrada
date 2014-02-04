package it.contrada.dao.interfaces;

import it.contrada.dto.GestoreDTO;

import java.util.List;

public interface IGestoreDAO {
	public List<GestoreDTO> getGestori() throws Exception;
	public int insertGestore(GestoreDTO gestore) throws Exception;
	public int deleteGestore(int idGestore) throws Exception;
}
