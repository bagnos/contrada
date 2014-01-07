package it.contrada.bean;

import it.contrada.dao.TesseraDAO;
import it.contrada.dao.interfaces.ITesseraDAO;
import it.contrada.dominio.dto.TipoTesseraDTO;
import it.contrada.dto.TesseraDTO;
import it.contrada.dto.TesseraStampataDTO;
import it.contrada.exceptions.ContradaExceptionBloccante;
import it.contrada.exceptions.ContradaExceptionNonBloccante;
import it.contrada.interfaces.IRicercaTessera;
import it.contrada.util.DecodificaErrore;

import java.sql.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Session Bean implementation class RicercaTessera
 */

public class RicercaTessera implements IRicercaTessera {

	/**
	 * @uml.property  name="tipoTesseraDao"
	 * @uml.associationEnd  
	 */
	private ITesseraDAO tipoTesseraDao;

	private static Log log = LogFactory.getLog(RicercaStrada.class);

	/**
	 * @param tipoTesseraDao
	 * @uml.property  name="tipoTesseraDao"
	 */
	public void setTipoTesseraDao(ITesseraDAO tipoTesseraDao) {
		this.tipoTesseraDao = tipoTesseraDao;
	}

	/**
	 * Default constructor.
	 */
	public RicercaTessera() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see IRicercaTessera#elencaTipoTessera()
	 */
	public List<TipoTesseraDTO> elencaTipoTessera()
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {
		List<TipoTesseraDTO> tipoTessere = null;
		try {
			tipoTessere = tipoTesseraDao.getTipoTessera();
		}

		catch (Exception ex) {
			log.error(ex);
			throw new ContradaExceptionBloccante(DecodificaErrore.get5018(), ex);
		}
		return tipoTessere;
	}

	public List<TipoTesseraDTO> ricercaTipoTesseraPerIncasso(int idTipoIncasso)
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {
		// TODO Auto-generated method stub
		List<TipoTesseraDTO> tipoTessere = null;
		try {

			tipoTessere = tipoTesseraDao
					.getTipoTesseraPerIncasso(idTipoIncasso);
		}

		catch (Exception ex) {
			log.error(ex);
			throw new ContradaExceptionBloccante(DecodificaErrore.get5018(), ex);
		}
		return tipoTessere;
	}

	public List<TipoTesseraDTO> ricercaPerAnagraficaIncasso(long idAnag,
			int idTipoIncasso) throws ContradaExceptionBloccante,
			ContradaExceptionNonBloccante {
		// TODO Auto-generated method stub
		List<TipoTesseraDTO> tipoTessere = null;
		try {
			ITesseraDAO tipoTesseraDao = new TesseraDAO();
			tipoTessere = tipoTesseraDao
					.getTipoTesseraPerIncasso(idTipoIncasso);
		}

		catch (Exception ex) {
			log.error(ex);
			throw new ContradaExceptionBloccante(DecodificaErrore.get5018(), ex);
		}
		return tipoTessere;
	}

	public List<TesseraDTO> ricercaTessereRendicontabiliManualmente(int idAnag,
			int anno) throws ContradaExceptionBloccante,
			ContradaExceptionNonBloccante {
		// TODO Auto-generated method stub
		List<TesseraDTO> tessere = null;
		try {
			tessere = tipoTesseraDao.getTessereRendicontabiliManualmente(
					idAnag, anno);

		} catch (Exception ex) {
			log.error(ex);
			throw new ContradaExceptionBloccante(DecodificaErrore.get5018(), ex);
		}
		return tessere;

	}

	public List<TesseraDTO> ricercaTessereRendicontabiliManualmente(int idAnag,
			int idTipoTessera, int anno) throws ContradaExceptionBloccante,
			ContradaExceptionNonBloccante {
		// TODO Auto-generated method stub
		List<TesseraDTO> tessere = null;
		try {
			tessere = tipoTesseraDao.getTessereRendicontabiliManualmente(
					idAnag, idTipoTessera, anno);

		} catch (Exception ex) {
			log.error(ex);
			throw new ContradaExceptionBloccante(DecodificaErrore.get5018(), ex);
		}
		return tessere;
	}

	public List<TesseraStampataDTO> recuperaTessereDaStampare(
			int idTipoTessera, Integer idTipoIncasso, List<Integer> idEsattori,
			int anno) throws ContradaExceptionBloccante,
			ContradaExceptionNonBloccante {
		// TODO Auto-generated method stub
		List<TesseraStampataDTO> tessere = null;
		try {
			tessere = tipoTesseraDao.getStampaTessera(idTipoTessera,
					idTipoIncasso, idEsattori, anno);
			return tessere;

		} catch (Exception ex) {
			log.error(ex);
			throw new ContradaExceptionBloccante(DecodificaErrore.get5018(), ex);
		}

	}

