package it.contrada.bean;

import it.contrada.dao.interfaces.IFlussoEsitiDAO;
import it.contrada.dao.interfaces.IFlussoPreautorizzazioniRidDAO;
import it.contrada.dao.interfaces.IFlussoRidIncassoDAO;
import it.contrada.dominio.dto.TipoCasualiIncassoRidDTO;
import it.contrada.dominio.dto.TipoCasualiPreautDTO;
import it.contrada.dto.FlussoEsitoDTO;
import it.contrada.enumcontrada.TipoFlusso;
import it.contrada.exceptions.ContradaExceptionBloccante;
import it.contrada.exceptions.ContradaExceptionNonBloccante;
import it.contrada.incassorid.dto.IncassoRidDTO;
import it.contrada.interfaces.IRicercaFlusso;
import it.contrada.preautrid.dto.RidEsitoPreautDTO;
import it.contrada.util.DecodificaErrore;

import java.sql.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Session Bean implementation class RicercaFlusso
 */

public class RicercaFlusso implements IRicercaFlusso {

	/**
	 * Default constructor.
	 * @uml.property  name="flussoEsitoDAO"
	 * @uml.associationEnd  
	 */

	@Autowired
	IFlussoEsitiDAO flussoEsitoDAO;

	/**
	 * @uml.property  name="preautDAO"
	 * @uml.associationEnd  
	 */
	@Autowired
	IFlussoPreautorizzazioniRidDAO preautDAO;

	/**
	 * @uml.property  name="ridIncassoDAO"
	 * @uml.associationEnd  
	 */
	@Autowired
	IFlussoRidIncassoDAO ridIncassoDAO;
	
	private static Log log = LogFactory.getLog(RicercaFlusso.class);
	
	

	/**
	 * @param flussoEsitoDAO
	 * @uml.property  name="flussoEsitoDAO"
	 */
	public void setFlussoEsitoDAO(IFlussoEsitiDAO flussoEsitoDAO) {
		this.flussoEsitoDAO = flussoEsitoDAO;
	}

	/**
	 * @param preautDAO
	 * @uml.property  name="preautDAO"
	 */
	public void setPreautDAO(IFlussoPreautorizzazioniRidDAO preautDAO) {
		this.preautDAO = preautDAO;
	}

	/**
	 * @param ridIncassoDAO
	 * @uml.property  name="ridIncassoDAO"
	 */
	public void setRidIncassoDAO(IFlussoRidIncassoDAO ridIncassoDAO) {
		this.ridIncassoDAO = ridIncassoDAO;
	}

	public RicercaFlusso() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @throws ContradaExceptionBloccante
	 * @see IRicercaFlusso#ricercaFlusso(TipoFlusso)
	 */
	public List<FlussoEsitoDTO> ricercaFlusso(TipoFlusso flusso, int nrLast)
			throws ContradaExceptionBloccante {
		// TODO Auto-generated method stub
		try {
			return flussoEsitoDAO.getFlussoEsito(flusso, nrLast);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error(e);;
			throw new ContradaExceptionBloccante(DecodificaErrore.get5018(), e);
		}
	}

	public List<RidEsitoPreautDTO> ricercaEsitoPreautPerData(Date dtDa, Date dtA)
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {
		// TODO Auto-generated method stub
		try {
			return flussoEsitoDAO.getEsitoPreautPerData(dtDa, dtA);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error(e);
			throw new ContradaExceptionBloccante(DecodificaErrore.get5018(), e);
		}
	}

	public List<RidEsitoPreautDTO> ricercaEsitoPreautPerRid(int idRid)
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {
		// TODO Auto-generated method stub
		try {
			return flussoEsitoDAO.getEsitoPreautPerRid(idRid);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error(e);
			throw new ContradaExceptionBloccante(DecodificaErrore.get5018(), e);
		}
	}

	public List<TipoCasualiPreautDTO> elencaCausaliPreaut()
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {
		// TODO Auto-generated method stub
		try {
			return preautDAO.elencoCausaliPreaut();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error(e);
			throw new ContradaExceptionBloccante(DecodificaErrore.get5018(), e);
		}
	}

	public List<RidEsitoPreautDTO> ricercaEsitoPreaut(int idRid, Date dtDa,
			Date dtA) throws ContradaExceptionBloccante,
			ContradaExceptionNonBloccante {
		// TODO Auto-generated method stub
		try {
			return flussoEsitoDAO.getEsitoPreaut(idRid, dtDa, dtA);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error(e);
			throw new ContradaExceptionBloccante(DecodificaErrore.get5018(), e);
		}
	}

	public List<TipoCasualiIncassoRidDTO> elencaCausaliIncassiRid()
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {
		// TODO Auto-generated method stub
		try {
			return ridIncassoDAO.elencoCausaliIncassoRid();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error(e);
			throw new ContradaExceptionBloccante(DecodificaErrore.get5018(), e);
		}
	}

	public List<IncassoRidDTO> ricercaEsitoincassoRid(Integer anno,
			Integer mese, Integer tipoIncassoRid, Integer nrRid,
			Integer causaleIncasso) throws ContradaExceptionNonBloccante,ContradaExceptionBloccante {
		// TODO Auto-generated method stub
		try {
			return flussoEsitoDAO.getEsitoincassoRid(anno, mese, tipoIncassoRid, nrRid, causaleIncasso);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error(e);
			throw new ContradaExceptionBloccante(DecodificaErrore.get5018(), e);
		}
	}

}
