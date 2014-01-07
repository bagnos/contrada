package it.contrada.dao;

import it.contrada.dao.ibatis.Connection;
import it.contrada.dao.interfaces.IIncassoDAO;
import it.contrada.dominio.dto.TipoIncassoDTO;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;

public class IncassoDAO extends SqlSessionDaoSupport implements IIncassoDAO {

	@Override
	public List<TipoIncassoDTO> getTipoIncasso() throws Exception {
		// TODO Auto-generated method stub
		List<TipoIncassoDTO> tipoIncasso = null;

		tipoIncasso = getSqlSessionTemplate().selectList(
				"it.contrada.queries.getTipoIncasso");

		return tipoIncasso;

	}

}
