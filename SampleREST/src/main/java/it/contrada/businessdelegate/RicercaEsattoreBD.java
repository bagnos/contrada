package it.contrada.businessdelegate;

import it.contrada.bean.factory.ContradaPojoFactory;
import it.contrada.dto.EsattoreDTO;
import it.contrada.exceptions.ContradaExceptionBloccante;
import it.contrada.exceptions.ContradaExceptionNonBloccante;
import it.contrada.interfaces.IRicercaEsattore;
import it.contrada.web.util.Errori;

import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class RicercaEsattoreBD {
	private static IRicercaEsattore ricercaEsattore;
	private static Log log = LogFactory.getLog(RicercaEsattoreBD.class);

	static {

		/*
		if (ricercaEsattore == null) {
			InitialContext ctx;
			try {
				ctx = new InitialContext();
				ricercaEsattore = (IRicercaEsattore) ctx
						.lookup("ContradaEAR/RicercaEsattore/local");

			} catch (NamingException e) {
				// TODO Auto-generated catch block
				log.error(e.getMessage(), e);
				throw new RuntimeException (e);
			}
		}*/
		ricercaEsattore=ContradaPojoFactory.getRicercaEsattoreIstance();
	}

	public static List<EsattoreDTO> elenca() throws ContradaExceptionBloccante,
			ContradaExceptionNonBloccante {

		try {

			return ricercaEsattore.elencaEsattore();

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
