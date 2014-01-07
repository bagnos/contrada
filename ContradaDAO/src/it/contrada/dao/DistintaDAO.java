package it.contrada.dao;

import java.util.HashMap;
import java.util.List;

import it.contrada.dao.interfaces.IDistintaDAO;
import it.contrada.dto.DistintaDTO;

import org.mybatis.spring.support.SqlSessionDaoSupport;

public class DistintaDAO extends SqlSessionDaoSupport implements IDistintaDAO{

	@Override
	public int insertDistinta(DistintaDTO distinta) throws Exception {
		// TODO Auto-generated method stub
		return getSqlSessionTemplate().insert(
				"it.contrada.distinta.queries.insertDistinta", distinta);
	}

	@Override
	public int getMaxAnno(int nrAnno) throws Exception {
		// TODO Auto-generated method stub
		return Integer.parseInt(getSqlSessionTemplate().selectOne(
				"iit.contrada.distinta.queries.getMaxDistinta", nrAnno).toString());
	}

	@Override
	public List<DistintaDTO> getDistinta(int nrAnno) throws Exception {
		// TODO Auto-generated method stub
		HashMap<String, Integer> map=new HashMap<String, Integer>();
		map.put("nrAnno", nrAnno);
		return getSqlSessionTemplate().selectList("it.contrada.distinta.queries.getDistinta", map);
	}

}
