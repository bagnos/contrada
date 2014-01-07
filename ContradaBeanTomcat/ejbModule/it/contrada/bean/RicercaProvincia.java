package it.contrada.bean;

import it.contrada.dao.interfaces.IProvinciaDAO;
import it.contrada.dto.ProvinciaDTO;
import it.contrada.exceptions.ContradaExceptionBloccante;
import it.contrada.exceptions.ContradaExceptionNonBloccante;
import it.contrada.interfaces.IRicercaProvincia;
import it.contrada.util.DecodificaErrore;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Session Bean implementation class RicercaProvincia
 */

public class RicercaProvincia implements IRicercaProvincia {

    /**
	 * @uml.property  name="provinciaDao"
	 * @uml.associationEnd  
	 */
    @Autowired    
	private IProvinciaDAO provinciaDao;
    
    private static Log log = LogFactory.getLog(RicercaProvincia.class);
	
	/**
	 * @param provinciaDao
	 * @uml.property  name="provinciaDao"
	 */
	public void setProvinciaDao(IProvinciaDAO provinciaDao) {
		this.provinciaDao = provinciaDao;
	}

	/**
     * Default constructor. 
     */
    public RicercaProvincia() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see IRicercaProvincia#ricercaPerRegione(String)
     */
    public List<ProvinciaDTO> recuperaPerRegione(int cdRegione) throws ContradaExceptionBloccante,ContradaExceptionNonBloccante
    {
        // TODO Auto-generated method stub
    	List<ProvinciaDTO> provincie=null;
		try
		{		
		
			provincie=provinciaDao.getProvincia(cdRegione);	
		}
		
		catch (Exception ex)
		{			
			log.error(ex);
			throw new ContradaExceptionBloccante(DecodificaErrore.get5018(),ex);
		}
		return provincie;
    }

	public List<ProvinciaDTO> recuperaPerStato(String cdIsoStato)
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
    	List<ProvinciaDTO> provincie=null;
		try
		{		
			
			provincie=provinciaDao.getProvinciaPerStato(cdIsoStato);	
		}
		
		catch (Exception ex)
		{			
			log.error(ex);
			throw new ContradaExceptionBloccante(DecodificaErrore.get5018(),ex);
		}
		return provincie;
	}

	public List<ProvinciaDTO> elenca() throws ContradaExceptionBloccante,
			ContradaExceptionNonBloccante {
		// TODO Auto-generated method stub
		List<ProvinciaDTO> provincie=null;
		try
		{		
		
			provincie=provinciaDao.getAll();	
		}
		
		catch (Exception ex)
		{	
			log.error(ex);
			throw new ContradaExceptionBloccante(DecodificaErrore.get5018(),ex);
		}
		return provincie;
	}

}
