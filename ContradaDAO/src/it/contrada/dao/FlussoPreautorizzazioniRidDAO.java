package it.contrada.dao;

import it.contrada.dao.interfaces.IFlussoPreautorizzazioniRidDAO;
import it.contrada.dominio.dto.TipoCasualiPreautDTO;
import it.contrada.dto.RidDTO;
import it.contrada.preautrid.dto.FlussoPreautInviatoDTO;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

public class FlussoPreautorizzazioniRidDAO extends SqlSessionDaoSupport
		implements IFlussoPreautorizzazioniRidDAO {

	@Override
	public int insertFlussoPreaut(List<Integer> rid) throws Exception {
		// TODO Auto-generated method stub
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("rid", rid);
		return getSqlSessionTemplate()
				.insert(
						"it.contrada.preautorizzazioni.queries.insertFlussoPreaut",
						map);
	}

	@Override
	public int aggiornaRicezioneFlusso(int idRid, Date dtInvio, Date dtEsito,
			int causale, int idFileEsito) throws Exception {
		// TODO Auto-generated method stub
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("idRid", idRid);
		map.put("dtInvio", dtInvio);
		map.put("dtEsito", dtEsito);
		map.put("causale", causale);
		map.put("idFileEsito", idFileEsito);

		return getSqlSessionTemplate().update(
				"it.contrada.preautorizzazioni.queries.aggiornaEsitoPreaut",
				map);
	}

	@Override
	public List<TipoCasualiPreautDTO> elencoCausaliPreaut() {
		// TODO Auto-generated method stub
		return getSqlSessionTemplate()
				.selectList(
						"it.contrada.preautorizzazioni.queries.selectTipoCausaliPreaut");
	}

	@Override
	public List<RidDTO> gerRidPreautorizzatiInviati(java.util.Date dtInvio)
			throws Exception {
		// TODO Auto-generated method stub
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("dtInvio", dtInvio);
		return getSqlSessionTemplate().selectList(
				"it.contrada.preautorizzazioni.queries.getRidPreautorizzati",
				map);
	}

	@Override
	public List<FlussoPreautInviatoDTO> getFlussoPreautorizzati(int anno)
			throws Exception {
		// TODO Auto-generated method stub
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("anno", anno);
		return getSqlSessionTemplate().selectList(
				"it.contrada.preautorizzazioni.queries.getFlussoPreautorizzati",
				map);
	}

	@Override
	public int getNrPreautorizzazioniToday() throws Exception {
		// TODO Auto-generated method stub
		return (Integer) getSqlSessionTemplate().selectOne("it.contrada.preautorizzazioni.queries.getNrPreautorizzazioniToday");
	}

	@Override
	public int eliminaFlussoPreautorizzazioni(java.util.Date dtInvio)
			throws Exception {
		// TODO Auto-generated method stub
		return getSqlSessionTemplate().delete("it.contrada.preautorizzazioni.queries.eliminaFlussoPreautorizzati");
	}
}
