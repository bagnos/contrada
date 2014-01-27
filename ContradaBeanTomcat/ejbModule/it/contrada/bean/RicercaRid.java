package it.contrada.bean;

import it.contrada.dao.interfaces.IRidDAO;
import it.contrada.dominio.dto.TipoIncassoDTO;
import it.contrada.dominio.dto.TipoStatoRidDTO;
import it.contrada.dto.MembroRidDTO;
import it.contrada.dto.RidDTO;
import it.contrada.exceptions.ContradaExceptionBloccante;
import it.contrada.exceptions.ContradaExceptionNonBloccante;
import it.contrada.interfaces.IRicercaRid;
import it.contrada.util.DecodificaErrore;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Session Bean implementation class RicercaRid
 */

public class RicercaRid implements IRicercaRid {

	/**
	 * @uml.property  name="ridDao"
	 * @uml.associationEnd  
	 */
	@Autowired
	private IRidDAO ridDao;

	private static Log log = LogFactory.getLog(RicercaRid.class);

	/**
	 * @param ridDao
	 * @uml.property  name="ridDao"
	 */
	public void setRidDao(IRidDAO ridDao) {
		this.ridDao = ridDao;
	}

	/**
	 * Default constructor.
	 */
	public RicercaRid() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see IRicercaRid#ricercaPerNomeCognome(String, String)
	 */
	public List<MembroRidDTO> ricercaPerNomeCognome(String nome, String cognome)
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {
		// TODO Auto-generated method stub
		List<MembroRidDTO> membri = null;
		try {

			membri = ridDao.getMembro(cognome, nome);
		}

		catch (Exception ex) {
			log.error(ex);
			throw new ContradaExceptionBloccante(DecodificaErrore.get5018(), ex);
		}
		return membri;
	}

	/**
	 * @see IRicercaRid#ricercaPerCognome(String)
	 */
	public List<MembroRidDTO> ricercaPerCognome(String cognome)
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {
		// TODO Auto-generated method stub
		List<MembroRidDTO> membri = null;
		try {

			membri = ridDao.getMembroPerCognome(cognome);
		}

		catch (Exception ex) {
			log.error(ex);
			throw new ContradaExceptionBloccante(DecodificaErrore.get5018(), ex);
		}
		return membri;
	}

	public List<TipoStatoRidDTO> elencaStati()
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {
		// TODO Auto-generated method stub
		List<TipoStatoRidDTO> stati = null;
		try {

			stati = ridDao.getStatiRid();
		}

		catch (Exception ex) {
			log.error(ex);
			throw new ContradaExceptionBloccante(DecodificaErrore.get5018(), ex);
		}
		return stati;
	}

	public RidDTO ricercaPerId(int idRid) throws ContradaExceptionBloccante,
			ContradaExceptionNonBloccante {
		// TODO Auto-generated method stub
		try {
			RidDTO rid = ridDao.getRid(idRid);
			return rid;
		}

		catch (Exception ex) {
			log.error(ex);
			throw new ContradaExceptionBloccante(DecodificaErrore.get5018(), ex);
		}
	}

	public List<TipoIncassoDTO> elencaTipoIncassiRid()
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {
		// TODO Auto-generated method stub
		try {
			return ridDao.elencaTipoIncassi();
		} catch (Exception ex) {
			log.error(ex);
			throw new ContradaExceptionBloccante(DecodificaErrore.get5018(), ex);
		}
	}

	public List<MembroRidDTO> ricercaPerRid(int idRid)
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {
		// TODO Auto-generated method stub
		try {
			return ridDao.getMembro(idRid);
		} catch (Exception ex) {
			log.error(ex);
			throw new ContradaExceptionBloccante(DecodificaErrore.get5018(), ex);
		}
	}

	public List<MembroRidDTO> ricercaPerCognomeParazile(String matchCognome)
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {
		// TODO Auto-generated method stub
		try {
			return ridDao.getMembroPerCognomeParziale(matchCognome);
		} catch (Exception ex) {
			log.error(ex);
			throw new ContradaExceptionBloccante(DecodificaErrore.get5018(), ex);
		}
	}

	public List<RidDTO> ricercaPerStato(List<Integer> cdStato,Integer idTipoTessera)
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {
		// TODO Auto-generated method stub
		try {
			return ridDao.getRidPerStato(cdStato,idTipoTessera);
		} catch (Exception ex) {
			log.error(ex);
			throw new ContradaExceptionBloccante(DecodificaErrore.get5018(), ex);
		}
	}

	public List<MembroRidDTO> ricercaPerCognomeNomeParzaile(
			String matchCognome, String nome)
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {
		// TODO Auto-generated method stub
		try {
			return ridDao.getMembroPerCognomeNomeParziale(matchCognome,nome);
		} catch (Exception ex) {
			log.error(ex);
			throw new ContradaExceptionBloccante(DecodificaErrore.get5018(), ex);
		}
	}

}
