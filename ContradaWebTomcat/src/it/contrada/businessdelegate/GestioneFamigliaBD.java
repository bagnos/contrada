package it.contrada.businessdelegate;

import it.contrada.bean.factory.ContradaPojoFactory;
import it.contrada.dto.FamigliaDTO;
import it.contrada.exceptions.ContradaExceptionBloccante;
import it.contrada.exceptions.ContradaExceptionNonBloccante;
import it.contrada.interfaces.IGestioneFamiglia;
import it.contrada.web.util.Errori;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class GestioneFamigliaBD {
	private static IGestioneFamiglia gestioneFamiglia;
	private static Log log = LogFactory.getLog(GestioneFamigliaBD.class);

	private GestioneFamigliaBD() {
	}

	static {
		/*
		if (gestioneFamiglia == null) {
			InitialContext ctx;
			try {
				ctx = new InitialContext();
				gestioneFamiglia = (IGestioneFamiglia) ctx
						.lookup("ContradaEAR/GestioneFamiglia/local");
			} catch (NamingException e) {
				// TODO Auto-generated catch block
				log.error(e.getMessage(), e);
				throw new RuntimeException (e);
			}

		}*/
		gestioneFamiglia=ContradaPojoFactory.getGestioneFamigliaInstance();
	}

	public static void aggiornaFamiglia(FamigliaDTO famiglia)
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {
		try {

			gestioneFamiglia.aggiornaFamiglia(famiglia);
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
