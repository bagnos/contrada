package it.contrada.bean;

import it.contrada.dao.interfaces.ICapDAO;
import it.contrada.dao.interfaces.IStradaDAO;
import it.contrada.dto.CapDTO;
import it.contrada.dto.ComuneDTO;
import it.contrada.dto.LocalitaDTO;
import it.contrada.dto.ProvinciaDTO;
import it.contrada.dto.StradaDTO;
import it.contrada.exceptions.ContradaExceptionBloccante;
import it.contrada.interfaces.IGestioneStradario;
import it.contrada.util.DecodificaErrore;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Session Bean implementation class GestioneStradario
 */

public class GestioneStradario implements IGestioneStradario {



	/**
	 * @uml.property  name="stradaDao"
	 * @uml.associationEnd  
	 */
	@Autowired
	IStradaDAO stradaDao;
	
	/**
	 * @uml.property  name="capDao"
	 * @uml.associationEnd  
	 */
	@Autowired
	ICapDAO capDao;
	
	/**
	 * @param capDao
	 * @uml.property  name="capDao"
	 */
	public void setCapDao(ICapDAO capDao) {
		this.capDao = capDao;
	}



	private static Log log = LogFactory.getLog(GestioneStradario.class);

	
	
	/**
	 * @param stradaDao
	 * @uml.property  name="stradaDao"
	 */
	public void setStradaDao(IStradaDAO stradaDao) {
		this.stradaDao = stradaDao;
	}

	/**
	 * Default constructor.
	 */
	public GestioneStradario() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @throws ContradaExceptionBloccante 
	 * @see IGestioneStradario#inserisciStrada(StradaDTO)
	 */
	public int inserisciStrada(StradaDTO strada) throws ContradaExceptionBloccante {
		// TODO Auto-generated method stub
		try {
			return stradaDao.insertStrada(strada);
		} catch (Exception ex) {
			log.error(ex);
			throw new ContradaExceptionBloccante(DecodificaErrore.get5018(), ex);
		}
	}

	public int inserisciLocalita(LocalitaDTO localita)
			throws ContradaExceptionBloccante {
		// TODO Auto-generated method stub
		try {
			return stradaDao.insertLocalita(localita);
		} catch (Exception ex) {
			log.error(ex);
			throw new ContradaExceptionBloccante(DecodificaErrore.get5018(), ex);
		}
	}

	

	public int inserisciCap(CapDTO cap) throws ContradaExceptionBloccante {
		// TODO Auto-generated method stub
		try {
			return capDao.insertCAP(cap);
		} catch (Exception ex) {
			log.error(ex);
			throw new ContradaExceptionBloccante(DecodificaErrore.get5018(), ex);
		}
	}

	public ProvinciaDTO inserisciProvincia(ProvinciaDTO prov)
			throws ContradaExceptionBloccante {
		// TODO Auto-generated method stub
		try {
			prov.setCdProvincia(stradaDao.getMaxProvincia());
			stradaDao.insertProvincia(prov);
			
			ComuneDTO comune=new ComuneDTO(); 
			comune.setDsComune(prov.getDsProvincia());
			comune.setCdProvincia(prov.getCdProvincia());
			comune.setCdComune(stradaDao.getMaxComune(prov.getCdProvincia()));
			stradaDao.insertComune(comune);
			
			CapDTO cap=new CapDTO();
			cap.setCdCap(String.format("%5s",
					prov.getCdCap().toString())
					.replaceAll(" ", "0").toUpperCase());
			cap.setCdComune(comune.getCdComune());
			cap.setCdProv(prov.getCdProvincia());
			inserisciCap(cap);
			
			 return prov;
		} catch (Exception ex) {
			log.error(ex);
			throw new ContradaExceptionBloccante(DecodificaErrore.get5018(), ex);
		}
	}



}
