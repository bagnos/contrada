package it.contrada.dao;

import it.contrada.dao.interfaces.IAnagrafeDAO;
import it.contrada.dominio.dto.TipoStatoAnagraficaDTO;
import it.contrada.dto.AnagraficaDTO;
import it.contrada.dto.ParmResidenzaDTO;
import it.contrada.dto.RicercaFasceEtaDTO;
import it.contrada.exceptions.ContradaExceptionBloccante;
import it.contrada.exceptions.ContradaExceptionNonBloccante;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;

public class AnagrafeDAO extends SqlSessionDaoSupport implements IAnagrafeDAO {

	private static Log log = LogFactory.getLog(AnagrafeDAO.class);

	@Override
	public boolean existAnagraficaByCdFisc(String cdFiscale) throws Exception {
		// TODO Auto-generated method stub

		if (log.isTraceEnabled()) {
			log.trace("cdFiscale=" + cdFiscale);
		}

		// sqlMap = Connection.getSqlSessionFactory();

		// SqlSession session = sqlMap.openSession();

		HashMap map = new HashMap();
		map.put("cdFisc", cdFiscale);

		int nrRows = Integer.parseInt(getSqlSessionTemplate().selectOne(
				"it.contrada.queries.existContradaiolotByCdFisc", map)
				.toString());

		if (log.isTraceEnabled()) {
			log.trace("nrRows=" + nrRows);
		}

		return (nrRows > 0 ? true : false);

	}

	public AnagraficaDTO inserisciAnagrafica(AnagraficaDTO anagraficaDTO)
			throws Exception {
		// TODO Auto-generated method stub

		/*
		 * sqlMap = Connection.getSqlSessionFactory();
		 * 
		 * SqlSession session = sqlMap.openSession();
		 */

		int rowsAffected = getSqlSessionTemplate().insert(
				"it.contrada.anagrafica.queries.insertAnagrafica",
				anagraficaDTO);

		if (log.isTraceEnabled()) {
			log.trace("rowsAffected=" + rowsAffected);
		}

		return anagraficaDTO;

	}

	@Override
	public AnagraficaDTO getAnagraficaByCodiceAnagrafica(long cdAnag)
			throws Exception {
		// TODO Auto-generated method stub
		HashMap map = new HashMap();
		map.put("cdAnag", cdAnag);
		return (AnagraficaDTO) getSqlSessionTemplate().selectOne(
				"it.contrada.anagrafica.queries.getAnagrafica", map);

	}

	@Override
	public List<AnagraficaDTO> getAnagraficaByCodiceFamiglia(long cdFamiglia)
			throws Exception {
		// TODO Auto-generated method stub
		HashMap map = new HashMap();
		map.put("cdFamiglia", cdFamiglia);
		return getSqlSessionTemplate().selectList(
				"it.contrada.anagrafica.queries.getAnagrafica", map);

	}
	
	@Override
	public List<AnagraficaDTO> getAnagraficaByGestore(int idGestore)
			throws Exception {
		// TODO Auto-generated method stub
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("idGestore", idGestore);
		map.put("order", "fam");
		return getSqlSessionTemplate().selectList(
				"it.contrada.anagrafica.queries.getAnagrafica", map);
	}

	@Override
	public List<AnagraficaDTO> getAnagraficaByCognome(String cognome)
			throws Exception {
		// TODO Auto-generated method stub
		HashMap map = new HashMap();
		map.put("cognome", "%" + cognome + "%");
		return getSqlSessionTemplate().selectList(
				"it.contrada.anagrafica.queries.getAnagrafica", map);
	}

	@Override
	public List<AnagraficaDTO> getAnagraficaByCognomeNome(String cognome,
			String nome) throws Exception {
		// TODO Auto-generated method stub
		HashMap map = new HashMap();
		map.put("cognome", "%" + cognome + "%");
		map.put("nome", "%" + nome + "%");

		return getSqlSessionTemplate().selectList(
				"it.contrada.anagrafica.queries.getAnagrafica", map);
	}

