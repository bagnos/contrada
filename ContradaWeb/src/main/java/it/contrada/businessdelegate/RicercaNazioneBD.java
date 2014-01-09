package it.contrada.businessdelegate;

import java.util.List;

import it.contrada.bean.factory.ContradaPojoFactory;
import it.contrada.dto.NazioneDTO;
import it.contrada.exceptions.ContradaExceptionBloccante;
import it.contrada.exceptions.ContradaExceptionNonBloccante;
import it.contrada.interfaces.IRicercaNazione;
import it.contrada.web.util.Costante;
import it.contrada.web.util.Errori;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class RicercaNazioneBD {

	private static IRicercaNazione ricercaNazione;
	private static Log log = LogFactory.getLog(RicercaNazioneBD.class);

	static {
		/*
		InitialContext ctx = null;
		try {
			ctx = new InitialContext();
			ricercaNazione = (IRicercaNazione) ctx
					.lookup("ContradaEAR/RicercaNazione/local");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage(), e);
			throw new RuntimeException (e);
		}*/
		ricercaNazione=ContradaPojoFactory.getRicercaNazioneIstance();

	}

	public List<NazioneDTO> elencaNazione() throws ContradaExceptionBloccante,
			ContradaExceptionNonBloccante {

		try {

			return ricercaNazione.elencaNazione();
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
}
