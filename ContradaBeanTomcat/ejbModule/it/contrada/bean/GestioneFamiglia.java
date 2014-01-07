package it.contrada.bean;

import it.contrada.dao.interfaces.IFamigliaDAO;
import it.contrada.dto.FamigliaDTO;
import it.contrada.exceptions.ContradaExceptionBloccante;
import it.contrada.exceptions.ContradaExceptionNonBloccante;
import it.contrada.interfaces.IGestioneFamiglia;
import it.contrada.util.DecodificaErrore;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Session Bean implementation class InserisciFamiglia
 */

public class GestioneFamiglia implements IGestioneFamiglia {

	/**
	 * @uml.property  name="famigliaDao"
	 * @uml.associationEnd  
	 */
	@Autowired
	private IFamigliaDAO famigliaDao;

	/**
	 * @return
	 * @uml.property  name="famigliaDao"
	 */
	public IFamigliaDAO getFamigliaDao() {
		return famigliaDao;
	}
	
	private static Log log = LogFactory.getLog(GestioneFamiglia.class);

	/**
	 * @param famigliaDao
	 * @uml.property  name="famigliaDao"
	 */
	public void setFamigliaDao(IFamigliaDAO famigliaDao) {
		this.famigliaDao = famigliaDao;
	}

	/**
	 * Default constructor.
	 */
	public GestioneFamiglia() {
		// TODO Auto-generated constructor stub
	}

	public FamigliaDTO inserisciFamglia(int idCapoFamiglia)
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {
		// TODO Auto-generated method stub
		try {

			FamigliaDTO famigliaDTO = famigliaDao
					.inserisciFamiglia(idCapoFamiglia);

			return famigliaDTO;
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

	public void aggiornaFamiglia(FamigliaDTO famiglia)
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {
		// TODO Auto-generated method stub
		try {
			int rows = famigliaDao.aggiornaFamiglia(famiglia);
			if (rows == 0) {
				throw new ContradaExceptionBloccante(DecodificaErrore
						.getError("4"));
			}
		} catch (ContradaExceptionBloccante ex) {
			log.error(ex);
			throw ex;
		} catch (Exception ex) {
			log.error(ex);
			throw new ContradaExceptionBloccante(DecodificaErrore.get5018(), ex);
		}

	}
}