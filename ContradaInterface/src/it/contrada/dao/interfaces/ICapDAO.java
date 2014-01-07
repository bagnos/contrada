package it.contrada.dao.interfaces;

import it.contrada.dto.CapDTO;

import java.util.List;

public interface ICapDAO {
	public List<CapDTO> recuperaCap(int cdProvincia, int cdComune)
			throws Exception;

	public List<CapDTO> recuperaParzialeCap(String matchCap, int cdProvincia)
			throws Exception;

	public int insertCAP(CapDTO cap) throws Exception;
}
