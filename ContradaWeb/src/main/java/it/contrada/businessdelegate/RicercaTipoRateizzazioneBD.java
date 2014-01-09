package it.contrada.businessdelegate;

import it.contrada.bean.factory.ContradaPojoFactory;
import it.contrada.dominio.dto.TipoRateizzazioneDTO;
import it.contrada.dto.IncassoAnnualeDTO;
import it.contrada.dto.ParmIncassoDTO;
import it.contrada.dto.RateizzazioneDTO;
import it.contrada.exceptions.ContradaExceptionBloccante;
import it.contrada.exceptions.ContradaExceptionNonBloccante;
import it.contrada.interfaces.IRicercaRateizzazione;
import it.contrada.web.util.Errori;

import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class RicercaTipoRateizzazioneBD {
	private static IRicercaRateizzazione ricercaRateizzazione;
	private static Log log = LogFactory
			.getLog(RicercaTipoRateizzazioneBD.class);

	static {
		/*
		 * InitialContext ctx; try { ctx = new InitialContext();
		 * ricercaRateizzazione = (IRicercaRateizzazione) ctx
		 * .lookup("ContradaEAR/RicercaRateizzazione/local");
		 * 
		 * } catch (NamingException e) { // TODO Auto-generated catch block
		 * log.error(e.getMessage(), e); throw new RuntimeException(e); }
		 */
		ricercaRateizzazione = ContradaPojoFactory
				.getRicercaRateizzazioneIstance();
	}

	public static List<TipoRateizzazioneDTO> elencaTipoRateizzazione()
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {

		return ricercaRateizzazione.elencaTipoRateizzazione();

	}

	public List<TipoRateizzazioneDTO> ricercaPerTessera(int idTessera)
			throws ContradaExceptionBloccante {
		try {

			return ricercaRateizzazione.ricercaPerTessera(idTessera);

		} catch (ContradaExceptionBloccante e) {
			log.error(e.getMessage(), e);
			throw e;
		}

		catch (Exception e1) {
			// TODO Auto-generated catch block
			log.error(e1.getMessage(), e1);
			throw new ContradaExceptionBloccante(Errori.TEMP_PROB_COLL);
		}

	}

	public List<TipoRateizzazioneDTO> ricercaPerTesseraIncasso(int idTessera,
			int idIncasso) throws ContradaExceptionBloccante {
		try {

			List<TipoRateizzazioneDTO> rateizzazioni = null;
			rateizzazioni = ricercaRateizzazione.ricercaPerTesseraIncasso(
					idTessera, idIncasso);
			return rateizzazioni;
		} catch (ContradaExceptionBloccante e) {
			log.error(e.getMessage(), e);
			throw e;
		}

		catch (Exception e1) {
			// TODO Auto-generated catch block
			log.error(e1.getMessage(), e1);
			throw new ContradaExceptionBloccante(Errori.TEMP_PROB_COLL);
		}

	}

	public static List<RateizzazioneDTO> ricercaRateizzazionePerAnagrafica(
			int idAnagrafica) throws ContradaExceptionBloccante,
			ContradaExceptionNonBloccante {
		List<RateizzazioneDTO> rateizzazioni = null;

		rateizzazioni = ricercaRateizzazione.ricercaPerAnagrafica(idAnagrafica);
		return rateizzazioni;

	}

	public static List<RateizzazioneDTO> ricercaRateizzazionePerAnagrafica(
			int idAnagrafica, int idTipoTessera)
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {
		List<RateizzazioneDTO> rateizzazioni = null;

		rateizzazioni = ricercaRateizzazione.ricercaPerAnagrafica(idAnagrafica,
				idTipoTessera);
		return rateizzazioni;

	}

	public static List<RateizzazioneDTO> ricercaRateizzazionePerAnagrafica(
			int idAnagrafica, Integer[] idTipoTessera, Integer annoDa,
			Integer annoA) throws ContradaExceptionBloccante,
			ContradaExceptionNonBloccante {
		List<RateizzazioneDTO> rateizzazioni = null;

		rateizzazioni = ricercaRateizzazione.ricercaPerAnagrafica(idAnagrafica,
				idTipoTessera, annoDa, annoA);
		return rateizzazioni;

	}

	public static List<RateizzazioneDTO> ricercaRate(ParmIncassoDTO parm)
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {
		List<RateizzazioneDTO> rateizzazioni = null;

		rateizzazioni = ricercaRateizzazione.ricercaRate(parm);
		return rateizzazioni;

	}

	public static List<RateizzazioneDTO> ricercaRateByAnagrafica(
			ParmIncassoDTO parmIncasso) throws ContradaExceptionBloccante,
			ContradaExceptionNonBloccante {
		List<RateizzazioneDTO> rateizzazioni = null;

		rateizzazioni = ricercaRateizzazione
				.ricercaRateByAnagrafica(parmIncasso);
		return rateizzazioni;
	}

	public static List<IncassoAnnualeDTO> recuperaIncassoAnnuale(int anno,
			List<Integer> idTipoTessera) throws ContradaExceptionBloccante,
			ContradaExceptionNonBloccante {
		List<IncassoAnnualeDTO> incassi = ricercaRateizzazione
				.recuperaIncassoAnnuale(anno, idTipoTessera);
		return incassi;
	}

}
