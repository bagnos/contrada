package it.contrada.dao;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;

import it.contrada.dao.interfaces.IFlussoIncassoPosteDAO;
import it.contrada.enumcontrada.TipoFlusso;
import it.contrada.poste.FlussoIncassoPostaDTO;
import it.contrada.poste.IncassoPostaDTO;
import it.contrada.poste.RendicontazioneIncassoPostaDTO;

import org.mybatis.spring.support.SqlSessionDaoSupport;

public class FlussoIncassoPosteDAO extends SqlSessionDaoSupport implements
		IFlussoIncassoPosteDAO {

	@Override
	public List<IncassoPostaDTO> getFlussiIncassoPosta(int anno,
			Date dtScadenza, List<Integer> tipoTessere) {
		// TODO Auto-generated method stub
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		String dtScadenzaFormat = format.format(dtScadenza);

		HashMap<String, Object> map = new HashMap<String, Object>();

		map.put("anno", anno);
		map.put("dtScadenza", dtScadenzaFormat);
		map.put("tipoTessere", tipoTessere);
		return getSqlSessionTemplate().selectList(
				"it.contrada.flussoposte.queries.getFlussoIncassoPoste", map);

	}

	@Override
	public int insertIncassoPosta(List<IncassoPostaDTO> incassi)
			throws Exception {
		// TODO Auto-generated method stub
		for (IncassoPostaDTO incasso : incassi) {
			getSqlSessionTemplate().insert(
					"it.contrada.flussoposte.queries.insertIncassoPoste",
					incasso);
		}
		return incassi.size();
	}

	@Override
	public int insertFlussoIncassoPosta(FlussoIncassoPostaDTO flusso)
			throws Exception {
		// TODO Auto-generated method stub
		return getSqlSessionTemplate().insert(
				"it.contrada.flussoposte.queries.insertFlussoIncassoPoste",
				flusso);
	}

	@Override
	public int eliminaFlussoIncassoPosta(long idFlusso) throws Exception {
		// TODO Auto-generated method stub
		return getSqlSessionTemplate().delete(
				"it.contrada.flussoposte.queries.eliminaFlussoIncassoPoste",
				idFlusso);
	}

	@Override
	public int eliminaIncassiPosta(long idFlusso) throws Exception {
		// TODO Auto-generated method stub
		return getSqlSessionTemplate()
				.delete("it.contrada.flussoposte.queries.eliminaIncassoPoste",
						idFlusso);
	}

	@Override
	public int insertRendicontazioneFlussi(RendicontazioneIncassoPostaDTO esito)
			throws Exception {
		// TODO Auto-generated method stub
		return getSqlSessionTemplate().insert(
				"it.contrada.flussoposte.queries.insertRendicontazione", esito);

	}

	@Override
	public List<FlussoIncassoPostaDTO> getFlussoPostalePerAnno(int anno)
			throws Exception {
		// TODO Auto-generated method stub
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("anno", anno);
		return getSqlSessionTemplate().selectList(
				"it.contrada.flussoposte.queries.getFlussoPostale", map);

	}

	@Override
	public int aggiornaRendicontazioneFlusso(
			RendicontazioneIncassoPostaDTO esito) throws Exception {
		// TODO Auto-generated method stub
		return getSqlSessionTemplate().insert(
				"it.contrada.flussoposte.queries.updateRendicontazione", esito);
	}

}
