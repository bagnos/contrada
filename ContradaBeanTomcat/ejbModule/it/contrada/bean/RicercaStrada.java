package it.contrada.bean;

import it.contrada.dao.interfaces.ICapDAO;
import it.contrada.dao.interfaces.IStradaDAO;
import it.contrada.dto.CapDTO;
import it.contrada.dto.LocalitaDTO;
import it.contrada.dto.StradaDTO;
import it.contrada.exceptions.ContradaExceptionBloccante;
import it.contrada.exceptions.ContradaExceptionNonBloccante;
import it.contrada.interfaces.IRicercaStrada;
import it.contrada.util.DecodificaErrore;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Session Bean implementation class RicercaStrada
 */

public class RicercaStrada implements IRicercaStrada {

	/**
	 * @uml.property  name="stradaDao"
	 * @uml.associationEnd  
	 */
	@Autowired
	private IStradaDAO stradaDao;

	/**
	 * @uml.property  name="capDao"
	 * @uml.associationEnd  
	 */
	@Autowired
	private ICapDAO capDao;

	private static Log log = LogFactory.getLog(RicercaStrada.class);

	/**
	 * @param stradaDao
	 * @uml.property  name="stradaDao"
	 */
	public void setStradaDao(IStradaDAO stradaDao) {
		this.stradaDao = stradaDao;
	}

	/**
	 * @param capDao
	 * @uml.property  name="capDao"
	 */
	public void setCapDao(ICapDAO capDao) {
		this.capDao = capDao;
	}

	/**
	 * @see IRicercaStrada#recuperaPerCap(String)
	 */
	public List<StradaDTO> recuperaPerCap(String cdCap)
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {
		List<StradaDTO> strade = null;
		try {

			strade = stradaDao.getStrada(cdCap);
		}

		catch (Exception ex) {
			log.error(ex);
			throw new ContradaExceptionBloccante(DecodificaErrore.get5018(), ex);
		}
		return strade;
	}

	public List<StradaDTO> recuperaPerCapViaParziale(String cdCap,
			String matchVia) throws ContradaExceptionBloccante,
			ContradaExceptionNonBloccante {
		// TODO Auto-generated method stub
		List<StradaDTO> strade = null;
		try {

			strade = stradaDao.getStrada(cdCap, matchVia);

		}

		catch (Exception ex) {
			log.error(ex);
			throw new ContradaExceptionBloccante(DecodificaErrore.get5018(), ex);
		}
		return strade;
	}

	public List<CapDTO> recuperaCapParziale(String matchCap, int cdProvincia)
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {
		// TODO Auto-generated method stub
		List<CapDTO> caps = null;
		try {

			caps = capDao.recuperaParzialeCap(matchCap, cdProvincia);
			return caps;
		}

		catch (Exception ex) {
			log.error(ex);
			throw new ContradaExceptionBloccante(DecodificaErrore.get5018(), ex);
		}

	}

	public List<LocalitaDTO> recuperaLocalitaPerCap(String cdCap,
			int cdProvincia, int cdComune) throws ContradaExceptionBloccante,
			ContradaExceptionNonBloccante {
		// TODO Auto-generated method stub
		List<LocalitaDTO> loc = null;
		try {
			loc = stradaDao.getLocalita(cdCap, cdProvincia, cdComune);
			return loc;
		} catch (Exception ex) {
			log.error(ex);
			throw new ContradaExceptionBloccante(DecodificaErrore.get5018(), ex);
		}
	}

	public List<StradaDTO> recuperaPerCapLocViaParziale(String cdCap,
			Integer idLoc, String matchVia) throws ContradaExceptionBloccante,
			ContradaExceptionNonBloccante {
		// TODO Auto-generated method stub
		List<StradaDTO> strade = null;
		try {

			strade = stradaDao.getStrada(cdCap, idLoc, matchVia);
			return strade;
		}

		catch (Exception ex) {
			log.error(ex);
			throw new ContradaExceptionBloccante(DecodificaErrore.get5018(), ex);
		}

	}

}
