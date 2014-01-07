package it.contrada.dao;

import java.util.HashMap;

import it.contrada.dao.interfaces.IUtenteDAO;

import org.mybatis.spring.support.SqlSessionDaoSupport;

public class UtenteDAO extends SqlSessionDaoSupport implements IUtenteDAO {

	@Override
	public int updatePsw(String utente, String vecchiaPsw, String nuovaPsw
			) throws Exception {
		// TODO Auto-generated method stub
		HashMap<String, String> map=new HashMap<String, String>();
		map.put("utente", utente);
		map.put("vecchiaPsw", vecchiaPsw);
		map.put("nuovaPsw", nuovaPsw);		
		return getSqlSessionTemplate().update("it.contrada.utente.queries.updatePsw", map);
	}

}
