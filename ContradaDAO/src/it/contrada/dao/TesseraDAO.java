package it.contrada.dao;

import it.contrada.dao.interfaces.ITesseraDAO;
import it.contrada.dominio.dto.TipoCaricaDTO;
import it.contrada.dominio.dto.TipoStatoAnagraficaDTO;
import it.contrada.dominio.dto.TipoTesseraDTO;
import it.contrada.dto.TesseraDTO;
import it.contrada.dto.TesseraStampataDTO;
import it.contrada.exceptions.ContradaExceptionBloccante;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.naming.ldap.HasControls;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;

public class TesseraDAO extends SqlSessionDaoSupport implements ITesseraDAO {

	private static Log log = LogFactory.getLog(TesseraDAO.class);

	@Override
	public List<TipoTesseraDTO> getTipoTessera() throws Exception {
		// TODO Auto-generated method stub
		List<TipoTesseraDTO> tipoTessera = null;

		tipoTessera = getSqlSessionTemplate().selectList(
				"it.contrada.tessera.queries.getTipoTessera");

		return tipoTessera;

	}

	@Override
	public List<TipoTesseraDTO> getTipoTesseraPerIncasso(int idTipoIncasso)
			throws Exception {
		// TODO Auto-generated method stub
		List<TipoTesseraDTO> tipoTessera = null;
		HashMap map = new HashMap();
		map.put("idTipoIncasso", idTipoIncasso);
		tipoTessera = getSqlSessionTemplate().selectList(
				"it.contrada.tessera.queries.getTipoTesseraPerIncasso", map);

		return tipoTessera;

	}

	@Override
	public List<TipoTesseraDTO> getTipoTesseraPerAnagIncasso(long idAnag,
			int idTipoIncasso) throws Exception {
		// TODO Auto-generated method stub
		List<TipoTesseraDTO> tipoTessera = null;

		HashMap map = new HashMap();
		map.put("idTipoIncasso", idTipoIncasso);
		map.put("idAnag", idAnag);
		tipoTessera = getSqlSessionTemplate()
				.selectList(
						"it.contrada.tessera.queries.getTipoTesseraPerAnagIncasso",
						map);

		return tipoTessera;
	}

	@Override
	public List<TesseraDTO> insertTessere(List<TesseraDTO> tessere)
			throws Exception {
		// TODO Auto-generated method stub
		for (TesseraDTO tessera : tessere) {
			int rows = getSqlSessionTemplate().insert(
					"it.contrada.tessera.queries.insertTessera", tessera);
			if (log.isTraceEnabled()) {
				log.trace("rowsAffected=" + rows);
			}

			if (rows == 0) {
				throw new ContradaExceptionBloccante(
						"nessuna riga inserita per tessera "
								+ tessera.getDsTipoTessera() + " e anagrafica "
								+ tessera.getIdAnag());
			}
		}

		return tessere;

	}

	@Override
	public int eliminaTessere(int cdAnag) throws Exception {
		// TODO Auto-generated method stub

		HashMap map = new HashMap();
		map.put("idAnag", cdAnag);
		return getSqlSessionTemplate().delete(
				"it.contrada.tessera.queries.eliminaTessere", map);
	}

	@Override
	public int aggiornaRidTessera(List<Integer> idTessere, int idRid)
			throws Exception {
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder();
		int i = 0;
		HashMap map = new HashMap();

		for (Integer idTessera : idTessere) {

			// sb.append(idTessera);

			// if (i!=idTessere.size()-1)
			// {
			// sb.append(",");
			// }
			map.clear();
			map.put("idTessera", idTessera);
			map.put("idRid", idRid);
			getSqlSessionTemplate().update(
					"it.contrada.tessera.queries.updateRid", map);
			i++;
		}

		return i;
	}

	@Override
	public int annullaRid(int idRid) throws Exception {
		// TODO Auto-generated method stub
		return getSqlSessionTemplate().update(
				"it.contrada.tessera.queries.annullaRid", idRid);
	}

	@Override
	public int insertTesseraStorico(int idTessera) throws Exception {
		// TODO Auto-generated method stub

		HashMap<String, Integer> map = new HashMap<String, Integer>();
		int anno = getAnnoInCorso();

		map.put("idTessera", idTessera);
		map.put("anno", anno);

		return getSqlSessionTemplate().insert(
				"it.contrada.tessera.queries.insertStorico", map);

	}

