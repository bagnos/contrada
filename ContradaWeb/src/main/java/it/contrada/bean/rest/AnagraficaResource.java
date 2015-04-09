package it.contrada.bean.rest;

import it.contrada.businessdelegate.RicercaAnagraficaBD;
import it.contrada.dto.AnagMaxMinDTO;
import it.contrada.dto.AnagraficaDTO;
import it.contrada.exceptions.ContradaExceptionBloccante;
import it.contrada.exceptions.ContradaExceptionNonBloccante;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/anagrafica")
@Produces(MediaType.APPLICATION_JSON)
public class AnagraficaResource {
	
	@GET
	@Path("/{cognome}/{nome}")
	public List<AnagraficaDTO> getAnagrafica(
			@PathParam("cognome") String cognome, @PathParam("nome") String nome) throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {

		
			List<AnagraficaDTO> anags = RicercaAnagraficaBD
					.ricercaAnagraficaPerCognomeNome(cognome, nome);
			
			return anags;
		
	}
	
	@GET
	@Path("/{id}")
	public AnagraficaDTO getAnagrafica(
			@PathParam("id") int id) throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {

		
			AnagraficaDTO anag = RicercaAnagraficaBD
					.ricercaAnagrafica(id);
			return anag;
		
	}
	
	@GET
	@Path("/maxmin")
	public AnagMaxMinDTO getAnagraficaMaxMin(
			) throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {

		
		AnagMaxMinDTO anag = RicercaAnagraficaBD.getAnagraficheMaxMin();
					
			return anag;
		
	}
}
