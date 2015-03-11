package it.contrada.bean.rest;

import it.contrada.businessdelegate.RicercaRidBD;
import it.contrada.businessdelegate.RicercaTipoRateizzazioneBD;
import it.contrada.dto.MembroRidDTO;
import it.contrada.dto.RateizzazioneDTO;
import it.contrada.dto.RidDTO;
import it.contrada.exceptions.ContradaExceptionBloccante;
import it.contrada.exceptions.ContradaExceptionNonBloccante;

import java.util.Calendar;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/rid")
@Produces(MediaType.APPLICATION_JSON)
public class RidResource {

	@GET	
	@Path("/{id}")
	public List<MembroRidDTO> getRid(
			@PathParam("id") int idRid)
			
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {

		
		List<MembroRidDTO> membri=RicercaRidBD.ricercaPerRid(idRid);
		return membri;

	}
}
