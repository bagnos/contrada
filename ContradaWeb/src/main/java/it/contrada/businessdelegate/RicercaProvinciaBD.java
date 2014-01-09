package it.contrada.businessdelegate;

import it.contrada.bean.factory.ContradaPojoFactory;
import it.contrada.dto.ProvinciaDTO;
import it.contrada.exceptions.ContradaExceptionBloccante;
import it.contrada.exceptions.ContradaExceptionNonBloccante;
import it.contrada.interfaces.IRicercaProvincia;
import it.contrada.web.util.Errori;

import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class RicercaProvinciaBD {
	private static IRicercaProvincia ricercaProvincia;
	private static Log log = LogFactory.getLog(RicercaProvinciaBD.class);

	static {
		/*
		 * InitialContext ctx; try { ctx = new InitialContext();
		 * ricercaProvincia = (IRicercaProvincia) ctx
		 * .lookup("ContradaEAR/RicercaProvincia/local"); } catch
		 * (NamingException e) { // TODO Auto-generated catch block
		 * log.error(e.getMessage(), e); throw new RuntimeException (e); }
		 */
		ricercaProvincia = ContradaPojoFactory.getRicercaProvinciaIstance();

	}

	public List<ProvinciaDTO> ricercaPerStato(String cdIsoStato)
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {

		try {

			return ricercaProvincia.recuperaPerStato(cdIsoStato);
		} catch (ContradaExceptionBloccante e) {
			log.error(e.getMessage(), e);
			throw e;
		}

		catch (ContradaExceptionNonBloccante e) {
			log.error(e.getMessage(), e);
			throw e;
		}

		catch (Exception e1) {
			// TODO Auto-generated catch block
			log.error(e1.getMessage(), e1);
			throw new ContradaExceptionBloccante(Errori.TEMP_PROB_COLL);
		}

	}

	public List<ProvinciaDTO> ricercaPerRegione(int cdRegione)
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {

		try {

			return ricercaProvincia.recuperaPerRegione(cdRegione);

		} catch (ContradaExceptionBloccante e) {
			log.error(e.getMessage(), e);
			throw e;
		}

		catch (ContradaExceptionNonBloccante e) {
			log.error(e.getMessage(), e);
			throw e;
		}

		catch (Exception e1) {
			// TODO Auto-generated catch block
			log.error(e1.getMessage(), e1);
			throw new ContradaExceptionBloccante(Errori.TEMP_PROB_COLL);
		}

	}

	public List<ProvinciaDTO> elenca() throws ContradaExceptionBloccante,
			ContradaExceptionNonBloccante {

		try {

			return ricercaProvincia.elenca();
		} catch (ContradaExceptionBloccante e) {
			log.error(e.getMessage(), e);
			throw e;
		}

		catch (ContradaExceptionNonBloccante e) {
			log.error(e.getMessage(), e);
			throw e;
		}

		catch (Exception e1) {
			// TODO Auto-generated catch block
			log.error(e1.getMessage(), e1);
			throw new ContradaExceptionBloccante(Errori.TEMP_PROB_COLL);
		}

	}

}
