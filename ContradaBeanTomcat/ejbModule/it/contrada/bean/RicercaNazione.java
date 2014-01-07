package it.contrada.bean;

import it.contrada.dao.interfaces.INazioneDAO;
import it.contrada.dto.NazioneDTO;
import it.contrada.exceptions.ContradaExceptionBloccante;
import it.contrada.exceptions.ContradaExceptionNonBloccante;
import it.contrada.interfaces.IRicercaNazione;
import it.contrada.util.DecodificaErrore;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Session Bean implementation class RicercaNazione
 */

public class RicercaNazione implements IRicercaNazione {

	/**
	 * @uml.property  name="nazioneDao"
	 * @uml.associationEnd  
	 */
	@Autowired
	private INazioneDAO nazioneDao;
	
	private static Log log = LogFactory.getLog(RicercaNazione.class);

	/**
	 * @param nazioneDao
	 * @uml.property  name="nazioneDao"
	 */
	public void setNazioneDao(INazioneDAO nazioneDao) {
		this.nazioneDao = nazioneDao;
	}

	/**
	 * Default constructor.
	 */
	public RicercaNazione() {
		// TODO Auto-generated constructor stub
	}

	public List<NazioneDTO> elencaNazione() throws ContradaExceptionBloccante,
			ContradaExceptionNonBloccante {
		// TODO Auto-generated method stub
		List<NazioneDTO> nazioni = null;
		try {

			nazioni = nazioneDao.getNazione();
		}

		catch (Exception ex) {
			log.error(ex);
			throw new ContradaExceptionBloccante(DecodificaErrore.get5018(), ex);
		}
		return nazioni;
	}

}
