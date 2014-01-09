package it.contrada.bean.rest;

import it.contrada.dto.AnagraficaDTO;

import java.util.GregorianCalendar;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/helloworld")
public class HelloWorldResource {

	@Produces("text/plain")
	@GET
	public String hello()
	{
		return "hello world!";
	}
	
	@Produces(MediaType.APPLICATION_JSON)
	@GET
	@Path("/json")
	public AnagraficaDTO getAnagraficaDTO()
	{
		AnagraficaDTO a=new AnagraficaDTO();
		a.setCognome("Bagnolesi");
		a.setNome("Simone");
		a.setEta(27);
		
		return a;
	}
	
}

