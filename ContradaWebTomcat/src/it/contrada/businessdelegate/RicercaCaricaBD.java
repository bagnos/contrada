package it.contrada.businessdelegate;

import it.contrada.bean.factory.ContradaPojoFactory;
import it.contrada.dominio.dto.TipoCaricaDTO;
import it.contrada.dto.CaricaTesseraDTO;
import it.contrada.exceptions.ContradaExceptionBloccante;
import it.contrada.exceptions.ContradaExceptionNonBloccante;
import it.contrada.interfaces.IRicercaCarica;
import it.contrada.web.util.Errori;

import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class RicercaCaricaBD {
	private static IRicercaCarica ricercaCarica;
	private static Log log = LogFactory.getLog(RicercaCaricaBD.class);

	private RicercaCaricaBD() {
	}

	static {
		/*
		if (ricercaCarica == null) {
			InitialContext ctx;
			try {
				ctx = new InitialContext();
				ricercaCarica = (IRicercaCarica) ctx
						.lookup("ContradaEAR/RicercaCarica/local");
			} catch (NamingException e) {
				// TODO Auto-generated catch block
				log.error(e.getMessage(), e);
				throw new RuntimeException(e);
			}

		}*/
		ricercaCarica=ContradaPojoFactory.getRicercaCaricaIstance();
	}

	public static List<CaricaTesseraDTO> ricercaPerTessera(int tipoTessera)
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {
		List<CaricaTesseraDTO> cariche = null;

		cariche = ricercaCarica.ricercaPerTipoTessera(tipoTessera);

		return cariche;
	}

	public static List<TipoCaricaDTO> elencaCariche()
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {
		List<TipoCaricaDTO> cariche = ricercaCarica.elencaCariche();
		return cariche;

	}
}
