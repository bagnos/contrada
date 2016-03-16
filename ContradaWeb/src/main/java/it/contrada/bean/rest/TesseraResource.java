package it.contrada.bean.rest;

import it.contrada.businessdelegate.GestioneTesseraBD;
import it.contrada.businessdelegate.RicercaTipoRateizzazioneBD;
import it.contrada.dto.RateizzazioneDTO;
import it.contrada.exceptions.ContradaExceptionBloccante;
import it.contrada.exceptions.ContradaExceptionNonBloccante;
import it.contrada.web.util.PayPalUtil;
import it.othala.payment.paypal.dto.ProfilePayPalDTO;
import it.othala.payment.paypal.dto.RichiestaIncassoDTO;
import it.othala.payment.paypal.dto.SetExpressCheckoutDTO;
import it.othala.payment.paypal.dto.TesseraPaymentDTO;

import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@Path("/tessera")
@Produces(MediaType.APPLICATION_JSON)

public class TesseraResource {
	private static Properties prop = null;
	private static Log log = LogFactory.getLog(TesseraResource.class);

	@GET
	@Path("/{idAnag}/{idTipoTessera}")
	public List<RateizzazioneDTO> getTesserePerId(
			@PathParam("idAnag") int idAnag,
			@PathParam("idTipoTessera") int idTipo)
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {

		Integer year = Calendar.getInstance().get(Calendar.YEAR);
		Integer precYear = (year - 2);
		Integer tipoTessere[] = { idTipo };

		List<RateizzazioneDTO> rate = RicercaTipoRateizzazioneBD
				.ricercaRateizzazionePerAnagrafica(idAnag, tipoTessere,
						precYear, year);
		return rate;

	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/setCheckOut")
	public String inserisciRichiestaIncassoPaylPal(@Context HttpServletRequest servletRequest,RichiestaIncassoDTO richiestaIncasso)
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {
		try {
			
			ProfilePayPalDTO prof = PayPalUtil.getProfile("it",servletRequest);
			SetExpressCheckoutDTO checkout = GestioneTesseraBD
					.inserisciOrderPayment(richiestaIncasso, prof);
			return checkout.getRedirectUrl();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			log.error("errore file properties", e);
			throw new ContradaExceptionBloccante(
					"errore nel recupero delle properties da file");
		}

	}
	@GET
	@Path("/{idAnag}")
	public List<RateizzazioneDTO> getTesserePerId(
			@PathParam("idAnag") int idAnag) throws ContradaExceptionBloccante,
			ContradaExceptionNonBloccante {

		Integer year = Calendar.getInstance().get(Calendar.YEAR);
		Integer precYear = (year - 5);

		List<RateizzazioneDTO> rate = RicercaTipoRateizzazioneBD
				.ricercaRateizzazionePerAnagrafica(idAnag, null, precYear, year);
		return rate;

	}

	
}
