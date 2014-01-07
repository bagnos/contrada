package it.contrada.businessdelegate;

import it.contrada.bean.factory.ContradaPojoFactory;
import it.contrada.dominio.dto.TipoTesseraDTO;
import it.contrada.dto.AnagraficaDTO;
import it.contrada.exceptions.ContradaExceptionBloccante;
import it.contrada.exceptions.ContradaExceptionNonBloccante;
import it.contrada.interfaces.IGestioneTessera;
import it.contrada.web.util.Errori;

import java.sql.Date;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class GestioneTesseraBD {
	private static IGestioneTessera gestioneTessera;
	private static Log log = LogFactory.getLog(GestioneTesseraBD.class);

	private GestioneTesseraBD() {
	}

	static {
		/*
		if (gestioneTessera == null) {
			InitialContext ctx;
			try {
				ctx = new InitialContext();
				gestioneTessera = (IGestioneTessera) ctx
						.lookup("ContradaEAR/GestioneTessera/local");
			} catch (NamingException e) {
				// TODO Auto-generated catch block
				log.error(e.getMessage(), e);
				throw new RuntimeException(e);
			}

		}*/
		gestioneTessera=ContradaPojoFactory.getGestioneTesseraIstance();
	}

	public static int allineaQuota(java.util.Date dtRiferimento)
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {

		java.sql.Date dataRif = new Date(dtRiferimento.getTime());
		return gestioneTessera.allineaQuotaTessere(dataRif);

	}

	public static int aggiornaTipoTessera(List<TipoTesseraDTO> tessere,
			boolean aggiornaTessereInCorso) throws ContradaExceptionBloccante,
			ContradaExceptionNonBloccante {

		return gestioneTessera.aggiornaTipoTessera(tessere,
				aggiornaTessereInCorso);

	}

}
