package it.contrada.bean;

import it.contrada.dao.interfaces.IFamigliaDAO;
import it.contrada.dto.MembroFamigliaDTO;
import it.contrada.exceptions.ContradaExceptionBloccante;
import it.contrada.exceptions.ContradaExceptionNonBloccante;
import it.contrada.interfaces.IRicercaFamiglia;
import it.contrada.util.DecodificaErrore;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Session Bean implementation class RicercaFamiglia
 */

public class RicercaFamiglia implements IRicercaFamiglia {

	/**
	 * @uml.property  name="famigliaDao"
	 * @uml.associationEnd  
	 */
	@Autowired
	private IFamigliaDAO famigliaDao;
	
	private static Log log = LogFactory.getLog(GestioneAnagrafica.class);

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
	public RicercaFamiglia() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see IRicercaFamiglia#ricercaPerCognome(String)
	 */
	public List<MembroFamigliaDTO> ricercaPerCognome(String cognome)
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {
		// TODO Auto-generated method stub
		List<MembroFamigliaDTO> membri = null;
		try {

			membri = famigliaDao.getMembroPerCognome(cognome);
		}

		catch (Exception ex) {
			log.error(ex);
			throw new ContradaExceptionBloccante(DecodificaErrore.get5018(), ex);
		}
		return membri;
	}

	/**
	 * @see IRicercaFamiglia#ricercaPerNomeCognome(String, String)
	 */
	public List<MembroFamigliaDTO> ricercaPerNomeCognome(String nome,
			String cognome) throws ContradaExceptionBloccante,
			ContradaExceptionNonBloccante {
		// TODO Auto-generated method stub
		List<MembroFamigliaDTO> membri = null;
		try {

			membri = famigliaDao.getMembro(cognome, nome);
		}

		catch (Exception ex) {
			log.error(ex);
			throw new ContradaExceptionBloccante(DecodificaErrore.get5018(), ex);
		}
		return membri;
	}

	public List<MembroFamigliaDTO> ricercaPerCodice(int cdFamiglia)
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {
		// TODO Auto-generated method stub
		List<MembroFamigliaDTO> membri = null;
		try {

			membri = famigliaDao.getMembro(cdFamiglia);
		}

		catch (Exception ex) {
			log.error(ex);
			throw new ContradaExceptionBloccante(DecodificaErrore.get5018(), ex);
		}
		return membri;
	}

	public List<MembroFamigliaDTO> ricercaMembroPerCognomeParziale(
			String matchCognome) throws ContradaExceptionBloccante,
			ContradaExceptionNonBloccante {
		// TODO Auto-generated method stub
		List<MembroFamigliaDTO> membri = null;
		try {

			membri = famigliaDao.getMembroPerCognomeParziale(matchCognome);
		}

		catch (Exception ex) {
			log.error(ex);
			throw new ContradaExceptionBloccante(DecodificaErrore.get5018(), ex);
		}
		return membri;
	}

	public List<MembroFamigliaDTO> ricercaParzialePerCognomeNome(
			String cognome, String nome) throws ContradaExceptionBloccante,
			ContradaExceptionNonBloccante {
		// TODO Auto-generated method stub
		try {
			List<MembroFamigliaDTO> anags = famigliaDao
					.getAnagraficaParzialePerCognomeNome(cognome, nome);
			return anags;
		}

		catch (Exception ex) {
			log.error(ex);
			throw new ContradaExceptionBloccante(DecodificaErrore.get5018(), ex);
		}
	}

}
