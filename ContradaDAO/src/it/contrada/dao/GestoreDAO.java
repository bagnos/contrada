package it.contrada.dao;

import it.contrada.dao.interfaces.IGestoreDAO;
import it.contrada.dto.GestoreDTO;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

public class GestoreDAO extends SqlSessionDaoSupport implements IGestoreDAO {

	@Override
	public List<GestoreDTO> getGestori() throws Exception {
		// TODO Auto-generated method stub
		return getSqlSessionTemplate().selectList(
				"it.contrada.gestore.queries.getGestori");
	}

	@Override
	public int insertGestore(GestoreDTO gestore) throws Exception {
		// TODO Auto-generated method stub
		return getSqlSessionTemplate().insert(
				"it.contrada.gestore.queries.insertGestore", gestore);
	}

	@Override
	public int deleteGestore(int idGestore) throws Exception {
		// TODO Auto-generated method stub
		HashMap<String , Integer> map=new HashMap<String, Integer>();
		map.put("idGestore", idGestore);
		return getSqlSessionTemplate().delete(
				"it.contrada.gestore.queries.deleteGestore", map);
	}

}
