package it.contrada.dao;

import it.contrada.common.util.LogUtil;
import it.contrada.dao.interfaces.IFlussoEsitiDAO;
import it.contrada.dto.FlussoEsitoDTO;
import it.contrada.enumcontrada.TipoFlusso;
import it.contrada.incassorid.dto.IncassoRidDTO;
import it.contrada.preautrid.dto.RidEsitoPreautDTO;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;

public class FlussoEsitoDAO extends SqlSessionDaoSupport implements
		IFlussoEsitiDAO {

	private static Log log = LogFactory.getLog(FlussoEsitoDAO.class);

	@Override
	public FlussoEsitoDTO insertFlussoEsito(FlussoEsitoDTO flussoEsito)
			throws Exception {
		// TODO Auto-generated method stub
		int rowsAffected = getSqlSessionTemplate().insert(
				"it.contrada.flussoesito.queries.insertFlussoEsito",
				flussoEsito);

		LogUtil.logTraceMessage(log, String.format("inserito %d flusso esito",
				rowsAffected));

		return flussoEsito;
	}

	@Override
	public List<FlussoEsitoDTO> getFlussoEsito(TipoFlusso flusso, int nrLast)
			throws Exception {
		// TODO Auto-generated method stub
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("tipoFlusso", flusso.getFlusso());
		map.put("nrLast", nrLast);
		return getSqlSessionTemplate().selectList(
				"it.contrada.flussoesito.queries.getFlussiEsito", map);
	}

	@Override
	public int aggiornaFlussoEsito(FlussoEsitoDTO flussoEsito) throws Exception {
		// TODO Auto-generated method stub

		return getSqlSessionTemplate().update(
				"it.contrada.flussoesito.queries.aggiornaFlussoEsito",
				flussoEsito);
	}

	@Override
	public List<RidEsitoPreautDTO> getEsitoPreautPerRid(int idRid)
			throws Exception {
		// TODO Auto-generated method stub
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("idRid", idRid);
		return getSqlSessionTemplate().selectList(
				"it.contrada.flussoesito.queries.getEsitoPreautPerRid", map);
	}

	@Override
	public List<RidEsitoPreautDTO> getEsitoPreautPerData(Date dtDa, Date dtA)
			throws Exception {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		HashMap<String, java.sql.Date> map = new HashMap<String, java.sql.Date>();
		map.put("dtDa", dtDa);
		map.put("dtA", dtA);
		return getSqlSessionTemplate().selectList(
				"it.contrada.flussoesito.queries.getEsitoPreautPerRid", map);
	}

	@Override
	public List<RidEsitoPreautDTO> getEsitoPreaut(int idRid, Date dtDa, Date dtA)
			throws Exception {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("idRid", idRid);
		map.put("dtDa", dtDa);
		map.put("dtA", dtA);
		return getSqlSessionTemplate().selectList(
				"it.contrada.flussoesito.queries.getEsitoPreautPerRid", map);
	}

	@Override
	public List<IncassoRidDTO> getEsitoincassoRid(Integer anno, Integer mese,
			Integer tipoIncassoRid, Integer nrRid, Integer causaleIncasso)
			throws Exception {
		// TODO Auto-generated method stub
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		if (anno!=null)
		map.put("anno", anno);
		
		if (mese!=null)
		map.put("mese", mese);
		
		if (tipoIncassoRid!=null)
		map.put("tipoIncassoRid", tipoIncassoRid);
		
		if (causaleIncasso!=null)
		map.put("causaleIncasso", causaleIncasso);
		
		if (nrRid!=null)
		map.put("nrRid", nrRid);

		return getSqlSessionTemplate().selectList(
				"it.contrada.flussoesito.queries.getEsitoIncassiRid", map);
	}

	@Override
	public Date getMaxDataFlusso(TipoFlusso tipoFlusso) throws Exception {
		// TODO Auto-generated method stub
		return (Date)getSqlSessionTemplate().selectOne(
				"it.contrada.flussoesito.queries.getMaxDataFlusso", tipoFlusso.getFlusso());
	}
}
