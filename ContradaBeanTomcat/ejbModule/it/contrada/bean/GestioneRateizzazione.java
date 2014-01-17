package it.contrada.bean;

import it.contrada.bean.utils.Utente;
import it.contrada.common.util.LogUtil;
import it.contrada.dao.interfaces.IDistintaDAO;
import it.contrada.dao.interfaces.IRateizzazioneDAO;
import it.contrada.dao.interfaces.ITesseraDAO;
import it.contrada.dto.DistintaDTO;
import it.contrada.dto.OperazioneDTO;
import it.contrada.dto.RateizzazioneDTO;
import it.contrada.exceptions.ContradaExceptionBloccante;
import it.contrada.exceptions.ContradaExceptionNonBloccante;
import it.contrada.interfaces.IGestioneRateizzazione;
import it.contrada.pojo.Operazione;
import it.contrada.util.BaseUtil;
import it.contrada.util.Constanti;
import it.contrada.util.DecodificaErrore;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Session Bean implementation class GestioneRateizzazione
 */

public class GestioneRateizzazione implements IGestioneRateizzazione {

	private static Log log = LogFactory.getLog(GestioneRateizzazione.class);

	/**
	 * @uml.property  name="rateizzazioneDao"
	 * @uml.associationEnd  
	 */
	private IRateizzazioneDAO rateizzazioneDao;

	/**
	 * @uml.property  name="distintaDao"
	 * @uml.associationEnd  
	 */
	private IDistintaDAO distintaDao;

	/**
	 * @uml.property  name="tesseraDao"
	 * @uml.associationEnd  
	 */
	private ITesseraDAO tesseraDao;

	/**
	 * @uml.property  name="operazioneBO"
	 * @uml.associationEnd  
	 */
	private Operazione operazioneBO;

	/**
	 * @param operazioneBO
	 * @uml.property  name="operazioneBO"
	 */
	public void setOperazioneBO(Operazione operazioneBO) {
		this.operazioneBO = operazioneBO;
	}

	/**
	 * @return
	 * @uml.property  name="rateizzazioneDao"
	 */
	public IRateizzazioneDAO getRateizzazioneDao() {
		return rateizzazioneDao;
	}

	/**
	 * @param rateizzazioneDao
	 * @uml.property  name="rateizzazioneDao"
	 */
	public void setRateizzazioneDao(IRateizzazioneDAO rateizzazioneDao) {
		this.rateizzazioneDao = rateizzazioneDao;
	}

	/**
	 * @return
	 * @uml.property  name="distintaDao"
	 */
	public IDistintaDAO getDistintaDao() {
		return distintaDao;
	}

	/**
	 * @param distintaDao
	 * @uml.property  name="distintaDao"
	 */
	public void setDistintaDao(IDistintaDAO distintaDao) {
		this.distintaDao = distintaDao;
	}

	/**
	 * @return
	 * @uml.property  name="tesseraDao"
	 */
	public ITesseraDAO getTesseraDao() {
		return tesseraDao;
	}

	/**
	 * @param tesseraDao
	 * @uml.property  name="tesseraDao"
	 */
	public void setTesseraDao(ITesseraDAO tesseraDao) {
		this.tesseraDao = tesseraDao;
	}

	/**
	 * Default constructor.
	 */
	public GestioneRateizzazione() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see IGestioneRateizzazione#inserisciRateizzazione(RateizzazioneDTO)
	 */
	public DistintaDTO inserisciRateizzazione(
			List<RateizzazioneDTO> rateizzazioni)
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {
		// TODO Auto-generated method stub

		String user = Utente.getUser();
		

		try {

			if (rateizzazioni == null || rateizzazioni.isEmpty()) {
				throw new ContradaExceptionBloccante(DecodificaErrore
						.getError("7"));
			}

			int quotaTot = 0;
			int nrAnno = rateizzazioni.get(0).getNrAnno();
			for (RateizzazioneDTO rateizzazione : rateizzazioni) {
				quotaTot += rateizzazione.getImRata();
			}

			int nrDistinta = distintaDao.getMaxAnno(nrAnno);

			DistintaDTO distinta = new DistintaDTO();

			OperazioneDTO operazioneDTO = operazioneBO.inserisciOperazione(
					user, String.format(Constanti.OPERAZIONE_INSERT_DISTINTA,
							nrDistinta));

			distinta.setOperazione(operazioneDTO);

			distinta.setImDistinta(quotaTot);
			distinta.setNrAnno(nrAnno);
			distinta.setNrDistinta(nrDistinta);
			distinta.setTxOper(String.format(
					"Incassata %d tessere per un totale di %s",
					rateizzazioni.size(), BaseUtil.formatImporto(quotaTot)));
			distinta.setTxUtente(operazioneDTO.getUser());
			int rowsDist = distintaDao.insertDistinta(distinta);
			if (rowsDist == 0) {
				throw new ContradaExceptionBloccante(DecodificaErrore
						.getError("6"));
			}
			LogUtil.logTraceMessage(log, String.format("distinta inserita %S",
					distinta));

			for (RateizzazioneDTO rateizzazione : rateizzazioni) {
				rateizzazione.setNrAnnoDistinta(distinta.getNrAnno());
				rateizzazione.setNrDistinta(distinta.getNrDistinta());

				int rows = rateizzazioneDao.insertRateizzazione(rateizzazione);
				LogUtil.logTraceMessage(log, String.format(
						"rateizzazione inserita %S", rateizzazione));

				if (rows == 0) {
					throw new ContradaExceptionBloccante(DecodificaErrore
							.getError("4"));
				}

			}

			return distinta;
		}

		catch (ContradaExceptionBloccante ex) {
			log.error(ex);
			throw ex;
		}

		catch (Exception ex) {
			log.error(ex);
			throw new ContradaExceptionBloccante(DecodificaErrore.get5018(), ex);
		}

	}

	public void eliminaRateizzazione(RateizzazioneDTO rateizzazione)
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {
		// TODO Auto-generated method stub
		try {
			int rows = rateizzazioneDao.eliminaRateizzazione(rateizzazione);
			if (rows == 0) {
				throw new ContradaExceptionBloccante(DecodificaErrore
						.getError("8"));
			}
		}

		catch (ContradaExceptionBloccante ex) {
			log.error(ex);
			throw ex;
		}

		catch (Exception ex) {
			log.error(ex);
			throw new ContradaExceptionBloccante(DecodificaErrore.get5018(), ex);
		}
	}

}
