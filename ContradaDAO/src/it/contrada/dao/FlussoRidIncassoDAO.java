package it.contrada.dao;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;

import it.contrada.dao.interfaces.IFlussoRidIncassoDAO;
import it.contrada.dominio.dto.TipoCasualiIncassoRidDTO;
import it.contrada.enumcontrada.TipoIncassoRid;
import it.contrada.enumcontrada.TipoMeseIncasso;
import it.contrada.incassorid.dto.IncassoRidDTO;

import org.mybatis.spring.support.SqlSessionDaoSupport;

public class FlussoRidIncassoDAO extends SqlSessionDaoSupport implements
		IFlussoRidIncassoDAO {

	@Override
	public int insertIncassoRid(int anno, int mese, int idTipoIncassoRid,java.sql.Date dtValuta)
			throws Exception {
		// TODO Auto-generated method stub
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("anno", anno);
		map.put("mese", mese);
		map.put("idTipoIncassoRid", idTipoIncassoRid);
		map.put("dtValuta", dtValuta);

		return getSqlSessionTemplate().insert(
				"it.contrada.flussoincassirid.queries.insertIncassoRid",
				map);
	}

	@Override
	public int existFlusso(int anno, int mese,
			int idTipoIncassoRid) throws Exception {
		// TODO Auto-generated method stub
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("anno", anno);
		map.put("mese", mese);
		map.put("idTipoIncassoRid", idTipoIncassoRid);

		return ((Integer) getSqlSessionTemplate().selectOne(
				"it.contrada.flussoincassirid.queries.existFlusso", map))
				.intValue();
	}

	@Override
	public List<IncassoRidDTO> getIncassiDaInviare(int anno,int mese,int idTipoIncassoRid) throws Exception {
		// TODO Auto-generated method stub
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("anno", anno);
		map.put("mese", mese);
		map.put("idTipoIncassoRid", idTipoIncassoRid);
		return getSqlSessionTemplate().selectList(
				"it.contrada.flussoincassirid.queries.getIncassiDaInviare",map);
	}

	

	@Override
	public int insertFlussoRid(int anno, int mese, Date dtValuta,
			int idTipoIncassoRid,String nomeFileFlusso,long idOperazione) throws Exception {
		// TODO Auto-generated method stub
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("nrAnno", anno);
		map.put("nrMese", mese);
		map.put("idTipoIncassoRid", idTipoIncassoRid);
		map.put("dtValuta", dtValuta);
		map.put("nomeFileFlusso", nomeFileFlusso);
		map.put("idOperazione", idOperazione);
		
		return getSqlSessionTemplate().insert(
		"it.contrada.flussoincassirid.queries.insertFlussoRid",map);
	}

	@Override
	public int eliminaFlussoRid(int anno, int mese,
			int idTipoIncassoRid)
			throws Exception {
		// TODO Auto-generated method stub
		HashMap<String,Object> map=new HashMap<String,Object>();
		map.put("anno", anno);
		map.put("mese", mese);		
		map.put("idTipoIncassoRid", idTipoIncassoRid);
		return getSqlSessionTemplate().delete("it.contrada.flussoincassirid.queries.eliminaFlussoIncassoRid",map);
	}

	@Override
	public int eliminaIncassoRid(int anno, int mese,
			int idTipoIncassoRid)
			throws Exception {
		// TODO Auto-generated method stub
		HashMap<String,Object> map=new HashMap<String,Object>();
		map.put("anno", anno);
		map.put("mese", mese);		
		map.put("idTipoIncassoRid", idTipoIncassoRid);
		return getSqlSessionTemplate().delete("it.contrada.flussoincassirid.queries.eliminaIncassoRid",map);
	}

	@Override
	public String getNomeFileFlusso(int anno, int mese,
			int idTipoIncassoRid) throws Exception {
		// TODO Auto-generated method stub
		HashMap<String,Object> map=new HashMap<String,Object>();
		map.put("anno", anno);
		map.put("mese", mese);		
		map.put("idTipoIncassoRid", idTipoIncassoRid);
		return getSqlSessionTemplate().selectOne("it.contrada.flussoincassirid.queries.getNomeFileFlusso",map).toString();
	}

	@Override
	public List<TipoCasualiIncassoRidDTO> elencoCausaliIncassoRid() {
		// TODO Auto-generated method stub
		return getSqlSessionTemplate().selectList("it.contrada.flussoincassirid.queries.getTipoCausaliIncassiRid");
	}

	@Override
	public int aggiornaEsitoRid(long idFlussoAddebito, Date dtEsito,
			int idRidEsito,int idFlussoEsito) throws Exception {
		// TODO Auto-generated method stub
		HashMap<String,Object> map=new HashMap<String,Object>();
		map.put("idFlussoAddebito", idFlussoAddebito);
		map.put("dtEsito", dtEsito);		
		map.put("idRidEsito", idRidEsito);
		map.put("idFlussoEsito", idFlussoEsito);
		return getSqlSessionTemplate().update("it.contrada.flussoincassirid.queries.aggiornaRidEsito",map);
	}

	@Override
	public int aggiornaEsitoRid(long idRid, Date dtValuta,
			Date dtEsito, int idRidEsito, int idFlussoEsito) throws Exception {
		// TODO Auto-generated method stub
		HashMap<String,Object> map=new HashMap<String,Object>();
		map.put("idRid", idRid);
		map.put("dtValuta", dtValuta);
		map.put("dtEsito", dtEsito);		
		map.put("idRidEsito", idRidEsito);
		map.put("idFlussoEsito", idFlussoEsito);
		return getSqlSessionTemplate().update("it.contrada.flussoincassirid.queries.aggiornaRidEsitoPerRidValuta",map);
	}

	@Override
	public long getIdFlussoAddebito(long idRid, Date dtValuta) throws Exception {
		// TODO Auto-generated method stub
		HashMap<String,Object> map=new HashMap<String,Object>();
		map.put("idRid", idRid);
		map.put("dtValuta", dtValuta);		
		return (Long)getSqlSessionTemplate().selectOne("it.contrada.flussoincassirid.queries.getIdFlussoAddebito",map);
	}

}
