package br.com.pontoeletronico.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.pontoeletronico.dominio.Perfil;

@Path("/perfil")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PerfilRest {

	@GET
	public Perfil[] recuperarPerfis(){
		return Perfil.getPerfis();
	}
	
}
