package it.contrada.businessdelegate;

import it.contrada.bean.factory.ContradaPojoFactory;
import it.contrada.dto.CapDTO;
import it.contrada.exceptions.ContradaExceptionBloccante;
import it.contrada.exceptions.ContradaExceptionNonBloccante;
import it.contrada.interfaces.IRicercaCap;
import it.contrada.web.util.Errori;

import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class RicercaCapBD {
	private static IRicercaCap ricercaCap;
	private static Log log = LogFactory.getLog(RicercaAnagraficaBD.class);

	private RicercaCapBD() {
	}
	
	static
	{
	/*
		if (ricercaCap == null) {
			try {
			InitialContext ctx = new InitialContext();
			
				ricercaCap = (IRicercaCap) ctx
						.lookup("ContradaEAR/RicercaCap/local");
			} catch (NamingException e) {
				// TODO Auto-generated catch block
				log.error(e.getMessage(), e);
				throw new RuntimeException (e);
			}
		}*/
		ricercaCap=ContradaPojoFactory.getRicercaCapIstance();
	}

	public static List<CapDTO> recuperaCap(int cdProvincia, int cdComune)
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {
		List<CapDTO> caps = null;

		try {
			

			caps = ricercaCap.recuperaCap(cdProvincia, cdComune);
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
		return caps;
	}
}
