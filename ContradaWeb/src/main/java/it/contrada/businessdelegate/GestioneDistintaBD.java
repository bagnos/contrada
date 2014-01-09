package it.contrada.businessdelegate;

import java.util.List;

import it.contrada.bean.factory.ContradaPojoFactory;
import it.contrada.dto.DistintaDTO;
import it.contrada.exceptions.ContradaExceptionBloccante;
import it.contrada.interfaces.IGestioneCarica;
import it.contrada.interfaces.IGestioneDistinta;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class GestioneDistintaBD {
	private static IGestioneDistinta gestioneDistinta;
	private static Log log = LogFactory.getLog(GestioneDistintaBD.class);
	static {
		/*
		if (gestioneDistinta == null) {
			InitialContext ctx;
			try {
				ctx = new InitialContext();
				gestioneDistinta = (IGestioneDistinta) ctx
						.lookup("ContradaEAR/GestioneDistinta/local");
			} catch (NamingException e) {
				// TODO Auto-generated catch block
				log.error(e.getMessage(), e);
				throw new RuntimeException (e);
			}

		}*/
		gestioneDistinta=(IGestioneDistinta)ContradaPojoFactory.getGestioneDistintaInstance();
	}
	public static List<DistintaDTO> ricercaDistintaPerAnno(int anno) throws ContradaExceptionBloccante {
		return gestioneDistinta.ricercaDistintaPerAnno(anno);
	}

}
