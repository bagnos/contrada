package it.contrada.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import it.contrada.dao.ibatis.Connection;
import it.contrada.dao.interfaces.IEsattoreDAO;

import it.contrada.dto.EsattoreDTO;

public class EsattoreDAO extends SqlSessionDaoSupport implements IEsattoreDAO {

	@Override
	public List<EsattoreDTO> getEsattore() throws Exception {
		// TODO Auto-generated method stub
		List<EsattoreDTO> esattori = null;

		esattori = getSqlSessionTemplate().selectList(
				"it.contrada.queries.getEsattore");

		return esattori;

	}

}
