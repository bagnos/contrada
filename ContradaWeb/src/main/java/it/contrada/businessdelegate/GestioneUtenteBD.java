package it.contrada.businessdelegate;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import it.contrada.bean.factory.ContradaPojoFactory;
import it.contrada.exceptions.ContradaExceptionBloccante;
import it.contrada.exceptions.ContradaExceptionNonBloccante;
import it.contrada.interfaces.IGestioneTessera;
import it.contrada.interfaces.IGestioneUtente;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class GestioneUtenteBD {

	private static IGestioneUtente gestioneUtente;
	private static Log log = LogFactory.getLog(GestioneUtenteBD.class);
	
	static {
		/*
		if (gestioneUtente == null) {
			InitialContext ctx;
			try {
				ctx = new InitialContext();
				gestioneUtente = (IGestioneUtente) ctx
						.lookup("ContradaEAR/GestioneUtente/local");
			} catch (NamingException e) {
				// TODO Auto-generated catch block
				log.error(e.getMessage(), e);
				throw new RuntimeException(e);
			}

		}*/
		gestioneUtente=ContradaPojoFactory.getGestioneUtenteIstance();
	}

	public static void cambiaPassword(String utente, String vecchiaPsw,
			String nuovaPsw, String confNuovaPsw) throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {
		 gestioneUtente.cambiaPassword(utente, vecchiaPsw, nuovaPsw, confNuovaPsw);
	}

}
