package it.contrada.bean.rest;

import it.contrada.exceptions.ContradaException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ContradaExceptionMapper implements
		ExceptionMapper<ContradaException> {

	public Response toResponse(ContradaException e) {
		// TODO Auto-generated method stub
		
		return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
				.entity(e.getMessage()).type("text/plain").build();

	}
}