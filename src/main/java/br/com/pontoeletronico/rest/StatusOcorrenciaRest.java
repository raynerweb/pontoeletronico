package br.com.pontoeletronico.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.pontoeletronico.dominio.StatusOcorrencia;

@Path("/status-ocorrencia")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class StatusOcorrenciaRest {

	@GET
	public Response statusOcorrencia() {
		return Response.ok(StatusOcorrencia.values()).build();
	}
}
