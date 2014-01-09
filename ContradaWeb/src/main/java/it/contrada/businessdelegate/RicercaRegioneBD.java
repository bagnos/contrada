package it.contrada.businessdelegate;

import it.contrada.bean.factory.ContradaPojoFactory;
import it.contrada.dto.RegioneDTO;
import it.contrada.exceptions.ContradaExceptionBloccante;
import it.contrada.exceptions.ContradaExceptionNonBloccante;
import it.contrada.interfaces.IRicercaRegione;
import it.contrada.web.util.Errori;

import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class RicercaRegioneBD {
	private static IRicercaRegione ricercaRegione;
	private static Log log = LogFactory.getLog(RicercaRegioneBD.class);

	static {
		/*
		InitialContext ctx;
		try {
			ctx = new InitialContext();
			ricercaRegione = (IRicercaRegione) ctx
					.lookup("ContradaEAR/RicercaRegione/local");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage(), e);
			throw new RuntimeException (e);
		}*/
		ricercaRegione=ContradaPojoFactory.getRicercaRegioneIstance();

	}

	public List<RegioneDTO> ricercaPerStato(String cdIsoStato)
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {

		try {

			return ricercaRegione.recuperaPerNazione(cdIsoStato);
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
