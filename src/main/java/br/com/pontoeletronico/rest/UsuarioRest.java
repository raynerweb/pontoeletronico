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

import br.com.pontoeletronico.dominio.Perfil;
import br.com.pontoeletronico.dominio.Usuario;
import br.com.pontoeletronico.dto.UsuarioDTO;
import br.com.pontoeletronico.exception.NegocioException;
import br.com.pontoeletronico.service.UsuarioService;
import br.com.pontoeletronico.utils.SessaoUtils;

@Path("/usuario")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UsuarioRest {

	@Autowired
	private UsuarioService service;

	@Context
	private HttpServletRequest request;

	private Perfil perfil = Perfil.ROOT;

	@Path("/cadastrar")
	@POST
	public Usuario cadastrar(Usuario usuario) throws NegocioException {
		service.realizarCadastroUsuario(usuario);
		return usuario;
	}

	@Path("/atualizar-usuario")
	@POST
	public Response atualizarUsuario(UsuarioDTO usuarioDto) {
		return Response.ok(service.atualizarUsuario(usuarioDto)).build();
	}

	@Path("/limpar-senha")
	@GET
	public Response limparSenha(@QueryParam("idUsuario") Long idUsuario) {
		Usuario usuario = SessaoUtils.getUsuarioLogado(request);
		if (usuario.isAutorizado(perfil)) {
			service.limparSenha(idUsuario);
		}
		return Response.ok().build();
	}

	@Path("/recuperar-usuario")
	@GET
	public Response recuperarUsuarios(@QueryParam("status") String siglaStatus,
			@QueryParam("perfil") String siglaPerfil) {
		return Response.ok(service.recuperarPorStatusEPerfil(siglaStatus, siglaPerfil)).build();
	}

}