	public List<TesseraStampataDTO> recuperaTessereDaStampare(
			int idTipoTessera, List<Integer> idAnagrafica, int anno)
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {
		// TODO Auto-generated method stub
		List<TesseraStampataDTO> tessere = null;
		try {
			tessere = tipoTesseraDao.getStampaTessera(idTipoTessera,
					idAnagrafica, anno);
			return tessere;

		} catch (Exception ex) {
			log.error(ex);
			throw new ContradaExceptionBloccante(DecodificaErrore.get5018(), ex);
		}

	}

	public List<TesseraStampataDTO> recuperaTessereDaStampare(
			int idTipoTessera, Integer idTipoIncasso, Integer idTipoCarica,
			int anno) throws ContradaExceptionBloccante,
			ContradaExceptionNonBloccante {
		// TODO Auto-generated method stub
		List<TesseraStampataDTO> tessere = null;
		try {
			tessere = tipoTesseraDao.getStampaTessera(idTipoTessera,
					idTipoIncasso, idTipoCarica, anno);
			return tessere;

		} catch (Exception ex) {
			log.error(ex);
			throw new ContradaExceptionBloccante(DecodificaErrore.get5018(), ex);
		}
	}

	public List<TesseraDTO> ricercaTesserePerDistinta(int anno, int distinta)
			throws ContradaExceptionBloccante {
		// TODO Auto-generated method stub
		List<TesseraDTO> tessere = null;
		try {
			tessere = tipoTesseraDao.getTesserePerDistinta(anno, distinta);
			return tessere;
		} catch (Exception ex) {
			log.error(ex);
			throw new ContradaExceptionBloccante(DecodificaErrore.get5018(), ex);
		}
	}

	public List<TesseraStampataDTO> recuperaTessereDaStampare(
			int idTipoTessera, Integer[] idTipoIncasso, int anno)
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {
		// TODO Auto-generated method stub
		List<TesseraStampataDTO> tessere = null;
		try {
			tessere = tipoTesseraDao.getStampaTessera(idTipoTessera,
					idTipoIncasso, anno);
			return tessere;

		} catch (Exception ex) {
			log.error(ex);
			throw new ContradaExceptionBloccante(DecodificaErrore.get5018(), ex);
		}
	}

	public List<TesseraStampataDTO> stampaTabulato(int idTipoTessera,
			Integer idTipoIncasso, Integer idTipoCarica, int anno)
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {
		// TODO Auto-generated method stub
		List<TesseraStampataDTO> tessere = null;
		try {
			tessere = tipoTesseraDao.getTabulato(idTipoTessera, idTipoIncasso,
					idTipoCarica, anno);
			return tessere;

		} catch (Exception ex) {
			log.error(ex);
			throw new ContradaExceptionBloccante(DecodificaErrore.get5018(), ex);
		}
	}

	public List<TesseraDTO> ricercaTessereNonPagantiUltimiAnni(
			int idTipoTessera, int annoDa,int annoA) throws ContradaExceptionBloccante,
			ContradaExceptionNonBloccante {
		// TODO Auto-generated method stub
		List<TesseraDTO> tessere = null;
		try {
			tessere = tipoTesseraDao.getTessereNonPagantiUltimiAnni(
					idTipoTessera, annoDa,annoA);
			return tessere;
		} catch (Exception ex) {
			log.error(ex);
			throw new ContradaExceptionBloccante(DecodificaErrore.get5018(), ex);
		}
	}

	public List<TesseraDTO> ricercaTessereNonPagantiUltimiAnni(
			int idTipoTessera, int annoDa, int tipoIncasso,int annoA,Integer tipoEsattore)
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {
		// TODO Auto-generated method stub
		List<TesseraDTO> tessere = null;
		try {
			tessere = tipoTesseraDao.getTessereNonPagantiUltimiAnni(
					idTipoTessera, annoDa, tipoIncasso,annoA,tipoEsattore);
			return tessere;
		} catch (Exception ex) {
			log.error(ex);
			throw new ContradaExceptionBloccante(DecodificaErrore.get5018(), ex);
		}
	}

	public List<TesseraStampataDTO> recuperaTessereDaStampareFineAnno(
			int idTipoTessera, Integer[] idTipoIncasso, int anno)
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {
		// TODO Auto-generated method stub
		List<TesseraStampataDTO> tessere = null;
		try {
			tessere = tipoTesseraDao.getStampaTesseraFineAnno(idTipoTessera,
					idTipoIncasso, anno);
			return tessere;

		} catch (Exception ex) {
			log.error(ex);
			throw new ContradaExceptionBloccante(DecodificaErrore.get5018(), ex);
		}
	}

	public List<TesseraDTO> ricercaTessereDaAllineare(Date dataRif)
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {
		// TODO Auto-generated method stub
		List<TesseraDTO> tessere = null;
		try {
			tessere = tipoTesseraDao.getTessereDaAllineare(dataRif);
			return tessere;

		} catch (Exception ex) {
			log.error(ex);
			throw new ContradaExceptionBloccante(DecodificaErrore.get5018(), ex);
		}
	}

}
