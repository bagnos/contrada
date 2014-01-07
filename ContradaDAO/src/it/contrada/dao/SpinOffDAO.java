package it.contrada.dao;

import it.contrada.dao.interfaces.ISpinOffDAO;
import it.contrada.dto.AnagraficaDTO;
import it.contrada.spinoff.dto.ComuneDTO;
import it.contrada.spinoff.dto.ComuneNewDTO;
import it.contrada.spinoff.dto.ProtettoreDTO;
import it.contrada.spinoff.dto.ViaDTO;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

public class SpinOffDAO extends SqlSessionDaoSupport implements ISpinOffDAO {

	@Override
	public List<ProtettoreDTO> getProtettori() throws Exception {
		// TODO Auto-generated method stub
		return getSqlSessionTemplate().selectList(
				"it.contrada.spinoff.queries.getProtettori");
	}

	@Override
	public ViaDTO getVia(long cdVia) throws Exception {
		// TODO Auto-generated method stub
		List<ViaDTO> vie = getSqlSessionTemplate().selectList(
				"it.contrada.spinoff.queries.getVia", cdVia);
		;
		if (vie != null && !vie.isEmpty()) {
			return vie.get(0);
		} else {
			return null;
		}
	}

	@Override
	public Integer getStrada(String dsStrada, String dsLocalita,
			String capPost, long cdProvincia, long cdComune) throws Exception {
		// TODO Auto-generated method stub
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("dsStrada", "%" + dsStrada + "%");
		map.put("dsLocalita", dsLocalita);
		map.put("capPost", capPost);
		map.put("cdProvincia", cdProvincia);
		map.put("cdComune", cdComune);

		List<Integer> strade = getSqlSessionTemplate().selectList(
				"it.contrada.spinoff.queries.getStrada", map);
		if (strade != null && strade.size() > 0) {
			return strade.get(0);
		} else {
			return null;
		}
	}

	@Override
	public List<ComuneNewDTO> getComune(String dsComune, long provincia)
			throws Exception {
		// TODO Auto-generated method stub
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("dsComune", dsComune);
		map.put("provincia", provincia);
		List<ComuneNewDTO> comuni = getSqlSessionTemplate().selectList(
				"it.contrada.spinoff.queries.getIdComune", map);
		return comuni;

	}

	@Override
	public Integer getProvincia(String sigla) throws Exception {
		// TODO Auto-generated method stub

		Object prov = getSqlSessionTemplate().selectOne(
				"it.contrada.spinoff.queries.getIdProvincia", sigla);
		if (prov == null) {
			return null;
		} else {
			return (Integer) prov;
		}
	}

	@Override
	public ComuneDTO getComune(long Cap, long ProgCap) throws Exception {
		// TODO Auto-generated method stub
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("Cap", Cap);
		map.put("ProgCap", ProgCap);
		List<ComuneDTO> comuni = getSqlSessionTemplate().selectList(
				"it.contrada.spinoff.queries.getComune", map);
		if (comuni != null && !comuni.isEmpty()) {
			return comuni.get(0);
		} else {
			return null;
		}
	}

	@Override
	public int getCapProv(String cdCap,int idProvincia) throws Exception {
		// TODO Auto-generated method stub
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("idProvincia", idProvincia);		;
		map.put("cdCap", cdCap);
		
		return (Integer)getSqlSessionTemplate().selectOne(
				"it.contrada.spinoff.queries.getCapProv", map);
	}

	@Override
	public AnagraficaDTO inserisciAnagrafica(AnagraficaDTO anagraficaDTO)
			throws Exception {
		// TODO Auto-generated method stub

		/*
		 * sqlMap = Connection.getSqlSessionFactory();
		 * 
		 * SqlSession session = sqlMap.openSession();
		 */

		int rowsAffected = getSqlSessionTemplate().insert(
				"it.contrada.spinoff.queries.insertAnagrafica",
				anagraficaDTO);

		return anagraficaDTO;

	}

	@Override
	public int getCapByComProv(int idProvincia, int idComune, String cdCap)
			throws Exception {
		// TODO Auto-generated method stub
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("idProvincia", idProvincia);
		map.put("idComune", idComune);
		map.put("cdCap", cdCap);
		
		return (Integer)getSqlSessionTemplate().selectOne(
				"it.contrada.spinoff.queries.getCapByComProvCap", map);
	}

	@Override
	public String getCapByComProv(int idProvincia, int idComune)
			throws Exception {
		// TODO Auto-generated method stub
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("idProvincia", idProvincia);
		map.put("idComune", idComune);
	
		
		return (String)getSqlSessionTemplate().selectOne(
				"it.contrada.spinoff.queries.getMaxCapByComProvCap", map);
	}

	@Override
	public int updateCapProtettori(String cdCapNew,int capProgNew, String cdCapOld, int capProg)
			throws Exception {
		// TODO Auto-generated method stub
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("cdCapNew", cdCapNew);
		map.put("cdCapOld", cdCapOld);
		map.put("capProg", capProg);
		map.put("capProgNew", capProgNew);
	
		
		return getSqlSessionTemplate().update(
				"it.contrada.spinoff.queries.updateCapProt", map);
	}

	@Override
	public int updateCapComune(String cdCapNew, String cdCapOld, int capProg)
			throws Exception {
		// TODO Auto-generated method stub
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("cdCapNew", cdCapNew);
		map.put("cdCapOld", cdCapOld);
		map.put("capProg", capProg);
	
		
		return getSqlSessionTemplate().update(
				"it.contrada.spinoff.queries.updateCapComune", map);
	}

	@Override
	public Integer getMaxCapProg(long cap) throws Exception {
		// TODO Auto-generated method stub
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("Cap", cap);
		return (Integer)getSqlSessionTemplate().selectOne(
				"it.contrada.spinoff.queries.getMaxCapProg", map);
	}

	@Override
	public int insertCapOld(long cap, long progCap, String localita,
			String comune, String provincia) throws Exception {
		// TODO Auto-generated method stub
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("Cap", cap);
		map.put("ProgCap", progCap);
		map.put("Localita", localita);
		map.put("Comune", comune);
		map.put("Provincia", provincia);
		
		
		return getSqlSessionTemplate().insert(
				"it.contrada.spinoff.queries.insertCapOld", map);
	}

}
