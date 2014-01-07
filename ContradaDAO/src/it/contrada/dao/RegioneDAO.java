package it.contrada.dao;
 
import it.contrada.dao.interfaces.IRegioneDAO;
import it.contrada.dto.RegioneDTO;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

public class RegioneDAO extends SqlSessionDaoSupport implements IRegioneDAO {

	@Override
	public List<RegioneDTO> getRegione(String cdStato) throws Exception {
		// TODO Auto-generated method stub

		List<RegioneDTO> regioni = null;
		HashMap map = new HashMap();
		map.put("cdStato", cdStato);
		regioni = getSqlSessionTemplate().selectList(
				"it.contrada.queries.getRegione", map);

		return regioni;
	}

}