	@Override
	public int aggiornaTesseraStorico(int idTessera) throws Exception {
		// TODO Auto-generated method stub
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("idTessera", idTessera);

		return getSqlSessionTemplate().update(
				"it.contrada.tessera.queries.aggiornaTesseraStorico", map);

	}

	@Override
	public int allineaQuoteTessera(java.sql.Date dataRif) throws Exception {
		// TODO Auto-generated method stub
		int quoteAgg = 0;
		// si recupera tutte le cariche che hanno un carica Successiva
		/*
		 * List<TipoCaricaDTO> cariche = getSqlSessionTemplate().selectList(
		 * "it.contrada.carica.queries.getCaricheSuccessive"); HashMap map = new
		 * HashMap();
		 * 
		 * 
		 * // si aggiornano tutte le tessere con in nuovi parametri for
		 * (TipoCaricaDTO carica : cariche) { map.clear(); map.put("dataRif",
		 * dataRif); map.put("idCarica", carica.getIdCarica()); map.put("quota",
		 * carica.getImMinimo()); map.put("caricaSucc",
		 * carica.getIdTipoCaricaSucc());
		 * 
		 * quoteAgg += getSqlSessionTemplate().update(
		 * "it.contrada.tessera.queries.aggiornaQuotaCarica", map); }
		 */
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("dataRif", dataRif);
		quoteAgg += getSqlSessionTemplate().update(
				"it.contrada.tessera.queries.aggiornaQuotaCarica", map);
		return quoteAgg;
	}

	@Override
	public List<TesseraDTO> getTessereRendicontabiliManualmente(
			int idAnagrafica, int anno) throws Exception {
		// TODO Auto-generated method stub
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("idAnagrafica", idAnagrafica);
		map.put("anno", anno);

		List<TesseraDTO> tessere = getSqlSessionTemplate()
				.selectList(
						"it.contrada.tessera.queries.getTessereRendicontabiliManualmente",
						map);
		return tessere;

	}

	@Override
	public List<TesseraDTO> getTesserePerAnagrafica(int idAnagrafica)
			throws Exception {
		// TODO Auto-generated method stub
		List<TesseraDTO> tessere = getSqlSessionTemplate().selectList(
				"it.contrada.tessera.queries.getTesserePerAnagrafica",
				idAnagrafica);
		return tessere;
	}

	@Override
	public int eliminaTessera(int idTessera) throws Exception {
		// TODO Auto-generated method stub
		return getSqlSessionTemplate().delete(
				"it.contrada.tessera.queries.eliminaTessera", idTessera);
	}

	@Override
	public int eliminaTesseraStorico(int idTessera) throws Exception {
		// TODO Auto-generated method stub

		return getSqlSessionTemplate().delete(
				"it.contrada.tessera.queries.eliminaTesseraStorico", idTessera);

	}

	@Override
	public int aggiornaTessera(TesseraDTO tessera) throws Exception {
		// TODO Auto-generated method stub
		return getSqlSessionTemplate().update(
				"it.contrada.tessera.queries.aggiornaTessera", tessera);
	}

	@Override
	public int disattivaTessera(int idTessera) throws Exception {
		// TODO Auto-generated method stub
		return getSqlSessionTemplate().update(
				"it.contrada.tessera.queries.disattivaTessera", idTessera);
	}

	@Override
	public int getTesseraRendicontata(int idTessera) throws Exception {
		// TODO Auto-generated method stub

		return (Integer) getSqlSessionTemplate()
				.selectOne(
						"it.contrada.tessera.queries.getTesseraRendicontata",
						idTessera);
	}

	@Override
	public int deleteStoricoAnno(int anno) throws Exception {
		// TODO Auto-generated method stub
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("anno", anno);
		return (Integer) getSqlSessionTemplate().insert(
				"it.contrada.tessera.queries.deleteStorico", map);
	}

	@Override
	public int insertStoricoAnno(int anno) throws Exception {
		// TODO Auto-generated method stub
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("anno", anno);
		return (Integer) getSqlSessionTemplate().insert(
				"it.contrada.tessera.queries.insertStorico", map);
	}

