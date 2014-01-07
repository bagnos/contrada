package it.contrada.dao;

import it.contrada.dao.interfaces.IOperazioneDAO;
import it.contrada.dto.OperazioneDTO;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

public class OperazioneDAO extends SqlSessionDaoSupport implements IOperazioneDAO {

	@Override
	public int inserisciOperazione(OperazioneDTO operazione) {
		// TODO Auto-generated method stub
		return getSqlSessionTemplate().insert("it.contrada.queries.operazione.insertOperazione",operazione);
	}

	@Override
	public List<OperazioneDTO> getUltimeOperazioni(int nRows) throws Exception {
		// TODO Auto-generated method stub
		HashMap<String,Integer> map=new HashMap<String, Integer>();
		map.put("nRows", nRows);
		return getSqlSessionTemplate().selectList("it.contrada.queries.operazione.getUltimeOperazioni",map);
	}

}
