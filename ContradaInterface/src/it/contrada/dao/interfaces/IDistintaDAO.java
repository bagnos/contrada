package it.contrada.dao.interfaces;

import it.contrada.dto.DistintaDTO;

import java.util.List;

public interface IDistintaDAO {
	public int insertDistinta(DistintaDTO distinta) throws Exception;

	public int getMaxAnno(int nrAnno) throws Exception;

	public List<DistintaDTO> getDistinta(int nrAnno) throws Exception;
}
