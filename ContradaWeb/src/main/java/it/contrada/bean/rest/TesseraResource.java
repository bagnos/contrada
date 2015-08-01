package it.contrada.bean.rest;

import it.contrada.businessdelegate.RicercaTipoRateizzazioneBD;
import it.contrada.dto.RateizzazioneDTO;
import it.contrada.exceptions.ContradaExceptionBloccante;
import it.contrada.exceptions.ContradaExceptionNonBloccante;

import java.util.Calendar;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/tessera")
@Produces(MediaType.APPLICATION_JSON)
public class TesseraResource {

	@GET	
	@Path("/{idAnag}/{idTipoTessera}")
	public List<RateizzazioneDTO> getTesserePerId(
			@PathParam("idAnag") int idAnag,
			@PathParam("idTipoTessera") int idTipo)
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {

		
		
		Integer year=Calendar.getInstance().get(Calendar.YEAR);
		Integer precYear=(year-2);
		Integer tipoTessere[]={idTipo};
		
		List<RateizzazioneDTO> rate= RicercaTipoRateizzazioneBD.ricercaRateizzazionePerAnagrafica(
				idAnag, tipoTessere,
				precYear,year);
		return rate;

	}
	
	@GET	
	@Path("/{idAnag}")
	public List<RateizzazioneDTO> getTesserePerId(
			@PathParam("idAnag") int idAnag
			)
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {

		
		List<RateizzazioneDTO> rate= RicercaTipoRateizzazioneBD.ricercaRateizzazionePerAnagrafica(idAnag);
		return rate;

	}
}
