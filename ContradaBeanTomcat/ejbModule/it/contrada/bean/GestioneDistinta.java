package it.contrada.bean;

import it.contrada.dao.interfaces.IDistintaDAO;
import it.contrada.dto.DistintaDTO;
import it.contrada.exceptions.ContradaExceptionBloccante;
import it.contrada.interfaces.IGestioneDistinta;
import it.contrada.util.DecodificaErrore;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Session Bean implementation class GestioneDistinta
 */

public class GestioneDistinta implements IGestioneDistinta {

	/**
	 * @uml.property  name="distintaDao"
	 * @uml.associationEnd  
	 */
	@Autowired
	private IDistintaDAO distintaDao;
	
	private static Log log = LogFactory.getLog(GestioneDistinta.class);
	

	/**
	 * @param distintaDao
	 * @uml.property  name="distintaDao"
	 */
	public void setDistintaDao(IDistintaDAO distintaDao) {
		this.distintaDao = distintaDao;
	}

	/**
	 * Default constructor.
	 */
	public GestioneDistinta() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @throws ContradaExceptionBloccante 
	 * @see IGestioneDistinta#ricercaDistintaPerAnno(int)
	 */
	public List<DistintaDTO> ricercaDistintaPerAnno(int anno) throws ContradaExceptionBloccante {
		// TODO Auto-generated method stub
		try {
			return distintaDao.getDistinta(anno);
		} catch (Exception ex) {
			log.error(ex);
			throw new ContradaExceptionBloccante(DecodificaErrore.get5018(), ex);
		}
	}

}
