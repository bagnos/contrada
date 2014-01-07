package it.contrada.bean;

import it.contrada.dao.interfaces.IMeseDAO;
import it.contrada.dominio.dto.TipoMeseDTO;
import it.contrada.enumcontrada.TipoIncassoRid;
import it.contrada.exceptions.ContradaExceptionBloccante;
import it.contrada.exceptions.ContradaExceptionNonBloccante;
import it.contrada.interfaces.IRicercaMese;
import it.contrada.util.DecodificaErrore;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Session Bean implementation class RicercaMese
 */

public class RicercaMese implements IRicercaMese {



	/**
	 * @uml.property  name="meseDao"
	 * @uml.associationEnd  
	 */
	@Autowired
	IMeseDAO meseDao;
	
	private static Log log = LogFactory.getLog(RicercaMese.class);

	/**
	 * @param meseDao
	 * @uml.property  name="meseDao"
	 */
	public void setMeseDao(IMeseDAO meseDao) {
		this.meseDao = meseDao;
	}

	/**
	 * Default constructor.
	 */
	public RicercaMese() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see IRicercaMese#ricercaMese(TipoIncassoRid)
	 */
	public List<TipoMeseDTO> ricercaMese(int incasso)
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {
		// TODO Auto-generated method stub
		try {
			return meseDao.ricercaMese(incasso);
		} catch (ContradaExceptionBloccante ex) {
			
			throw ex;
		}

		catch (Exception ex) {
			log.error(ex);
			throw new ContradaExceptionBloccante(DecodificaErrore.get5018(), ex);
		}
	}

	public List<TipoMeseDTO> elencaMesi() throws ContradaExceptionBloccante,
			ContradaExceptionNonBloccante {
		// TODO Auto-generated method stub
		try {
			return meseDao.elencaMese();
		} catch (ContradaExceptionBloccante ex) {
			log.error(ex);
			throw ex;
		}

		catch (Exception ex) {
			log.error(ex);
			throw new ContradaExceptionBloccante(DecodificaErrore.get5018(), ex);
		}
	}

}
