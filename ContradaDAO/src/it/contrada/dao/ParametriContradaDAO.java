package it.contrada.dao;

import it.contrada.dao.interfaces.IParametriContradaDAO;
import it.contrada.dto.ParametriContradaDTO;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

public class ParametriContradaDAO extends SqlSessionDaoSupport implements IParametriContradaDAO {

	@Override
	public ParametriContradaDTO getParametri() throws Exception {
		// TODO Auto-generated method stub
		
		List<ParametriContradaDTO> params= getSqlSessionTemplate().selectList(
		"it.contrada.parametricontrada.queries.getParametri");
		if (!params.isEmpty())
		{
			return params.get(0);
		}
		else
		{
			return null;
		}
	}

}
