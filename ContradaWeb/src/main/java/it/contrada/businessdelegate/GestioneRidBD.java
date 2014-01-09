package it.contrada.businessdelegate;

import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import it.contrada.bean.factory.ContradaPojoFactory;
import it.contrada.dto.MembroRidDTO;
import it.contrada.dto.RidDTO;
import it.contrada.exceptions.ContradaExceptionBloccante;
import it.contrada.exceptions.ContradaExceptionNonBloccante;
import it.contrada.interfaces.IGestioneAnagrafica;
import it.contrada.interfaces.IGestioneRid;
import it.contrada.web.util.Errori;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class GestioneRidBD {
	private static IGestioneRid gestioneRid;
	private static Log log = LogFactory.getLog(GestioneRidBD.class);

	static {
		/*
		if (gestioneRid == null) {
			InitialContext ctx;
			try {
				ctx = new InitialContext();
				gestioneRid = (IGestioneRid) ctx
						.lookup("ContradaEAR/GestioneRid/local");
			} catch (NamingException e) {
				// TODO Auto-generated catch block
				log.error(e.getMessage(), e);
				throw new RuntimeException (e);
			}

		}*/
		gestioneRid=ContradaPojoFactory.getGestioneRid();
	}

	public static void aggiornaRid(RidDTO ridDTO)
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {

		try {
			gestioneRid.aggiornaRid(ridDTO);
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

	public static RidDTO inserisciRidConMembri(RidDTO ridDTO)
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {

		try {
			return gestioneRid.inserisciRidConMembri(ridDTO);
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
