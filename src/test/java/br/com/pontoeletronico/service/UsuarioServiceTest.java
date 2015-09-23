package br.com.pontoeletronico.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.pontoeletronico.AbstractTest;
import br.com.pontoeletronico.dominio.Perfil;
import br.com.pontoeletronico.dominio.Status;
import br.com.pontoeletronico.dominio.Usuario;
import br.com.pontoeletronico.exception.NegocioException;
import br.com.pontoeletronico.repository.UsuarioRepository;

public class UsuarioServiceTest extends AbstractTest {

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private UsuarioRepository repositorio;

	private Usuario usuario;

	@Before
	public void setUp() {
		usuario = new Usuario();
		usuario.setMatricula("665544");
		usuario.setNome("Fulano");
		usuario.setPerfil(Perfil.OPERADOR);
		usuario.setSenha("112233");
		usuario.setStatus(Status.INATIVO);
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
		usuario.setStatus(null);
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
		usuario.setPerfil(null);
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
		usuario = repositorio.findOne(usuario.getId());
		usuarioService.limparSenha(usuario.getId());
		usuario = repositorio.findOne(usuario.getId());
		Assert.assertTrue(StringUtils.isBlank(usuario.getSenha()));
	}

	@Test
	public void inativarUsuario() {
		usuario.setStatus(Status.ATIVO);
		usuarioService.armazenarUsuario(usuario);
		Assert.assertNotNull(usuario.getId());
		usuario = repositorio.findOne(usuario.getId());
		usuarioService.inativarUsuario(usuario.getId());
		usuario = repositorio.findOne(usuario.getId());
		Assert.assertEquals(Status.INATIVO, usuario.getStatus());
	}

	@Test
	public void ativarUsuario() {
		usuarioService.armazenarUsuario(usuario);
		Assert.assertNotNull(usuario.getId());
		usuario = repositorio.findOne(usuario.getId());
		usuarioService.inativarUsuario(usuario.getId());
		usuario = repositorio.findOne(usuario.getId());
		Assert.assertEquals(Status.INATIVO, usuario.getStatus());
		usuarioService.ativarUsuario(usuario.getId());
		usuario = repositorio.findOne(usuario.getId());
		Assert.assertEquals(Status.ATIVO, usuario.getStatus());
	}

	@Test
	public void recuperarPeloUsuario() {
		List<Usuario> usuarios = usuarioService.recuperarPorStatusEPerfil(Status.ATIVO.getSigla(), Perfil.OPERADOR.getSigla());
		Assert.assertNotNull(usuarios);
		Assert.assertFalse(usuarios.isEmpty());
	}

}
