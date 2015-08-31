package it.contrada.dao;

import it.contrada.dao.interfaces.IStradaDAO;
import it.contrada.dto.ComuneDTO;
import it.contrada.dto.LocalitaDTO;
import it.contrada.dto.ProvinciaDTO;
import it.contrada.dto.StradaDTO;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

public class StradaDAO extends SqlSessionDaoSupport implements IStradaDAO {

	@Override
	public List<StradaDTO> getStrada(String cdCap) throws Exception {
		// TODO Auto-generated method stub
		List<StradaDTO> strade = null;
		HashMap map = new HashMap();
		map.put("cdCap", cdCap);
		strade = getSqlSessionTemplate().selectList(
				"it.contrada.stradario.queries.getStrada", map);

		return strade;

	}

	@Override
	public List<StradaDTO> getStrada(String cdCap, String matchVia)
			throws Exception {
		// TODO Auto-generated method stub
		List<StradaDTO> strade = null;
		HashMap map = new HashMap();
		map.put("cdCap", cdCap);
		map.put("matchVia", "%"+matchVia+"%");
		strade = getSqlSessionTemplate().selectList(
				"it.contrada.stradario.queries.getStrada", map);

		return strade;
	}

	@Override
	public int insertStrada(StradaDTO strada) throws Exception {
		// TODO Auto-generated method stub
		return getSqlSessionTemplate().insert(
				"it.contrada.stradario.queries.insertStrada", strada);
	}

	@Override
	public int insertLocalita(LocalitaDTO localita) throws Exception {
		// TODO Auto-generated method stub
		 return getSqlSessionTemplate().insert(
				"it.contrada.stradario.queries.insertLocalita", localita);
	}

	@Override
	public List<LocalitaDTO> getLocalita(String cdCap, int cdProvincia,
			int cdComune) throws Exception {
		// TODO Auto-generated method stub
		List<LocalitaDTO> localita = null;
		HashMap map = new HashMap();
		map.put("cdCap", cdCap);
		map.put("cdProvincia", cdProvincia);
		map.put("cdComune", cdComune);
		localita = getSqlSessionTemplate().selectList(
				"it.contrada.stradario.queries.getLocalita", map);

		return localita;

	}

	@Override
	public List<StradaDTO> getStrada(String cdCap, Integer idLoc,
			String matchVia) throws Exception {
		// TODO Auto-generated method stub
		List<StradaDTO> strade = null;
		HashMap map = new HashMap();
		map.put("cdCap", cdCap);
		map.put("idLoc", idLoc);
		map.put("matchVia", "%"+matchVia+"%");
		strade = getSqlSessionTemplate().selectList(
				"it.contrada.stradario.queries.getStradaPerLoc", map);

		return strade;
	}

	@Override
	public int insertProvincia(ProvinciaDTO provincia)
			throws Exception {
		// TODO Auto-generated method stub
		return getSqlSessionTemplate().insert(
				"it.contrada.stradario.queries.insertProvincia", provincia);
	}

	@Override
	public int getMaxProvincia() throws Exception {
		// TODO Auto-generated method stub
		return (Integer) getSqlSessionTemplate().selectOne("it.contrada.stradario.queries.getMaxProvincia");
	}

	@Override
	public int insertComune(ComuneDTO comune) throws Exception {
		// TODO Auto-generated method stub
		return getSqlSessionTemplate().insert(
				"it.contrada.stradario.queries.insertComune", comune);
	}

	@Override
	public int getMaxComune(int cdProvincia) throws Exception {
		// TODO Auto-generated method stub
		HashMap<String, Integer> map=new HashMap<String, Integer>();
		map.put("cdProvincia", cdProvincia);
		
		return (Integer) getSqlSessionTemplate().selectOne("it.contrada.stradario.queries.getMaxProvincia");
	}


}
