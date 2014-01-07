package it.contrada.bean;

import it.contrada.dao.interfaces.IIncassoDAO;
import it.contrada.dominio.dto.TipoIncassoDTO;
import it.contrada.exceptions.ContradaExceptionBloccante;
import it.contrada.exceptions.ContradaExceptionNonBloccante;
import it.contrada.interfaces.IRicercaIncasso;
import it.contrada.util.DecodificaErrore;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Session Bean implementation class RicercaIncasso
 */

public class RicercaIncasso implements IRicercaIncasso {

	/**
	 * @uml.property  name="incassoDao"
	 * @uml.associationEnd  
	 */
	@Autowired
	private IIncassoDAO incassoDao;
	
	private static Log log = LogFactory.getLog(RicercaIncasso.class);

	/**
	 * @param incassoDao
	 * @uml.property  name="incassoDao"
	 */
	public void setIncassoDao(IIncassoDAO incassoDao) {
		this.incassoDao = incassoDao;
	}

	/**
	 * Default constructor.
	 */
	public RicercaIncasso() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see IRicercaIncasso#elencaTipoIncasso()
	 */
	public List<TipoIncassoDTO> elencaTipoIncasso()
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {
		// TODO Auto-generated method stub
		List<TipoIncassoDTO> tipoIncasso = null;
		try {

			tipoIncasso = incassoDao.getTipoIncasso();
		}

		catch (Exception ex) {
			log.error(ex);
			throw new ContradaExceptionBloccante(DecodificaErrore.get5018(), ex);
		}
		return tipoIncasso;

	}
}
