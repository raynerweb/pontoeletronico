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
import br.com.pontoeletronico.service.UsuarioService;
import br.com.pontoeletronico.utils.SessaoUtils;

@Path("login")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class LoginRest {

	@Autowired
	private UsuarioService usuarioService;

	@POST
	public String login(Usuario usuario, @Context HttpServletRequest request) {
		usuarioService.realizarLogin(usuario);
		SessaoUtils.addUsuarioSessao(request, usuario);
		return "OK";
	}

}
