package it.contrada.dao;

import it.contrada.dao.interfaces.IProvinciaDAO;
import it.contrada.dto.ProvinciaDTO;

import java.util.HashMap;
import java.util.List;
import org.mybatis.spring.support.SqlSessionDaoSupport;

public class ProvinciaDAO extends SqlSessionDaoSupport implements IProvinciaDAO {

	@Override
	public List<ProvinciaDTO> getProvincia(int cdRegione) throws Exception {
		// TODO Auto-generated method stub
		List<ProvinciaDTO> provincie = null;
		HashMap map = new HashMap();
		map.put("cdRegione", cdRegione);
		provincie = getSqlSessionTemplate().selectList(
				"it.contrada.queries.getProvincia", map);

		return provincie;
	}

	@Override
	public List<ProvinciaDTO> getProvinciaPerStato(String cdIsoStato)
			throws Exception {
		// TODO Auto-generated method stub
		List<ProvinciaDTO> provincie = null;

		HashMap map = new HashMap();
		map.put("cdIsoStato", cdIsoStato);
		provincie = getSqlSessionTemplate().selectList(
				"it.contrada.queries.getProvinciaPerStato", map);

		return provincie;

	}

	@Override
	public List<ProvinciaDTO> getAll() throws Exception {
		// TODO Auto-generated method stub
		List<ProvinciaDTO> provincie = null;

		provincie = getSqlSessionTemplate().selectList(
				"it.contrada.queries.getAllProvincia");

		return provincie;

	}

	
}
