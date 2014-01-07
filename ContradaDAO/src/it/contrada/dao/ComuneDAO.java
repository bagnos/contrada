package it.contrada.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import it.contrada.dao.ibatis.Connection;
import it.contrada.dao.interfaces.IComuneDAO;
import it.contrada.dto.ComuneDTO;
import it.contrada.dto.NazioneDTO;

public class ComuneDAO extends SqlSessionDaoSupport implements IComuneDAO {

	@Override
	public List<ComuneDTO> getComune(int cdProvincia) throws Exception {
		// TODO Auto-generated method stub
		List<ComuneDTO> comuni = null;

		HashMap map = new HashMap();
		map.put("cdProvincia", cdProvincia);
		comuni = getSqlSessionTemplate().selectList(
				"it.contrada.queries.getComune", map);

		return comuni;
	}

	@Override
	public List<ComuneDTO> getComune(String cdStato) throws Exception {
		// TODO Auto-generated method stub
		List<ComuneDTO> comuni = null;

		HashMap map = new HashMap();
		map.put("cdStato", cdStato);
		comuni = getSqlSessionTemplate().selectList(
				"it.contrada.queries.getComuneByStato", map);

		return comuni;

	}

}
