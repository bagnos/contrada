package it.contrada.businessdelegate;

import it.contrada.bean.factory.ContradaPojoFactory;
import it.contrada.dto.CapDTO;
import it.contrada.dto.LocalitaDTO;
import it.contrada.dto.StradaDTO;
import it.contrada.exceptions.ContradaExceptionBloccante;
import it.contrada.interfaces.IGestioneRid;
import it.contrada.interfaces.IGestioneStradario;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class GestioneStradarioBD {
	private static IGestioneStradario gestioneStradario;
	private static Log log = LogFactory.getLog(GestioneStradarioBD.class);

	static {
		/*
		if (gestioneStradario == null) {
			InitialContext ctx;
			try {
				ctx = new InitialContext();
				gestioneStradario = (IGestioneStradario) ctx
						.lookup("ContradaEAR/GestioneStradario/local");
			} catch (NamingException e) {
				// TODO Auto-generated catch block
				log.error(e.getMessage(), e);
				throw new RuntimeException (e);
			}

		}*/
		gestioneStradario=ContradaPojoFactory.getGestioneStradarioInstance();
	}
	
	public static int inserisciStrada(StradaDTO strada) throws ContradaExceptionBloccante {
		return gestioneStradario.inserisciStrada(strada);
	}
	
	public static int inserisciLocalita(LocalitaDTO localita) throws ContradaExceptionBloccante {
		return gestioneStradario.inserisciLocalita(localita);
	}
	
	public static int inserisciCap(CapDTO cap) throws ContradaExceptionBloccante
	{
		return gestioneStradario.inserisciCap(cap);
	}

}
