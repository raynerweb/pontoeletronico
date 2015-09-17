package br.com.pontoeletronico.rest;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.pontoeletronico.dominio.Usuario;
import br.com.pontoeletronico.dto.RegistrarPonto;
import br.com.pontoeletronico.service.PontoService;
import br.com.pontoeletronico.utils.SessaoUtils;

@Path("/registrar-ponto")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PontoRest {

	@Autowired
	private PontoService pontoService;

	@POST
	public Response registrarPonto(RegistrarPonto registrarPonto, @Context HttpServletRequest request) {
		Usuario usuarioLogado = SessaoUtils.getUsuarioLogado(request);
		pontoService.registrarPonto(registrarPonto.getIdUsuario(), registrarPonto.getDataRegistro(), usuarioLogado);
		return Response.ok().build();
	}

}
