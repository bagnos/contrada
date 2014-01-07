package it.contrada.bean;

import it.contrada.dao.interfaces.ICapDAO;
import it.contrada.dto.CapDTO;
import it.contrada.exceptions.ContradaExceptionBloccante;
import it.contrada.exceptions.ContradaExceptionNonBloccante;
import it.contrada.interfaces.IRicercaCap;
import it.contrada.util.DecodificaErrore;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Session Bean implementation class RicercaCap
 */

public class RicercaCap implements IRicercaCap {

	/**
	 * @uml.property  name="capDao"
	 * @uml.associationEnd  
	 */
	@Autowired
	private ICapDAO capDao;
	
	private static Log log = LogFactory.getLog(RicercaCap.class);

	/**
	 * @param capDao
	 * @uml.property  name="capDao"
	 */
	public void setCapDao(ICapDAO capDao) {
		this.capDao = capDao;
	}

	/**
	 * Default constructor.
	 */
	public RicercaCap() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see IRicercaCap#recuperaCap(int, int)
	 */
	public List<CapDTO> recuperaCap(int cdProvincia, int cdComune)
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {
		// TODO Auto-generated method stub
		List<CapDTO> caps = null;
		try {

			caps = capDao.recuperaCap(cdProvincia, cdComune);

		}

		catch (Exception ex) {
			log.error(ex);
			throw new ContradaExceptionBloccante(DecodificaErrore.get5018(), ex);
		}
		return caps;
	}

}