	@Override
	public List<TesseraDTO> getTessereRendicontabiliManualmente(
			int idAnagrafica, int idTipoTessera, int anno) throws Exception {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("idAnagrafica", idAnagrafica);
		map.put("idTipoTessera", idTipoTessera);
		map.put("anno", anno);

		List<TesseraDTO> tessere = getSqlSessionTemplate()
				.selectList(
						"it.contrada.tessera.queries.getTessereRendicontabiliManualmente",
						map);
		return tessere;
	}

	@Override
	public int aggiornaTipoTessera(List<TipoTesseraDTO> tipoTessere)
			throws Exception {
		// TODO Auto-generated method stub
		int rows = 0;
		for (TipoTesseraDTO tipoTessera : tipoTessere) {
			rows += getSqlSessionTemplate().update(
					"it.contrada.tessera.queries.aggiornaTipoTessera",
					tipoTessera);
		}
		return rows;
	}

	@Override
	public int aggiornaImportoTessera(List<TipoTesseraDTO> tipoTessere)
			throws Exception {
		// TODO Auto-generated method stub
		int rows = 0;
		for (TipoTesseraDTO tipoTessera : tipoTessere) {
			rows += getSqlSessionTemplate().update(
					"it.contrada.tessera.queries.aggiornaImportoTessera",
					tipoTessera);
		}
		return rows;
	}

	@Override
	public int aggiornaImportoTesseraStorico(List<TipoTesseraDTO> tipoTessere)
			throws Exception {
		// TODO Auto-generated method stub
		int rows = 0;
		for (TipoTesseraDTO tipoTessera : tipoTessere) {
			rows += getSqlSessionTemplate()
					.update(
							"it.contrada.tessera.queries.aggiornaImportoTesseraStorico",
							tipoTessera);
		}
		return rows;
	}

	@Override
	public List<TesseraStampataDTO> getStampaTessera(int idTipoTessera,
			Integer idTipoIncasso, List<Integer> idEsattori, int anno)
			throws Exception {
		// TODO Auto-generated method stub
		HashMap<String, Object> parm = new HashMap<String, Object>();
		parm.put("idTipoTessera", idTipoTessera);
		if (idTipoIncasso != null) {
			parm.put("idTipoIncasso", idTipoIncasso);
		}
		if (idEsattori != null && !idEsattori.isEmpty()) {
			parm.put("idEsattori", idEsattori);
		}
		parm.put("anno", anno);
		return getSqlSessionTemplate().selectList(
				"it.contrada.tessera.queries.getStampaTessere", parm);

	}

	@Override
	public List<TesseraStampataDTO> getStampaTessera(int idTipoTessera,
			List<Integer> idAnagrafica, int anno) throws Exception {
		// TODO Auto-generated method stub
		HashMap<String, Object> parm = new HashMap<String, Object>();
		parm.put("idTipoTessera", idTipoTessera);
		parm.put("idAnagrafica", idAnagrafica);
		parm.put("anno", anno);
		return getSqlSessionTemplate().selectList(
				"it.contrada.tessera.queries.getStampaTessere", parm);
	}

	@Override
	public List<TesseraStampataDTO> getStampaTessera(int idTipoTessera,
			Integer idTipoIncasso, Integer idTipoCarica, int anno)
			throws Exception {
		// TODO Auto-generated method stub
		HashMap<String, Object> parm = new HashMap<String, Object>();
		parm.put("idTipoTessera", idTipoTessera);
		parm.put("idTipoIncasso", idTipoIncasso);
		parm.put("idTipoCarica", idTipoCarica);
		parm.put("anno", anno);
		parm.put("anagrafica", true);
		return getSqlSessionTemplate().selectList(
				"it.contrada.tessera.queries.getStampaTessere", parm);
	}

	@Override
	public List<TesseraDTO> getTesserePerDistinta(int anno, int distinta)
			throws Exception {
		// TODO Auto-generated method stub
		HashMap<String, Object> parm = new HashMap<String, Object>();
		parm.put("anno", anno);
		parm.put("distinta", distinta);

		return getSqlSessionTemplate().selectList(
				"it.contrada.tessera.queries.getTesserePerDistinta", parm);
	}

