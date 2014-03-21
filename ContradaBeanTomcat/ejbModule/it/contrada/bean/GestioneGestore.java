package it.contrada.bean;

import it.contrada.dao.TesseraDAO;
import it.contrada.dao.interfaces.IEsattoreDAO;
import it.contrada.dao.interfaces.IGestoreDAO;
import it.contrada.dto.EsattoreDTO;
import it.contrada.dto.GestoreDTO;
import it.contrada.exceptions.ContradaExceptionBloccante;
import it.contrada.exceptions.ContradaExceptionNonBloccante;
import it.contrada.interfaces.IGestioneGestore;
import it.contrada.util.DecodificaErrore;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Session Bean implementation class GestitoneGestore
 */

public class GestioneGestore implements IGestioneGestore {

	@Autowired
	private IGestoreDAO gestoreDAO;
	private IEsattoreDAO esattoreDAO;

	public void setEsattoreDAO(IEsattoreDAO esattoreDAO) {
		this.esattoreDAO = esattoreDAO;
	}

	private static Log log = LogFactory.getLog(GestioneGestore.class);

	public void setGestoreDAO(IGestoreDAO gestoreDAO) {
		this.gestoreDAO = gestoreDAO;
	}

	public GestioneGestore() {
		// TODO Auto-generated constructor stub
	}

	public List<GestoreDTO> elencaGestori() throws ContradaExceptionBloccante {
		// TODO Auto-generated method stub
		try {
			return gestoreDAO.getGestori();
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

	public int insertGestore(GestoreDTO gestore)
			throws ContradaExceptionNonBloccante, ContradaExceptionBloccante {
		// TODO Auto-generated method stub
		try {
			// lo inserisco anche come esattore
			EsattoreDTO es = new EsattoreDTO();
			es.setIdEsattore(gestore.getIdGestore());
			es.setDsEsattore(String.format("%s %s",	new Object[] { gestore.getCognome(), gestore.getNome() }));
			esattoreDAO.insertEsattore(es);

			// inserisco il gestore
			return gestoreDAO.insertGestore(gestore);
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

	public int deleteGestore(int idGestore)
			throws ContradaExceptionNonBloccante, ContradaExceptionBloccante {
		// TODO Auto-generated method stub
		try {
			return gestoreDAO.deleteGestore(idGestore);
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