	@Override
	public int aggiornaAnagrafica(AnagraficaDTO anagraficaDTO) throws Exception {
		// TODO Auto-generated method stub
		int rows = getSqlSessionTemplate().update(
				"it.contrada.anagrafica.queries.updateAnagraficaByCdAnag",
				anagraficaDTO);

		return rows;
	}

	@Override
	public List<TipoStatoAnagraficaDTO> getStatiAnagrafica() throws Exception {
		// TODO Auto-generated method stub
		return getSqlSessionTemplate().selectList(
				"it.contrada.anagrafica.queries.getStatiAnagrafica");

	}

	@Override
	public List<AnagraficaDTO> getAnagraficaByFasce(RicercaFasceEtaDTO criterio)
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {
		// TODO Auto-generated method stub
		HashMap<String, Object> map = new HashMap<String, Object>();
		if (criterio.getEta() != null)
			map.put("eta", criterio.getEta());

		if (criterio.getMese() != null)
			map.put("mese", criterio.getMese());

		if (criterio.getDtA() != null && criterio.getDtDa() != null) {
			map.put("dtDa", criterio.getDtDa());
			map.put("dtA", criterio.getDtA());
		}

		if (criterio.getSesso() != null) {
			map.put("sesso", criterio.getSesso());
		}

		return getSqlSessionTemplate().selectList(
				"it.contrada.anagrafica.queries.getFasceEta", map);
	}

	@Override
	public List<AnagraficaDTO> getIndirizzoAnagraficaPrincipale()
			throws Exception {
		// TODO Auto-generated method stub
		return getSqlSessionTemplate().selectList(
				"it.contrada.anagrafica.queries.getIndirizzoFamiglie");
	}

	@Override
	public List<AnagraficaDTO> getAnagraficaParzialePerCognome(String cognome)
			throws Exception {
		// TODO Auto-generated method stub
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("cognome", cognome + "%");
		return getSqlSessionTemplate()
				.selectList(
						"it.contrada.anagrafica.queries.getAnagraficaParzialePerCognome",
						map);

	}

	@Override
	public List<AnagraficaDTO> getAnagraficaPerResidenza(ParmResidenzaDTO parm)
			throws Exception {
		// TODO Auto-generated method stub

		/*
		 * if (parm.getLocalita() != null) { parm.setLocalita("%" +
		 * parm.getLocalita() + "%"); }
		 */

		return getSqlSessionTemplate().selectList(
				"it.contrada.anagrafica.queries.getAnagraficaPerResidenza",
				parm);
	}

	@Override
	public int aggiornaGestore(List<AnagraficaDTO> anagrafiche)
			throws Exception {
		// TODO Auto-generated method stub
		int rows = 0;
		for (AnagraficaDTO anagraficaDTO : anagrafiche) {
			rows += getSqlSessionTemplate().update(
					"it.contrada.anagrafica.queries.updateGestore",
					anagraficaDTO);
		}
		return rows;
	}

	@Override
	public int aggiornaIndirizzoAnagrafica(long idStrada, String nrCivico,
			int idFamiglia) throws Exception {
		// TODO Auto-generated method stub
		HashMap<String, Object> parms = new HashMap<String, Object>();
		parms.put("idStrada", idStrada);
		parms.put("idFamiglia", idFamiglia);
		parms.put("nrCivico", nrCivico);

		return getSqlSessionTemplate().update(
				"it.contrada.anagrafica.queries.updateIndirizzoAnagrafica",
				parms);

	}

	@Override
	public List<AnagraficaDTO> getPagantiAnnoPrecedente(int anno,
			int tipoTessera) throws Exception {
		// TODO Auto-generated method stub
		HashMap<String, Object> parms = new HashMap<String, Object>();
		parms.put("annoInCorso", anno);
		parms.put("anno", anno - 1);
		parms.put("tipoTessera", tipoTessera);

		return getSqlSessionTemplate().selectList(
				"it.contrada.anagrafica.queries.getPagantiAnnoPrecedente",
				parms);

	}

