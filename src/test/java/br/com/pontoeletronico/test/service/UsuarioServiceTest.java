package br.com.pontoeletronico.test.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.pontoeletronico.dominio.Perfil;
import br.com.pontoeletronico.dominio.Usuario;
import br.com.pontoeletronico.exception.NegocioException;
import br.com.pontoeletronico.service.UsuarioService;
import br.com.pontoeletronico.test.AbstractTest;

public class UsuarioServiceTest extends AbstractTest {

	@Autowired
	private UsuarioService usuarioService;

	private Usuario usuario;

	@Before
	public void setUp() {
		usuario = new Usuario();
		usuario.setMatricula("665544");
		usuario.setNome("Fulano");
		usuario.setPerfil(Perfil.OPERADOR);
		usuario.setSenha("112233");
	}

	@Test
	public void armazenaComSucesso() {
		usuarioService.armazenarUsuario(usuario);
		Assert.assertNotNull(usuario.getId());
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
	public void senhaNaoInformada() {
		usuario.setSenha(null);
		usuarioService.armazenarUsuario(usuario);
		Assert.assertNotNull(usuario.getId());
	}

}
