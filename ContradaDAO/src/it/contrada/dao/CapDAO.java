package it.contrada.dao;

import it.contrada.dao.interfaces.ICapDAO;
import it.contrada.dto.CapDTO;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;

public class CapDAO extends SqlSessionDaoSupport implements ICapDAO {

	@Override
	public List<CapDTO> recuperaCap(int cdProvincia, int cdComune)
			throws Exception {
		// TODO Auto-generated method stub
		List<CapDTO> caps = null;

		HashMap map = new HashMap();
		map.put("cdProvincia", cdProvincia);
		map.put("cdComune", cdComune);
		caps = getSqlSessionTemplate().selectList("it.contrada.queries.getCap",
				map);

		return caps;

	}

	@Override
	public List<CapDTO> recuperaParzialeCap(String matchCap,int cdProvincia) throws Exception {
		// TODO Auto-generated method stub
		List<CapDTO> caps = null;

		HashMap map = new HashMap();
		map.put("matchCap", "%"+matchCap+"%");		
		map.put("cdProvincia", cdProvincia);
		caps = getSqlSessionTemplate().selectList("it.contrada.cap.getCapParziale",
				map);

		return caps;

	}

	@Override
	public int insertCAP(CapDTO cap) throws Exception {
		// TODO Auto-generated method stub
		
		return getSqlSessionTemplate().insert("it.contrada.cap.insertCap",
				cap);
	}

}
