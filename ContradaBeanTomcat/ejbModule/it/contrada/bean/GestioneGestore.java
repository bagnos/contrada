package it.contrada.bean;

import it.contrada.dao.interfaces.IGestoreDAO;
import it.contrada.dto.GestoreDTO;
import it.contrada.exceptions.ContradaExceptionBloccante;
import it.contrada.interfaces.IGestioneGestore;
import it.contrada.util.DecodificaErrore;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Session Bean implementation class GestitoneGestore
 */

public class GestioneGestore implements IGestioneGestore {

	/**
	 * @uml.property  name="gestoreDAO"
	 * @uml.associationEnd  
	 */
	@Autowired
	private IGestoreDAO gestoreDAO;
	
	private static Log log = LogFactory.getLog(GestioneGestore.class);


	
	/**
	 * @param gestoreDAO
	 * @uml.property  name="gestoreDAO"
	 */
	public void setGestoreDAO(IGestoreDAO gestoreDAO) {
		this.gestoreDAO = gestoreDAO;
	}

	/**
	 * Default constructor.
	 */
	public GestioneGestore() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @throws ContradaExceptionBloccante
	 * @see IGestioneGestore#elencoGestori()
	 */
	public List<GestoreDTO> elencaGestori() throws ContradaExceptionBloccante {
		// TODO Auto-generated method stub
		try {
			return gestoreDAO.getGestori();
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