	@Override
	public List<AnagraficaDTO> getAnagraficaParzialePerCognomeNome(
			String cognome, String nome) throws Exception {
		// TODO Auto-generated method stub
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("cognome", cognome + "%");
		map.put("nome", nome + "%");
		return getSqlSessionTemplate()
				.selectList(
						"it.contrada.anagrafica.queries.getAnagraficaParzialePerCognome",
						map);

	}

	@Override
	public int existAnagrafica(String nome, String cognome, Date dtNascita)
			throws Exception {
		// TODO Auto-generated method stub
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("cognome", cognome);
		map.put("nome", nome);
		map.put("dtNascita", dtNascita);
		return (Integer) getSqlSessionTemplate().selectOne(
				"it.contrada.anagrafica.queries.exitAnagrafica", map);
	}

	@Override
	public int aggiornaStatoAnagrafica(List<Integer> idAnagrafiche,
			int idStatoAnagrafica) throws Exception {
		// TODO Auto-generated method stub

		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("idAnagrafiche", idAnagrafiche);
		map.put("idStatoAnagrafica", idStatoAnagrafica);
		return (Integer) getSqlSessionTemplate().update(
				"it.contrada.anagrafica.queries.updateStatoAnagrafica", map);

	}
	
	@Override
	public int aggiornaStatoAnagrafica(int idStatoAnagrafica, int idStatoAnagraficaPrec)
			throws Exception {
		// TODO Auto-generated method stub
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("idStatoAnagrafica", idStatoAnagrafica);
		map.put("idStatoAnagraficaPrec", idStatoAnagraficaPrec);
		return (Integer) getSqlSessionTemplate().update(
				"it.contrada.anagrafica.queries.updateAllStatoAnagrafica", map);
	}

	@Override
	public List<AnagraficaDTO> getVotanti(int idTipoTessera, Date dtElezione,
			int etaMin, int annoDa) throws Exception {
		// TODO Auto-generated method stub
		HashMap<String, Object> parms = new HashMap<String, Object>();
		parms.put("idTipoTessera", idTipoTessera);
		parms.put("dtElezione", dtElezione);
		parms.put("etaMin", etaMin);
		parms.put("annoDa", annoDa);

		return getSqlSessionTemplate().selectList(
				"it.contrada.anagrafica.queries.getVotanti", parms);

	}

	@Override
	public List<AnagraficaDTO> getIndirizzoAnagraficaPrincipale(
			List<Integer> codiciFamiglia) throws Exception {
		HashMap<String, Object> parms = new HashMap<String, Object>();
		parms.put("codiciFamiglia", codiciFamiglia);
		return getSqlSessionTemplate().selectList(
				"it.contrada.anagrafica.queries.getIndirizzoFamiglie",
				parms);
	}

	@Override
	public List<AnagraficaDTO> getFazzoletti(int annoPagamentoDa)
			throws Exception {
		// TODO Auto-generated method stub
		HashMap<String, Object> parms = new HashMap<String, Object>();
		parms.put("idTipoTessera", 1);
		parms.put("annoDa", annoPagamentoDa);

		return getSqlSessionTemplate().selectList(
				"it.contrada.anagrafica.queries.getVotanti", parms);
	}

	@Override
	public List<AnagraficaDTO> getAnagraficheConPrincipale(List<Integer> statiAnagrafica)
			throws Exception {
		// TODO Auto-generated method stub
		HashMap<String, Object> parms = new HashMap<String, Object>();
		parms.put("stato", statiAnagrafica);
		

		return getSqlSessionTemplate().selectList(
				"it.contrada.anagrafica.queries.getAnagraficheConPrincipale", parms);
	}

	

	
}
