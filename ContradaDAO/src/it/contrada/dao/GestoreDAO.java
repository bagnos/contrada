package it.contrada.dao;

import it.contrada.dao.interfaces.IGestoreDAO;
import it.contrada.dto.GestoreDTO;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

public class GestoreDAO extends SqlSessionDaoSupport implements IGestoreDAO {

	@Override
	public List<GestoreDTO> getGestori() throws Exception {
		// TODO Auto-generated method stub
		return getSqlSessionTemplate().selectList(
		"it.contrada.gestore.queries.getGestori");
	}
	
}
