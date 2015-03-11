package it.contrada.bean.rest;

import it.contrada.businessdelegate.RicercaAnagraficaBD;
import it.contrada.businessdelegate.RicercaTipoRateizzazioneBD;
import it.contrada.dto.AnagraficaDTO;
import it.contrada.dto.FamigliaDTO;
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

@Path("/famiglia")
@Produces(MediaType.APPLICATION_JSON)
public class FamigliaResource {

	@GET
	@Path("/{idFamiglia}")
	public List<AnagraficaDTO> getTesserePerId(
			@PathParam("idFamiglia") int idFamiglia)
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {

		List<AnagraficaDTO> fam = RicercaAnagraficaBD
				.ricercaAnagraficaPerFamiglia(idFamiglia);
		return fam;

	}
}
