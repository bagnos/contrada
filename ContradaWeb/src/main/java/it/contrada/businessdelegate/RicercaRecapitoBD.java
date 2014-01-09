package it.contrada.businessdelegate;

import it.contrada.dominio.dto.TipoRecapitoDTO;
import it.contrada.exceptions.ContradaExceptionBloccante;
import it.contrada.exceptions.ContradaExceptionNonBloccante;

import it.contrada.web.util.Errori;

import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class RicercaRecapitoBD {
	/*
	private static IRicercaRecapito ricercaRecapito;
	private static Log log = LogFactory.getLog(RicercaRecapitoBD.class);

	static {
		InitialContext ctx;
		try {
			ctx = new InitialContext();
			ricercaRecapito = (IRicercaRecapito) ctx
					.lookup("ContradaEAR/RicercaRecapito/local");

		} catch (NamingException e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage(), e);
			throw new RuntimeException (e);
		}
	}

	public static List<TipoRecapitoDTO> elenca()
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {

		try {

			return ricercaRecapito.elencaTipoRecapito();
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

	
	}
*/
}
