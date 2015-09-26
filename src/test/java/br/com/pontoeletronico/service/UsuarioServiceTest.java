package br.com.pontoeletronico.service;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.pontoeletronico.AbstractTest;
import br.com.pontoeletronico.dominio.Perfil;
import br.com.pontoeletronico.dominio.Status;
import br.com.pontoeletronico.dto.UsuarioDTO;
import br.com.pontoeletronico.exception.NegocioException;
import br.com.pontoeletronico.repository.UsuarioRepository;

@Ignore
public class UsuarioServiceTest extends AbstractTest {

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private UsuarioRepository repositorio;

	private UsuarioDTO usuario;

	@Before
	public void setUp() {
		usuario = new UsuarioDTO();
		usuario.setMatricula("665544");
		usuario.setNome("Fulano");
		usuario.setPerfil(Perfil.OPERADOR.getDescricao());
		usuario.setStatus(Status.INATIVO.getDescricao());
	}

	@Test
	public void armazenaComSucesso() {
		usuarioService.armazenarUsuario(usuario);
		Assert.assertNotNull(usuario.getId());
	}

	@Test
	public void realizarCadastroUsuarioComSucesso() {
		usuarioService.realizarCadastroUsuario(usuario);
		Assert.assertNotNull(usuario.getId());
		Assert.assertEquals(Status.ATIVO, usuario.getStatus());
	}

	@Test(expected = NegocioException.class)
	public void statusNaoInformado() {
		usuario.setStatus("");
		usuarioService.armazenarUsuario(usuario);
	}

	@Test(expected = NegocioException.class)
	public void matriculaNaoInformada() {
		usuario.setMatricula(null);
		usuarioService.armazenarUsuario(usuario);
		Assert.assertNotNull(usuario.getId());
	}

	@Test(expected = NegocioException.class)
	public void nomeNaoInformado() {
		usuario.setNome(null);
		usuarioService.armazenarUsuario(usuario);
		Assert.assertNotNull(usuario.getId());
	}

	@Test(expected = NegocioException.class)
	public void perfilNaoInformado() {
		usuario.setPerfil("");
		usuarioService.armazenarUsuario(usuario);
		Assert.assertNotNull(usuario.getId());
	}

	@Test(expected = NegocioException.class)
	public void naoPermitidoCadastroDuplicado() {
		usuarioService.realizarCadastroUsuario(usuario);
		Assert.assertNotNull(usuario.getId());
		usuarioService.realizarCadastroUsuario(usuario);
	}

	@Test
	public void limparSenhaUsuario() {
		usuarioService.armazenarUsuario(usuario);
		Assert.assertNotNull(usuario.getId());
		usuario = new UsuarioDTO(repositorio.findOne(usuario.getId()));
		usuarioService.limparSenha(usuario.getId());
		usuario = new UsuarioDTO(repositorio.findOne(usuario.getId()));
		Assert.assertTrue(usuario.isPossuiSenha());
	}

	@Test
	public void inativarUsuario() {
		usuario.setStatus(Status.ATIVO.getDescricao());
		usuarioService.armazenarUsuario(usuario);
		Assert.assertNotNull(usuario.getId());

		usuario.setStatus(Status.INATIVO.getDescricao());
		usuarioService.atualizarUsuario(usuario);

		usuario = new UsuarioDTO(repositorio.findOne(usuario.getId()));
		Assert.assertEquals(Status.INATIVO, usuario.getStatus());
	}

	@Test
	public void ativarUsuario() {
		usuarioService.armazenarUsuario(usuario);
		Assert.assertNotNull(usuario.getId());
		usuario.setStatus(Status.ATIVO.getDescricao());
		usuarioService.atualizarUsuario(usuario);

		usuario = new UsuarioDTO(repositorio.findOne(usuario.getId()));
		Assert.assertEquals(Status.ATIVO, usuario.getStatus());
	}

	@Test
	public void recuperarPeloUsuario() {
		List<UsuarioDTO> usuarios = usuarioService.recuperarPorStatusEPerfil(Status.ATIVO.getSigla(),
				Perfil.OPERADOR.getSigla());
		Assert.assertNotNull(usuarios);
		Assert.assertFalse(usuarios.isEmpty());
	}

}
