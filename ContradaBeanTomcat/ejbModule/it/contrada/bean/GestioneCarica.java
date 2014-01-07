package it.contrada.bean;

import it.contrada.dao.interfaces.ICaricaDAO;
import it.contrada.dominio.dto.TipoCaricaDTO;
import it.contrada.dominio.dto.TipoTesseraDTO;
import it.contrada.exceptions.ContradaExceptionBloccante;
import it.contrada.interfaces.IGestioneCarica;
import it.contrada.interfaces.IGestioneTessera;
import it.contrada.util.DecodificaErrore;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Session Bean implementation class GestioneCarica
 */

public class GestioneCarica implements IGestioneCarica {

	/**
	 * @uml.property  name="caricaDao"
	 * @uml.associationEnd  
	 */
	@Autowired
	private ICaricaDAO caricaDao;

	/**
	 * @uml.property  name="tessera"
	 * @uml.associationEnd  
	 */
	@Autowired
	private IGestioneTessera tessera;

	private static Log log = LogFactory.getLog(GestioneCarica.class);
	
	

	/**
	 * @return
	 * @uml.property  name="caricaDao"
	 */
	public ICaricaDAO getCaricaDao() {
		return caricaDao;
	}

	/**
	 * @param caricaDao
	 * @uml.property  name="caricaDao"
	 */
	public void setCaricaDao(ICaricaDAO caricaDao) {
		this.caricaDao = caricaDao;
	}

	/**
	 * @return
	 * @uml.property  name="tessera"
	 */
	public IGestioneTessera getTessera() {
		return tessera;
	}

	/**
	 * @param tessera
	 * @uml.property  name="tessera"
	 */
	public void setTessera(IGestioneTessera tessera) {
		this.tessera = tessera;
	}

	/**
	 * Default constructor.
	 */
	public GestioneCarica() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @throws ContradaExceptionBloccante
	 * @see IGestioneCarica#aggiornaCarica(List<TipoCaricaDTO>)
	 */
	public int aggiornaCarica(List<TipoCaricaDTO> cariche,
			boolean aggiornaTessereInCorso) throws ContradaExceptionBloccante {
		// TODO Auto-generated method stub
		int rows = 0;
		TipoTesseraDTO tipoTessera = null;
		try {

			List<TipoTesseraDTO> tesseraModificate = new ArrayList<TipoTesseraDTO>();

			if (cariche == null) {
				return 0;
			}
			for (TipoCaricaDTO carica : cariche) {
				{

					tipoTessera = new TipoTesseraDTO();
					tipoTessera.setIdTipoTessera(carica.getIdTipoTessera());
					tipoTessera.setImTessera(carica.getImMinimo());
					tipoTessera.setImTesseraPrec(carica.getImMinimoPrec());
					tipoTessera.setIdCarica(carica.getIdCarica());
					tesseraModificate.add(tipoTessera);

				}
			}
			rows = caricaDao.aggiornaCarica(cariche);
			if (aggiornaTessereInCorso) {

				rows += tessera.aggiornaImportoTessera(tesseraModificate);

			}

			return rows;
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
