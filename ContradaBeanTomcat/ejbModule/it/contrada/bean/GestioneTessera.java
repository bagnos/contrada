package it.contrada.bean;

import it.contrada.dao.AnagrafeDAO;
import it.contrada.dao.interfaces.IAnagrafeDAO;
import it.contrada.dao.interfaces.ITesseraDAO;
import it.contrada.dominio.dto.TipoTesseraDTO;
import it.contrada.enumcontrada.TipoStatoAnagrafica;
import it.contrada.exceptions.ContradaExceptionBloccante;
import it.contrada.exceptions.ContradaExceptionNonBloccante;
import it.contrada.interfaces.IGestioneTessera;
import it.contrada.util.DecodificaErrore;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Session Bean implementation class GestioneTessera
 */

public class GestioneTessera implements IGestioneTessera {

	private static Log log = LogFactory.getLog(GestioneTessera.class);

	/**
	 * @uml.property name="tesseraDao"
	 * @uml.associationEnd
	 */
	@Autowired
	private ITesseraDAO tesseraDao;
	private IAnagrafeDAO anagrafeDao;

	public void setAnagrafeDao(IAnagrafeDAO anagrafeDao) {
		this.anagrafeDao = anagrafeDao;
	}

	/**
	 * @param tesseraDao
	 * @uml.property name="tesseraDao"
	 */
	public void setTesseraDao(ITesseraDAO tesseraDao) {
		this.tesseraDao = tesseraDao;
	}

	/**
	 * Default constructor.
	 */
	public GestioneTessera() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see IGestioneTessera#preparaTesseraAnnoInCorso()
	 */

	public int allineaQuotaTessere(java.sql.Date dataRif)
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {
		// TODO Auto-generated method stub
		int rows = 0;
		try {
			//riattivo le anagrafiche sopsese
			anagrafeDao.aggiornaStatoAnagrafica(
					TipoStatoAnagrafica.Attiva.getStatoAnagrafica(),
					TipoStatoAnagrafica.Sospesa.getStatoAnagrafica());
			
			// si aggiornano le tessere con la nuova carica ed importo
			rows = tesseraDao.allineaQuoteTessera(dataRif);
			if (rows > 0) {
				Calendar calendar = GregorianCalendar.getInstance();
				calendar.setTime(dataRif);

				tesseraDao.deleteStoricoAnno(calendar
						.get(GregorianCalendar.YEAR));

				tesseraDao.insertStoricoAnno(calendar
						.get(GregorianCalendar.YEAR));
				
			}

		} catch (ContradaExceptionBloccante ex) {
			log.error(ex);
			throw ex;
		}

		catch (Exception ex) {
			log.error(ex);
			throw new ContradaExceptionBloccante(DecodificaErrore.get5018(), ex);
		}
		return rows;
	}

	public int aggiornaImportoTessera(List<TipoTesseraDTO> tessere)
			throws ContradaExceptionBloccante {
		int rows = 0;

		try {

			if (tessere == null) {
				return 0;
			}

			rows += tesseraDao.aggiornaImportoTessera(tessere);
			rows += tesseraDao.aggiornaImportoTesseraStorico(tessere);

			return rows;
		} catch (ContradaExceptionBloccante ex) {
			log.error(ex);
			throw ex;
		}

		// TODO Auto-generated catch block
		catch (Exception ex) {
			log.error(ex);
			throw new ContradaExceptionBloccante(DecodificaErrore.get5018(), ex);
		}

	}

	public int aggiornaTipoTessera(List<TipoTesseraDTO> tessere,
			boolean aggiornaTessereInCorso) throws ContradaExceptionBloccante,
			ContradaExceptionNonBloccante {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		int rows = 0;

		try {

			if (tessere == null) {
				return 0;
			}

			rows = tesseraDao.aggiornaTipoTessera(tessere);
			if (aggiornaTessereInCorso) {
				aggiornaImportoTessera(tessere);
			}

			return rows;
		} catch (ContradaExceptionBloccante ex) {
			log.error(ex);
			throw ex;
		}

		// TODO Auto-generated catch block
		catch (Exception ex) {
			log.error(ex);
			throw new ContradaExceptionBloccante(DecodificaErrore.get5018(), ex);
		}
	}

}
