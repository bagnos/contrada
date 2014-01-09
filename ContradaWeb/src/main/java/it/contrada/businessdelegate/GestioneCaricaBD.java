package it.contrada.businessdelegate;

import java.util.List;

import it.contrada.bean.factory.ContradaPojoFactory;
import it.contrada.dominio.dto.TipoCaricaDTO;
import it.contrada.exceptions.ContradaExceptionBloccante;
import it.contrada.exceptions.ContradaExceptionNonBloccante;
import it.contrada.interfaces.IGestioneAnagrafica;
import it.contrada.interfaces.IGestioneCarica;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class GestioneCaricaBD {
	private static IGestioneCarica gestioneCarica;
	private static Log log = LogFactory.getLog(GestioneCaricaBD.class);
	static {
	
		gestioneCarica=(IGestioneCarica)ContradaPojoFactory.getGestioneCaricaInstance();
		/*	if (gestioneCarica == null) {
			InitialContext ctx;
			try {
				ctx = new InitialContext();*
				gestioneCarica = (IGestioneCarica) ctx
						.lookup("ContradaEAR/GestioneCarica/local");
			} catch (NamingException e) {
				// TODO Auto-generated catch block
				log.error(e.getMessage(), e);
				throw new RuntimeException (e);
			}

		}*/
	}
	
	public static int aggiornaCarica(List<TipoCaricaDTO> cariche,
			boolean aggiornaTessereInCorso) throws ContradaExceptionBloccante, ContradaExceptionNonBloccante
	{
		return gestioneCarica.aggiornaCarica(cariche, aggiornaTessereInCorso);
	}

}