	@Override
	public List<TesseraStampataDTO> getStampaTessera(int idTipoTessera,
			Integer[] idTipoIncasso, int anno) throws Exception {
		// TODO Auto-generated method stub
		HashMap<String, Object> parm = new HashMap<String, Object>();

		parm.put("idTipoTessera", idTipoTessera);
		parm.put("idTipoIncassi", idTipoIncasso);
		parm.put("anno", anno);

		return getSqlSessionTemplate().selectList(
				"it.contrada.tessera.queries.getStampaTessere", parm);
	}

	@Override
	public List<TesseraStampataDTO> getTabulato(int idTipoTessera,
			Integer idTipoIncasso, Integer idTipoCarica, int anno)
			throws Exception {
		// TODO Auto-generated method stub
		HashMap<String, Object> parm = new HashMap<String, Object>();

		parm.put("idTipoTessera", idTipoTessera);
		parm.put("tabulato", true);
		if (idTipoIncasso != null) {
			parm.put("idTipoIncasso", idTipoIncasso);
		}
		if (idTipoCarica != null) {
			parm.put("idTipoCarica", idTipoCarica);
		}
		parm.put("anno", anno);

		return getSqlSessionTemplate().selectList(
				"it.contrada.tessera.queries.getStampaTessere", parm);
	}

	@Override
	public List<TesseraDTO> getTessereNonPagantiUltimiAnni(int idTipoTessera,
			int annoDa, int annoA) throws Exception {
		// TODO Auto-generated method stub
		HashMap<String, Integer> parm = new HashMap<String, Integer>();
		parm.put("tipoTessera", idTipoTessera);
		parm.put("annoDa", annoDa);
		parm.put("annoA", annoA);
		return getSqlSessionTemplate().selectList(
				"it.contrada.tessera.queries.getTessereNonPagantiUltimiAnni",
				parm);
	}

	@Override
	public List<TesseraDTO> getTessereNonPagantiUltimiAnni(int idTipoTessera,
			int annoDa, int tipoIncasso, int annoA, Integer tipoEsattore)
			throws Exception {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		HashMap<String, Integer> parm = new HashMap<String, Integer>();
		parm.put("tipoTessera", idTipoTessera);
		parm.put("annoDa", annoDa);
		parm.put("tipoIncasso", tipoIncasso);
		parm.put("annoA", annoA);
		if (tipoEsattore != null) {
			parm.put("tipoEsattore", tipoEsattore);
		}
		return getSqlSessionTemplate().selectList(
				"it.contrada.tessera.queries.getTessereNonPagantiUltimiAnni",
				parm);
	}

	@Override
	public int aggiornaTesseraStoricoByAnagrafica(int idAnagrafica)
			throws Exception {
		// TODO Auto-generated method stub
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("idAnagrafica", idAnagrafica);

		return getSqlSessionTemplate().update(
				"it.contrada.tessera.queries.aggiornaTesseraStoricoByCdAnag",
				map);
	}

	@Override
	public int disattivaTesseraByAnagrafica(int idAnagrafica) throws Exception {
		// TODO Auto-generated method stub
		return getSqlSessionTemplate().update(
				"it.contrada.tessera.queries.disattivaTesseraByCdAnag",
				idAnagrafica);
	}

	@Override
	public List<TesseraStampataDTO> getStampaTesseraFineAnno(int idTipoTessera,
			Integer[] idTipoIncasso, int anno) throws Exception {
		// TODO Auto-generated method stub
		HashMap<String, Object> parm = new HashMap<String, Object>();

		parm.put("idTipoTessera", idTipoTessera);
		parm.put("idTipoIncassi", idTipoIncasso);
		parm.put("fineAnno", Boolean.TRUE);
		parm.put("anno", anno);

		return getSqlSessionTemplate().selectList(
				"it.contrada.tessera.queries.getStampaTessere", parm);
	}

	@Override
	public List<TesseraDTO> getTessereDaAllineare(java.sql.Date dataRif)
			throws Exception {
		// TODO Auto-generated method stub
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("dataRif", dataRif);

		return getSqlSessionTemplate().selectList(
				"it.contrada.tessera.queries.getTessereDaAllineare", map);
	}

	@Override
	public int getAnnoInCorso() throws Exception {
		// TODO Auto-generated method stub
		return (Integer) getSqlSessionTemplate().selectOne(
				"it.contrada.tessera.queries.getAnnoInCorso");
	}

}
