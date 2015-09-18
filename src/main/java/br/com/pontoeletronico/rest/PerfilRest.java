package br.com.pontoeletronico.rest;

import java.util.Arrays;
import java.util.List;

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
	public List<Perfil> recuperarPerfis(){
		return Arrays.asList(Perfil.values());
	}
	
}
