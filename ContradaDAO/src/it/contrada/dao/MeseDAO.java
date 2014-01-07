package it.contrada.dao;

import java.util.List;

import it.contrada.dao.interfaces.IMeseDAO;
import it.contrada.dominio.dto.TipoMeseDTO;
import it.contrada.enumcontrada.TipoIncassoRid;

import org.mybatis.spring.support.SqlSessionDaoSupport;

public class MeseDAO extends SqlSessionDaoSupport implements IMeseDAO {

	@Override
	public List<TipoMeseDTO> ricercaMese(int incasso)
			throws Exception {
		// TODO Auto-generated method stub
		return getSqlSessionTemplate().selectList(
				"it.contrada.mese.queries.getMesiIncassoPerTipORid", incasso);
	}

	@Override
	public List<TipoMeseDTO> elencaMese() throws Exception {
		// TODO Auto-generated method stub
		return getSqlSessionTemplate().selectList(
				"it.contrada.mese.queries.elencaMesi");
	}

}
