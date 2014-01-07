package it.contrada.bean;

import it.contrada.dao.interfaces.IUtenteDAO;
import it.contrada.exceptions.ContradaExceptionBloccante;
import it.contrada.exceptions.ContradaExceptionNonBloccante;
import it.contrada.interfaces.IGestioneUtente;
import it.contrada.util.DecodificaErrore;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Session Bean implementation class GestioneUtente
 */

public class GestioneUtente implements IGestioneUtente {

	/**
	 * @uml.property  name="utenteDao"
	 * @uml.associationEnd  
	 */
	@Autowired
	IUtenteDAO utenteDao;

	private static Log log = LogFactory.getLog(GestioneTessera.class);
	
	

	/**
	 * @param utenteDao
	 * @uml.property  name="utenteDao"
	 */
	public void setUtenteDao(IUtenteDAO utenteDao) {
		this.utenteDao = utenteDao;
	}

	/**
	 * Default constructor.
	 */
	public GestioneUtente() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @throws ContradaExceptionBloccante
	 * @throws ContradaExceptionNonBloccante 
	 * @see IGestioneUtente#cambiaPassword(String, String, String, String)
	 */
	public void cambiaPassword(String utente, String vecchiaPsw,
			String nuovaPsw, String confNuovaPsw)
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {
		// TODO Auto-generated method stub
		try {

			if ((utente == null || utente.isEmpty())
					&& (vecchiaPsw == null || vecchiaPsw.isEmpty())) {
				throw new ContradaExceptionNonBloccante(DecodificaErrore
						.getError("33"));
			}

			if (!nuovaPsw.equals(confNuovaPsw)) {
				throw new ContradaExceptionNonBloccante(DecodificaErrore
						.getError("31"));
			}

			int rows = utenteDao.updatePsw(utente, vecchiaPsw, nuovaPsw);

			if (rows == 0) {
				throw new ContradaExceptionNonBloccante(DecodificaErrore
						.getError("32"));

			}

		} catch (ContradaExceptionNonBloccante ex) {
			log.error(ex);
			throw ex;

		}

		catch (ContradaExceptionBloccante ex) {
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
