package br.com.pontoeletronico.rest;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.pontoeletronico.dominio.Usuario;
import br.com.pontoeletronico.exception.NegocioException;
import br.com.pontoeletronico.service.UsuarioService;
import br.com.pontoeletronico.utils.SessaoUtils;

@Path("/usuario")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UsuarioRest {

	@Autowired
	private UsuarioService service;

	@Path("/cadastrar")
	@Produces(MediaType.TEXT_PLAIN)
	@POST
	public String cadastrar(Usuario usuario, @Context HttpServletRequest request) throws NegocioException {
		usuario = service.realizarLogin(usuario);
		SessaoUtils.addUsuarioSessao(request, usuario);
		return "OK";
	}
}
