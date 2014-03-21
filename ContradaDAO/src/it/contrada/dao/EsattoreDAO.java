package it.contrada.dao;

import it.contrada.dao.interfaces.IEsattoreDAO;
import it.contrada.dto.EsattoreDTO;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

public class EsattoreDAO extends SqlSessionDaoSupport implements IEsattoreDAO {

	@Override
	public List<EsattoreDTO> getEsattore() throws Exception {
		// TODO Auto-generated method stub
		List<EsattoreDTO> esattori = null;

		esattori = getSqlSessionTemplate().selectList(
				"it.contrada.esattore.queries.getEsattore");

		return esattori;

	}

	@Override
	public int insertEsattore(EsattoreDTO esattore )
			throws Exception {
		// TODO Auto-generated method stub
		return getSqlSessionTemplate().insert(
				"it.contrada.esattore.queries.insertEsattore",esattore);
	}

	

	@Override
	public int deleteEsattore(int idEsattore) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

}
