package br.com.pontoeletronico.rest;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.pontoeletronico.dominio.Usuario;
import br.com.pontoeletronico.service.UsuarioService;
import br.com.pontoeletronico.utils.SessaoUtils;

@Path("login")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class LoginRest {

	@Autowired
	private UsuarioService usuarioService;

	@Context
	private HttpServletRequest request;

	@POST
	public Response login(Usuario usuario) {
		Usuario usuarioLogado = usuarioService.realizarLogin(usuario);
		SessaoUtils.addUsuarioSessao(request, usuarioLogado);
		return Response.ok(usuarioLogado).build();
	}

	@GET
	@Path("logout")
	public Response logout() {
		SessaoUtils.removeUsuarioSessao(request);
		return Response.ok().build();
	}

}