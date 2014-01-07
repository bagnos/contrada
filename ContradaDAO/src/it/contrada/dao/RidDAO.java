package it.contrada.dao;

import it.contrada.dao.interfaces.IRidDAO;
import it.contrada.dominio.dto.TipoIncassoDTO;
import it.contrada.dominio.dto.TipoStatoRidDTO;
import it.contrada.dto.MembroRidDTO;
import it.contrada.dto.RidDTO;
import it.contrada.enumcontrada.TipoStatoRid;
import it.contrada.exceptions.ContradaExceptionBloccante;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;

public class RidDAO extends SqlSessionDaoSupport implements IRidDAO {
	private static Log log = LogFactory.getLog(AnagrafeDAO.class);

	@Override
	public List<MembroRidDTO> getMembro(String cognome, String nome)
			throws Exception {
		// TODO Auto-generated method stub
		List<MembroRidDTO> membri = null;

		HashMap<String, String> map = new HashMap<String, String>();
		map.put("cognome", cognome);
		map.put("nome", nome);
		membri = getSqlSessionTemplate().selectList(
				"it.contrada.rid.queries.getMembriRid", map);

		return membri;

	}

	@Override
	public List<MembroRidDTO> getMembroPerCognome(String cognome)
			throws Exception {
		// TODO Auto-generated method stub
		List<MembroRidDTO> membri = null;

		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("cognome", cognome);
		membri = getSqlSessionTemplate().selectList(
				"it.contrada.rid.queries.getMembriRid", map);

		return membri;

	}

	@Override
	public List<TipoStatoRidDTO> getStatiRid() throws Exception {
		// TODO Auto-generated method stub
		List<TipoStatoRidDTO> stati = null;

		stati = getSqlSessionTemplate().selectList(
				"it.contrada.rid.queries.getStatiRid");

		return stati;

	}

	@Override
	public int insertRid(RidDTO ridDTO) throws Exception {
		// TODO Auto-generated method stub

		int rowsAffected = getSqlSessionTemplate().insert(
				"it.contrada.rid.queries.insertRid", ridDTO);

		if (log.isTraceEnabled()) {
			log.trace("insertRid-rowsAffected=" + rowsAffected);
		}

		if (rowsAffected == 0) {
			throw new ContradaExceptionBloccante(
					"nessuna riga inserita per anagrafica "
							+ ridDTO.getIntestatarioRid());
		}

		return rowsAffected;

	}

	@Override
	public int updateRid(RidDTO ridDTO) throws Exception {
		// TODO Auto-generated method stub
		int rowsAffected = getSqlSessionTemplate().update(
				"it.contrada.rid.queries.updateRid", ridDTO);

		if (log.isTraceEnabled()) {
			log.trace("updateRid-rowsAffected=" + rowsAffected);
		}

		return rowsAffected;
	}

	@Override
	public RidDTO getRid(int idRid) throws Exception {
		// TODO Auto-generated method stub
		List<RidDTO> rids = getSqlSessionTemplate().selectList(
				"it.contrada.rid.queries.getRidConMembri", idRid);
		if (rids.isEmpty()) {
			return null;
		} else {
			return rids.get(0);
		}

	}

	@Override
	public List<RidDTO> getRidCensiti() throws Exception {
		// TODO Auto-generated method stub

		return getSqlSessionTemplate().selectList(
				"it.contrada.rid.queries.getRidCensiti");

	}
	
	

	@Override
	public int aggiornaStatoRid(TipoStatoRid stato, TipoStatoRid statoPrec)
			throws Exception {
		// TODO Auto-generated method stub
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("stato", stato.getStatoRid());
		map.put("statoPrec", statoPrec.getStatoRid());
		return getSqlSessionTemplate().update(
				"it.contrada.rid.queries.aggiornaStatoRid", map);
	}

	@Override
	public int aggiornaStatoRidPerIdRid(int idRid, TipoStatoRid stato,
			String note) throws Exception {
		// TODO Auto-generated method stub
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("stato", stato.getStatoRid());
		map.put("idRid", idRid);
		map.put("note", note);
		return getSqlSessionTemplate().update(
				"it.contrada.rid.queries.aggiornaStatoRidPerIdRid", map);
	}

