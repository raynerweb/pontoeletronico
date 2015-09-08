package br.com.pontoeletronico.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.pontoeletronico.dominio.Usuario;

@Path("usuario")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UsuarioRest {

	@Path("cadastrar")
	@Produces(MediaType.TEXT_PLAIN)
	public String cadastrar(Usuario usuario){
		return "OK";
	}
}
