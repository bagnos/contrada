package it.contrada.bean;

import it.contrada.dao.interfaces.ICaricaDAO;
import it.contrada.dominio.dto.TipoCaricaDTO;
import it.contrada.dto.CaricaTesseraDTO;
import it.contrada.exceptions.ContradaExceptionBloccante;
import it.contrada.exceptions.ContradaExceptionNonBloccante;
import it.contrada.interfaces.IRicercaCarica;
import it.contrada.util.DecodificaErrore;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Session Bean implementation class RicercaCarica
 */

public class RicercaCarica implements IRicercaCarica {

	/**
	 * @uml.property  name="caricaDao"
	 * @uml.associationEnd  
	 */
	@Autowired
	private ICaricaDAO caricaDao;
	
	private static Log log = LogFactory.getLog(RicercaCarica.class);

	/**
	 * @param caricaDao
	 * @uml.property  name="caricaDao"
	 */
	public void setCaricaDao(ICaricaDAO caricaDao) {
		this.caricaDao = caricaDao;
	}

	/**
	 * Default constructor.
	 */
	public RicercaCarica() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see IRicercaCarica#ricercaPerTipoTessera(int)
	 */
	public List<CaricaTesseraDTO> ricercaPerTipoTessera(int idTipoTessera)
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {
		// TODO Auto-generated method stub
		List<CaricaTesseraDTO> caricaTessera = null;
		try {

			caricaTessera = caricaDao.getCaricaPerTipo(idTipoTessera);
		} catch (ContradaExceptionNonBloccante e) {
			log.error(e);
			throw e;
		} catch (ContradaExceptionBloccante e) {
			log.error(e);
			throw e;
		} catch (Exception ex) {
			throw new ContradaExceptionBloccante(DecodificaErrore.get5018(), ex);
		}
		return caricaTessera;
	}

	public List<TipoCaricaDTO> elencaCariche()
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {
		// TODO Auto-generated method stub
		List<TipoCaricaDTO> cariche = null;
		try {

			cariche = caricaDao.getCariche();
			return cariche;
		} catch (ContradaExceptionNonBloccante e) {
			log.error(e);
			throw e;
		} catch (ContradaExceptionBloccante e) {
			log.error(e);
			throw e;
		} catch (Exception ex) {
			log.error(ex);
			throw new ContradaExceptionBloccante(DecodificaErrore.get5018(), ex);
		}

	}

}
