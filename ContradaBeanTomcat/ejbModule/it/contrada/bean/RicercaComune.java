package it.contrada.bean;

import it.contrada.dao.interfaces.IComuneDAO;
import it.contrada.dto.ComuneDTO;
import it.contrada.exceptions.ContradaExceptionBloccante;
import it.contrada.exceptions.ContradaExceptionNonBloccante;
import it.contrada.interfaces.IRicercaComune;
import it.contrada.util.DecodificaErrore;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Session Bean implementation class RicercaComune
 */

public class RicercaComune implements IRicercaComune {

	/**
	 * @uml.property  name="comuneDao"
	 * @uml.associationEnd  
	 */
	@Autowired
	private IComuneDAO comuneDao;
	
	private static Log log = LogFactory.getLog(RicercaComune.class);

	/**
	 * @param comuneDao
	 * @uml.property  name="comuneDao"
	 */
	public void setComuneDao(IComuneDAO comuneDao) {
		this.comuneDao = comuneDao;
	}

	/**
	 * Default constructor.
	 */
	public RicercaComune() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see IRicercaComune#ricercaPerProvincia(int)
	 */
	public List<ComuneDTO> recuperaPerProvincia(int cdProvincia)
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {
		// TODO Auto-generated method stub
		List<ComuneDTO> comuni = null;
		try {

			comuni = comuneDao.getComune(cdProvincia);
		}

		catch (Exception ex) {
			log.error(ex);
			throw new ContradaExceptionBloccante(DecodificaErrore.get5018(), ex);
		}
		return comuni;
	}

	public List<ComuneDTO> ricercaPerStato(String cdIsoStato)
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {
		// TODO Auto-generated method stub
		List<ComuneDTO> comuni = null;
		try {

			comuni = comuneDao.getComune(cdIsoStato);
		}

		catch (Exception ex) {
			log.error(ex);
			throw new ContradaExceptionBloccante(DecodificaErrore.get5018(), ex);
		}
		return comuni;
	}

}
