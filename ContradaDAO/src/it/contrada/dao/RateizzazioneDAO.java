package it.contrada.dao;

import it.contrada.dao.interfaces.IRateizzazioneDAO;
import it.contrada.dominio.dto.TipoRateizzazioneDTO;
import it.contrada.dto.IncassoAnnualeDTO;
import it.contrada.dto.ParmIncassoDTO;
import it.contrada.dto.RateizzazioneDTO;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

public class RateizzazioneDAO extends SqlSessionDaoSupport implements
		IRateizzazioneDAO {

	@Override
	public List<TipoRateizzazioneDTO> getTipoRateizzazione() throws Exception {
		// TODO Auto-generated method stub
		List<TipoRateizzazioneDTO> tipoRateizzazione = null;

		tipoRateizzazione = getSqlSessionTemplate().selectList(
				"it.contrada.rateizzazione.queries.getTipoRateizzazione");

		return tipoRateizzazione;
	}

	@Override
	public List<TipoRateizzazioneDTO> getTipoRateizzazionePerTessera(
			int idTessera) throws Exception {
		// TODO Auto-generated method stub
		List<TipoRateizzazioneDTO> tipoRateizzazione = null;

		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("idTessera", idTessera);
		tipoRateizzazione = getSqlSessionTemplate()
				.selectList(
						"it.contrada.rateizzazione.queries.getTipoRateizzazionePerTessera",
						map);

		return tipoRateizzazione;

	}

	@Override
	public List<TipoRateizzazioneDTO> getTipoRateizzazionePerTesseraIncasso(
			int idTipoTessera, int idTipoIncasso) throws Exception {
		// TODO Auto-generated method stub
		List<TipoRateizzazioneDTO> tipoRateizzazione = null;

		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("idTessera", idTipoTessera);
		map.put("idIncasso", idTipoIncasso);
		tipoRateizzazione = getSqlSessionTemplate()
				.selectList(
						"it.contrada.rateizzazione.queries.getTipoRateizzazionePerTesseraIncasso",
						map);

		return tipoRateizzazione;

	}

	@Override
	public int insertRateizzazione(RateizzazioneDTO rateizzazione)
			throws Exception {
		// TODO Auto-generated method stub
		return getSqlSessionTemplate().insert(
				"it.contrada.rateizzazione.queries.insertRateizzazione",
				rateizzazione);
	}

	@Override
	public List<RateizzazioneDTO> getRateizzazionePerAnagrafica(int idAnagrafica)
			throws Exception {
		// TODO Auto-generated method stub
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("idAnagrafica", idAnagrafica);

		return getSqlSessionTemplate()
				.selectList(
						"it.contrada.rateizzazione.queries.getRateizzazioniPerAnagrafica",
						map);
	}

	@Override
	public int eliminaRateizzazione(RateizzazioneDTO rateizzazione)
			throws Exception {
		// TODO Auto-generated method stub
		return getSqlSessionTemplate().delete(
				"it.contrada.rateizzazione.queries.deleteRateizzazione",
				rateizzazione);
	}

	@Override
	public int insertRateizzazioniPerFlusso(int nrAnno, int mese,
			int idTipoIncassoRid) throws Exception {
		// TODO Auto-generated method stub
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("anno", nrAnno);
		map.put("mese", mese);
		map.put("idTipoIncassoRid", idTipoIncassoRid);
		return getSqlSessionTemplate()
				.insert(
						"it.contrada.rateizzazione.queries.insertflussoRateizzazionePerMese",
						map);
	}

	@Override
	public int aggiornaRidFlussoAddebito(int nrAnno, int mese) throws Exception {
		// TODO Auto-generated method stub
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("anno", nrAnno);
		map.put("mese", mese);
		return getSqlSessionTemplate().update(
				"it.contrada.rateizzazione.queries.aggiornaRidFlussoAddebito",
				map);
	}

	@Override
	public int eliminaRateizzazioniDaFlusso(int nrAnno, int mese,
			int tipoIncassoRid) throws Exception {
		// TODO Auto-generated method stub
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("anno", nrAnno);
		map.put("mese", mese);
		map.put("idTipoIncassoRid", tipoIncassoRid);
		return getSqlSessionTemplate()
				.delete(
						"it.contrada.rateizzazione.queries.eliminaRateizzazioneDaFlusso",
						map);
	}

	@Override
	public int aggiornaStatoRata(int idStatoRata, long idFlussoAddebito)
			throws Exception {
		// TODO Auto-generated method stub
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("idStatoRata", idStatoRata);
		map.put("idFlussoAddebito", idFlussoAddebito);

		return getSqlSessionTemplate().update(
				"it.contrada.rateizzazione.queries.aggiornaStatoRata", map);
	}

	@Override
	public Integer getIdRidFromIdFlusso(long idFlussoAddebito) throws Exception {
		// TODO Auto-generated method stub
		HashMap<String, Object> map = new HashMap<String, Object>();

		map.put("idFlussoAddebito", idFlussoAddebito);

		return (Integer) getSqlSessionTemplate().selectOne(
				"it.contrada.rateizzazione.queries.getIdRidFromIdFlusso", map);

	}

	@Override
	public List<RateizzazioneDTO> getRate(ParmIncassoDTO parmIncasso)
			throws Exception {
		// TODO Auto-generated method stub
		return getSqlSessionTemplate().selectList(
				"it.contrada.rateizzazione.queries.getRate", parmIncasso);
	}

	@Override
	public List<RateizzazioneDTO> getRateizzazionePerAnagrafica(
			int idAnagrafica, int idTipoTessera) throws Exception {
		// TODO Auto-generated method stub
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("idAnagrafica", idAnagrafica);
		map.put("idTipoTessera", idTipoTessera);
		return getSqlSessionTemplate()
				.selectList(
						"it.contrada.rateizzazione.queries.getRateizzazioniPerAnagrafica",
						map);
	}

	@Override
	public int eliminaRataPerFlussoPosta(long idFlusso) throws Exception {
		// TODO Auto-generated method stub
		return getSqlSessionTemplate().delete(
				"it.contrada.rateizzazione.queries.eliminaRataPerFlussoPosta",
				idFlusso);
	}

	@Override
	public int aggiornaStatoRataPerPosta(int idStatoRata, String idFlussoIncassoPoste,java.sql.Date dtOperazione)
			throws Exception {
		// TODO Auto-generated method stub
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("idStatoRata", idStatoRata);
		map.put("idFlussoIncassoPoste", idFlussoIncassoPoste);
		map.put("dtOperazione", dtOperazione);

		return getSqlSessionTemplate().update(
				"it.contrada.rateizzazione.queries.aggiornaStatoRataPerPosta", map);
	}

	@Override
	public List<RateizzazioneDTO> getRateizzazionePerAnagrafica(
			int idAnagrafica, Integer[] idTipoTessera, Integer annoDa,
			Integer annoA) throws Exception {
		// TODO Auto-generated method stub
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("idAnagrafica", idAnagrafica);
		map.put("idTipoTessera", idTipoTessera);
		map.put("annoDa", annoDa);
		map.put("annoA", annoA);
		return getSqlSessionTemplate()
				.selectList(
						"it.contrada.rateizzazione.queries.getRateizzazioniPerAnagrafica",
						map);

	}

	@Override
	public List<IncassoAnnualeDTO> getIncassoAnnuale(int anno,List<Integer> idTipoTessera) throws Exception {
		// TODO Auto-generated method stub
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("anno", anno);
		map.put("tipoTessera", idTipoTessera);
		return  getSqlSessionTemplate()
		.selectList(
				"it.contrada.rateizzazione.queries.getIncassoAnnuale",
				map);
	}

}
