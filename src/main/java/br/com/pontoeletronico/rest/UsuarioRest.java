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

import org.springframework.beans.factory.annotation.Autowired;

import br.com.pontoeletronico.dominio.Perfil;
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

	@Context
	private HttpServletRequest request;
	
	private Perfil perfil = Perfil.ROOT;

	@Path("/cadastrar")
	@Produces(MediaType.TEXT_PLAIN)
	@POST
	public Usuario cadastrar(Usuario usuario) throws NegocioException {
		service.realizarCadastroUsuario(usuario);
		return usuario;
	}

	/**
	 * @param idUsuario
	 * @return "OK" quando realizado com sucesso e "ERROR" quando ouver algum
	 *         erro
	 */
	@Path("/inativar")
	@Produces(MediaType.TEXT_PLAIN)
	@GET
	public String inativarUsuario(@QueryParam("idUsuario") Long idUsuario) {
		Usuario usuario = SessaoUtils.getUsuarioLogado(request);
		if (usuario.isAutorizado(perfil)) {
			service.inativarUsuario(idUsuario);
		}
		return "OK";
	}

	/**
	 * @param idUsuario
	 * @return "OK" quando realizado com sucesso e "ERROR" quando ouver algum
	 *         erro
	 */
	@Path("/ativar")
	@Produces(MediaType.TEXT_PLAIN)
	@GET
	public String ativarUsuario(@QueryParam("idUsuario") Long idUsuario) {
		Usuario usuario = SessaoUtils.getUsuarioLogado(request);
		if (usuario.isAutorizado(perfil)) {
			service.ativarUsuario(idUsuario);
		}
		return "OK";
	}

	/**
	 * @param idUsuario
	 * @return "OK" quando realizado com sucesso e "ERROR" quando ouver algum
	 *         erro
	 */
	@Path("/limpar-senha")
	@Produces(MediaType.TEXT_PLAIN)
	@GET
	public String limparSenha(@QueryParam("idUsuario") Long idUsuario) {
		Usuario usuario = SessaoUtils.getUsuarioLogado(request);
		if (usuario.isAutorizado(perfil)) {
			service.limparSenha(idUsuario);
		}
		return "OK";
	}
}
