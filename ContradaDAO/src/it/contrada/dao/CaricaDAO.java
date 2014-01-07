package it.contrada.dao;

import it.contrada.dao.interfaces.ICaricaDAO;
import it.contrada.dominio.dto.TipoCaricaDTO;
import it.contrada.dominio.dto.TipoTesseraDTO;
import it.contrada.dto.CaricaTesseraDTO;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

public class CaricaDAO extends SqlSessionDaoSupport implements ICaricaDAO {

	@Override
	public List<CaricaTesseraDTO> getCaricaPerTipo(int idTipoTessera)
			throws Exception {
		// TODO Auto-generated method stub
		List<CaricaTesseraDTO> tipoCariche = null;

		HashMap map = new HashMap();
		map.put("idTessera", idTipoTessera);
		tipoCariche = getSqlSessionTemplate().selectList(
				"it.contrada.queries.getCaricaPerTessera", map);

		return tipoCariche;

	}

	@Override
	public List<TipoCaricaDTO> getCariche() throws Exception {
		// TODO Auto-generated method stub
		return getSqlSessionTemplate().selectList(
				"it.contrada.queries.getCariche");
	}

	@Override
	public int aggiornaCarica(List<TipoCaricaDTO> cariche) throws Exception {
		// TODO Auto-generated method stub
		int rows=0;
		for (TipoCaricaDTO carica : cariche) {
			rows+=getSqlSessionTemplate().update(
					"it.contrada.tessera.queries.aggiornaCarica", carica);
		}
		return rows;
	}

}
