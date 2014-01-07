package it.contrada.dao;

import it.contrada.dao.ibatis.Connection;
import it.contrada.dao.interfaces.INazioneDAO;
import it.contrada.dto.NazioneDTO;
import it.contrada.exceptions.ContradaExceptionBloccante;
import it.contrada.exceptions.ContradaExceptionNonBloccante;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;

public class NazioneDAO extends SqlSessionDaoSupport implements INazioneDAO {

	@Override
	public List<NazioneDTO> getNazione() throws Exception {
		// TODO Auto-generated method stub

		List<NazioneDTO> nazioni = null;

		nazioni = getSqlSessionTemplate().selectList(
				"it.contrada.queries.getNazione");

		return nazioni;

	}

}