	@Override
	public int aggiornaCoordinateBancarie(int idRid, int abi, int cab,
			String conto, String paese, int cinEuropeo, String cin,
			String note, String intestatario) throws Exception {
		// TODO Auto-generated method stub

		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("cdNazione", paese);
		map.put("idRid", idRid);
		map.put("txNote", note);
		map.put("cdAbi", abi);
		map.put("cdCab", cab);
		map.put("nrConto", conto);
		map.put("nrCinEuropeo", cinEuropeo);
		map.put("nrCin", cin);
		if (intestatario != null) {
			map.put("intestatario", intestatario);
		}

		return getSqlSessionTemplate().update(
				"it.contrada.rid.queries.aggiornaCoordinateBancarie", map);
	}

	@Override
	public int aggiornaNote(int idRid, String note) throws Exception {
		// TODO Auto-generated method stub

		HashMap<String, Object> map = new HashMap<String, Object>();

		map.put("idRid", idRid);
		map.put("txNote", note);

		return getSqlSessionTemplate().update(
				"it.contrada.rid.queries.aggiornaNote", map);
	}

	@Override
	public int aggiornaStatoRidPerIdFlussoAddebito(int stato,
			long idFlussoAddebito, String note) throws Exception {
		// TODO Auto-generated method stub
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("stato", stato);
		map.put("idFlussoAddebito", idFlussoAddebito);
		map.put("note", note);
		return getSqlSessionTemplate().update(
				"it.contrada.rid.queries.aggiornaStatoRidPerIdFlussoAddebito",
				map);
	}

	@Override
	public List<TipoIncassoDTO> elencaTipoIncassi() throws Exception {
		// TODO Auto-generated method stub
		return getSqlSessionTemplate().selectList(
				"it.contrada.rid.queries.elencaTipoIncassiRid");
	}

	@Override
	public List<MembroRidDTO> getMembro(int idRid) throws Exception {
		// TODO Auto-generated method stub
		List<MembroRidDTO> membri = null;

		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("idRid", idRid);
		membri = getSqlSessionTemplate().selectList(
				"it.contrada.rid.queries.getMembriRid", map);

		return membri;

	}

	@Override
	public List<MembroRidDTO> getMembroPerCognomeParziale(String matchCognome)
			throws Exception {
		// TODO Auto-generated method stub
		List<MembroRidDTO> membri = null;

		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("matchCognome",matchCognome + "%");
		membri = getSqlSessionTemplate().selectList(
				"it.contrada.rid.queries.getMembriPerCognomeParziale", map);

		return membri;

	}
	
	
	@Override
	public List<MembroRidDTO> getMembroPerCognomeNomeParziale(String cognome,
			String nome) throws Exception {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		List<MembroRidDTO> membri = null;

		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("matchCognome", cognome + "%");
		map.put("nome", nome + "%");
		membri = getSqlSessionTemplate().selectList(
				"it.contrada.rid.queries.getMembriPerCognomeParziale", map);

		return membri;
	}

	@Override
	public int aggiornaStatoRidPerListRid(List<Integer> rid,
			TipoStatoRid stato, TipoStatoRid statoPrec) throws Exception {
		// TODO Auto-generated method stub
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("stato", stato.getStatoRid());
		map.put("statoPrec", statoPrec.getStatoRid());
		map.put("rid", rid);

		return getSqlSessionTemplate().update(
				"it.contrada.rid.queries.aggiornaStatoRidPerListRid", map);

	}

	@Override
	public int aggiornaNoteRidPerIdFlussoAddebito(long idFlussoAddebito,
			String note) throws Exception {
		// TODO Auto-generated method stub
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("idFlussoAddebito", idFlussoAddebito);
		map.put("note", note);
		return getSqlSessionTemplate().update(
				"it.contrada.rid.queries.aggiornaNoteRidPerIdFlussoAddebito",
				map);
	}

	@Override
	public RidDTO getSchedaRid(int idRid) throws Exception {
		// TODO Auto-generated method stub
		RidDTO rid = null;

		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("idRid", idRid);
		rid = (RidDTO) getSqlSessionTemplate().selectOne(
				"it.contrada.rid.queries.getRid", map);

		return rid;

	}

	@Override
	public List<RidDTO> getRidPerStato(List<Integer> cdStato) throws Exception {
		// TODO Auto-generated method stub
		Hashtable<String, Object> par=new Hashtable<String, Object>();
		par.put("cdStato", cdStato);
		
		
		return getSqlSessionTemplate().selectList(
		"it.contrada.rid.queries.getRidPerStato",par);
	}

	

}
