package it.contrada.businessdelegate;

import it.contrada.bean.factory.ContradaPojoFactory;
import it.contrada.dto.ComuneDTO;
import it.contrada.exceptions.ContradaExceptionBloccante;
import it.contrada.exceptions.ContradaExceptionNonBloccante;
import it.contrada.interfaces.IRicercaComune;
import it.contrada.web.util.Errori;

import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class RicercaComuneBD {

	private static IRicercaComune ricercaComune;
	private static Log log = LogFactory.getLog(RicercaComuneBD.class);

	private RicercaComuneBD() {
	}
	
	static
	{
		/*
		if (ricercaComune == null) {
			try {
			InitialContext ctx = new InitialContext();
			
				ricercaComune = (IRicercaComune) ctx
						.lookup("ContradaEAR/RicercaComune/local");
			} catch (NamingException e) {
				// TODO Auto-generated catch block
				log.error(e.getMessage(), e);
				throw new RuntimeException (e);
			}
		}*/
		ricercaComune=ContradaPojoFactory.getRicercaComuneIstance();
	}

	public static List<ComuneDTO> ricercaPerStato(String cdIsoStato)
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {
		List<ComuneDTO> comuni = null;

		try {
			
			comuni = ricercaComune.ricercaPerStato(cdIsoStato);
		} catch (ContradaExceptionBloccante e)
		{
			log.error(e.getMessage(), e);
			throw e;
		}
		
		catch (ContradaExceptionNonBloccante e)
		{
			log.error(e.getMessage(), e);
			throw e;
		}
		
		catch (Exception e1) {
			// TODO Auto-generated catch block
			log.error(e1.getMessage(), e1);
			throw new ContradaExceptionBloccante(Errori.TEMP_PROB_COLL);
		}

		return comuni;
	}

	public static List<ComuneDTO> ricercaPerProvincia(int cdProvincia)
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {
		List<ComuneDTO> comuni = null;

		try {
			
			comuni = ricercaComune.recuperaPerProvincia(cdProvincia);
		} catch (ContradaExceptionBloccante e)
		{
			log.error(e.getMessage(), e);
			throw e;
		}
		
		catch (ContradaExceptionNonBloccante e)
		{
			log.error(e.getMessage(), e);
			throw e;
		}
		
		catch (Exception e1) {
			// TODO Auto-generated catch block
			log.error(e1.getMessage(), e1);
			throw new ContradaExceptionBloccante(Errori.TEMP_PROB_COLL);
		}

		return comuni;
	}
}
