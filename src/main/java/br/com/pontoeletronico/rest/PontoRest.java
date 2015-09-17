package br.com.pontoeletronico.rest;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.pontoeletronico.dominio.Ponto;
import br.com.pontoeletronico.dominio.Usuario;
import br.com.pontoeletronico.dto.RegistrarPonto;
import br.com.pontoeletronico.service.PontoService;
import br.com.pontoeletronico.utils.DateUtils;
import br.com.pontoeletronico.utils.SessaoUtils;

@Path("/ponto")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PontoRest {

	@Autowired
	private PontoService pontoService;

	@Context
	private HttpServletRequest request;

	@POST
	@Path("/registrar")
	public Response registrarPonto(RegistrarPonto registrarPonto) {
		Usuario usuarioLogado = SessaoUtils.getUsuarioLogado(request);
		pontoService.registrarPonto(registrarPonto.getIdUsuario(), registrarPonto.getDataRegistro(), usuarioLogado);
		return Response.ok().build();
	}

	@GET
	@Path("/recuperar")
	public Response recuperarPonto(@QueryParam("idUsuario") Long idUsuario,
			@QueryParam("dataRegistro") Long dataRegistro) {
		Ponto ponto = pontoService.recuperarPonto(idUsuario, DateUtils.toDate(dataRegistro),
				SessaoUtils.getUsuarioLogado(request));
		return Response.ok(ponto).build();
	}
	
	@GET
	public Response recuperarTodos(@QueryParam("idUsuario") Long idUsuario){
		return Response.ok().build();
	}

}
