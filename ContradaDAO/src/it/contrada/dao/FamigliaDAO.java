package it.contrada.dao;


import it.contrada.dao.interfaces.IFamigliaDAO;
import it.contrada.dto.FamigliaDTO;
import it.contrada.dto.MembroFamigliaDTO;
import it.contrada.exceptions.ContradaExceptionBloccante;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.mybatis.spring.support.SqlSessionDaoSupport;

public class FamigliaDAO extends SqlSessionDaoSupport implements IFamigliaDAO {

	private static Log log = LogFactory.getLog(FamigliaDAO.class);

	@Override
	public FamigliaDTO inserisciFamiglia(Integer idCapoFamiglia)
			throws Exception {
		// TODO Auto-generated method stub

		FamigliaDTO famiglia = new FamigliaDTO();
		famiglia.setIdCapoFamiglia(idCapoFamiglia);

		int rowsAffected = getSqlSessionTemplate().insert(
				"it.contrada.famiglia.queries.insertFamiglia", famiglia);

		if (rowsAffected == 0) {
			throw new ContradaExceptionBloccante(
					"nessuna nuva Famiglia inserita ");
		}

		return famiglia;

	}

	@Override
	public List<MembroFamigliaDTO> getMembro(String cognome, String nome)
			throws Exception {
		// TODO Auto-generated method stub

		HashMap map = new HashMap();
		map.put("cognome", cognome);
		map.put("nome", nome);

		return getSqlSessionTemplate().selectList("it.contrada.famiglia.queries.getMembroFamiglia", map);

	}

	@Override
	public List<MembroFamigliaDTO> getMembroPerCognome(String cognome)
			throws Exception {
		// TODO Auto-generated method stub

		HashMap map = new HashMap();
		map.put("cognome", cognome);

		return getSqlSessionTemplate().selectList(
				"it.contrada.famiglia.queries.getMembroFamiglia", map);

	}

	@Override
	public List<MembroFamigliaDTO> getMembro(int cdFamiglia) throws Exception {
		// TODO Auto-generated method stub

		HashMap map = new HashMap();
		map.put("cdFamiglia", cdFamiglia);

		return getSqlSessionTemplate()
				.selectList("it.contrada.famiglia.queries.getMembroFamiglia", map);

	}

	@Override
	public int aggiornaFamiglia(FamigliaDTO famiglia) throws Exception {
		// TODO Auto-generated method stub


		return getSqlSessionTemplate()
				.update("it.contrada.famiglia.queries.updateFamiglia", famiglia);
		
	}

	@Override
	public List<MembroFamigliaDTO> getMembroPerCognomeParziale(String matchCognome) throws Exception {
		// TODO Auto-generated method stub
		HashMap map = new HashMap();
		map.put("matchCognome", matchCognome+"%");
		return getSqlSessionTemplate()
		.selectList("it.contrada.famiglia.queries.getFamiglieParzialePerCognome",map);

	}

	@Override
	public List<MembroFamigliaDTO> getAnagraficaParzialePerCognomeNome(
			String cognome, String nome) throws Exception {
		// TODO Auto-generated method stub
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("matchCognome", cognome + "%");
		map.put("nome", nome + "%");
		return getSqlSessionTemplate()
				.selectList(
						"it.contrada.famiglia.queries.getFamiglieParzialePerCognome",
						map);

	}

}
