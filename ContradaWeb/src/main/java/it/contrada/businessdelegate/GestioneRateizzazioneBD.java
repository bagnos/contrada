package it.contrada.businessdelegate;

import it.contrada.bean.factory.ContradaPojoFactory;
import it.contrada.dto.DistintaDTO;
import it.contrada.dto.RateizzazioneDTO;
import it.contrada.exceptions.ContradaExceptionBloccante;
import it.contrada.exceptions.ContradaExceptionNonBloccante;
import it.contrada.interfaces.IGestioneRateizzazione;
import it.contrada.web.util.Errori;

import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class GestioneRateizzazioneBD {
	private static IGestioneRateizzazione gestioneRateizzazione;
	private static Log log = LogFactory.getLog(GestioneRateizzazioneBD.class);

	private GestioneRateizzazioneBD() {
	}

	static {
		/*
		if (gestioneRateizzazione == null) {
			InitialContext ctx;
			try {
				ctx = new InitialContext();
				gestioneRateizzazione = (IGestioneRateizzazione) ctx
						.lookup("ContradaEAR/GestioneRateizzazione/local");
			} catch (NamingException e) {
				// TODO Auto-generated catch block
				log.error(e.getMessage(), e);
				throw new RuntimeException (e);
			}

		}*/
		gestioneRateizzazione=ContradaPojoFactory.getGestioneRateizzazioneInstance();
	}

	public static DistintaDTO inserisciRateizzazione(
			List<RateizzazioneDTO> rateizzazioni)
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {
		try {
			return gestioneRateizzazione.inserisciRateizzazione(rateizzazioni);
		}

		catch (ContradaExceptionBloccante e) {
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

	public static void eliminaRateizzazione(RateizzazioneDTO rateizzazione)
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {
		try {
			gestioneRateizzazione.eliminaRateizzazione(rateizzazione);
		}

		catch (ContradaExceptionBloccante e) {
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
