package it.contrada.bean;

import it.contrada.dao.interfaces.IEsattoreDAO;
import it.contrada.dto.EsattoreDTO;
import it.contrada.exceptions.ContradaExceptionBloccante;
import it.contrada.exceptions.ContradaExceptionNonBloccante;
import it.contrada.interfaces.IRicercaEsattore;
import it.contrada.util.DecodificaErrore;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Session Bean implementation class RicercaEsattore
 */

public class RicercaEsattore implements IRicercaEsattore {

	/**
	 * @uml.property  name="esattoreDao"
	 * @uml.associationEnd  
	 */
	@Autowired
	private IEsattoreDAO esattoreDao;
	
	private static Log log = LogFactory.getLog(RicercaEsattore.class);
	
	
	/**
	 * @param esattoreDao
	 * @uml.property  name="esattoreDao"
	 */
	public void setEsattoreDao(IEsattoreDAO esattoreDao) {
		this.esattoreDao = esattoreDao;
	}

	/**
     * Default constructor. 
     */
    public RicercaEsattore() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see IRicercaEsattore#elencaEsattore()
     */
    public List<EsattoreDTO> elencaEsattore() throws ContradaExceptionBloccante,ContradaExceptionNonBloccante{
        // TODO Auto-generated method stub
    	// TODO Auto-generated method stub
    	List<EsattoreDTO> esattori=null;
		try
		{		
	
			esattori=esattoreDao.getEsattore();	
		}
		
		catch (Exception ex)
		{	
			log.error(ex);
			throw new ContradaExceptionBloccante(DecodificaErrore.get5018(),ex);
		}
		return esattori;
    }

}
