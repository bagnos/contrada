package it.contrada.businessdelegate;

import it.contrada.bean.factory.ContradaPojoFactory;
import it.contrada.dto.GestoreDTO;
import it.contrada.exceptions.ContradaExceptionBloccante;
import it.contrada.exceptions.ContradaExceptionNonBloccante;
import it.contrada.interfaces.IGestioneGestore;

import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class GestioneGestoreBD {

	private static IGestioneGestore gestioneGestore;
	private static Log log = LogFactory.getLog(GestioneFlussoBD.class);

	static {
		/*
		 * if (gestioneGestore == null) { InitialContext ctx; try { ctx = new
		 * InitialContext(); gestioneGestore = (IGestioneGestore) ctx
		 * .lookup("ContradaEAR/GestioneGestore/local"); } catch
		 * (NamingException e) { // TODO Auto-generated catch block
		 * log.error(e.getMessage(), e); throw new RuntimeException (e); }
		 * 
		 * }
		 */
		gestioneGestore = ContradaPojoFactory.getGestioneGestoreInstance();
	}

	public static List<GestoreDTO> elencaGestori()
			throws ContradaExceptionNonBloccante, ContradaExceptionBloccante {
		List<GestoreDTO> gestori = gestioneGestore.elencaGestori();
		return gestori;
	}

	public static int insertGestore(GestoreDTO gestore)
			throws ContradaExceptionNonBloccante, ContradaExceptionBloccante {
		return gestioneGestore.insertGestore(gestore);
	}

	public static int deleteGestore(int idGestore)
			throws ContradaExceptionNonBloccante, ContradaExceptionBloccante {
		return gestioneGestore.deleteGestore(idGestore);
	}
}
