package it.contrada.bean;

import it.contrada.dao.interfaces.IRegioneDAO;
import it.contrada.dto.RegioneDTO;
import it.contrada.exceptions.ContradaExceptionBloccante;
import it.contrada.exceptions.ContradaExceptionNonBloccante;
import it.contrada.interfaces.IRicercaRegione;
import it.contrada.util.DecodificaErrore;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Session Bean implementation class RicercaRegione
 */

public class RicercaRegione implements IRicercaRegione {

	/**
	 * @uml.property  name="regioneDao"
	 * @uml.associationEnd  
	 */
	@Autowired
	private IRegioneDAO regioneDao;

	private static Log log = LogFactory.getLog(RicercaRegione.class);

	/**
	 * @param regioneDao
	 * @uml.property  name="regioneDao"
	 */
	public void setRegioneDao(IRegioneDAO regioneDao) {
		this.regioneDao = regioneDao;
	}

	/**
	 * Default constructor.
	 */
	public RicercaRegione() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see IRicercaRegione#ricercaPerNazione(String)
	 */
	public List<RegioneDTO> recuperaPerNazione(String cdStato)
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {
		// TODO Auto-generated method stub
		List<RegioneDTO> regioni = null;
		try {

			regioni = regioneDao.getRegione(cdStato);
		}

		catch (Exception ex) {
			log.error(ex);
			throw new ContradaExceptionBloccante(DecodificaErrore.get5018(), ex);
		}
		return regioni;
	}

}
