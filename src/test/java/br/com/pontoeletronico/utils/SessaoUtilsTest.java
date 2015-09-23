package br.com.pontoeletronico.utils;

import javax.servlet.http.HttpServletRequest;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;

import br.com.pontoeletronico.dominio.Usuario;
import br.com.pontoeletronico.utils.SessaoUtils;

public class SessaoUtilsTest {

	private HttpServletRequest request;

	@Before
	public void setUp() {
		request = new MockHttpServletRequest();
	}

	@Test
	public void addUsuarioSessao() {
		Usuario usuario = new Usuario();
		usuario.setId(1l);
		SessaoUtils.addUsuarioSessao(request, usuario);
		Assert.assertNotNull(request.getSession().getAttribute("usuarioSessao"));
		Long idUsuarioSessao = Usuario.class.cast(request.getSession().getAttribute("usuarioSessao")).getId();
		Assert.assertEquals(idUsuarioSessao, usuario.getId());
	}

	@Test
	public void removeUsuarioSessao() {
		Usuario usuario = new Usuario();
		usuario.setId(1l);
		SessaoUtils.addUsuarioSessao(request, usuario);
		SessaoUtils.removeUsuarioSessao(request);
		Assert.assertNull(request.getSession().getAttribute("usuarioSessao"));
	}

	@Test
	public void isUsuarioNaSessao() {
		Usuario usuario = new Usuario();
		usuario.setId(1l);
		SessaoUtils.addUsuarioSessao(request, usuario);
		Assert.assertNotNull(request.getSession().getAttribute("usuarioSessao"));
		Assert.assertTrue(SessaoUtils.isUsuarioNaSessao(request, usuario));
	}
	
	@Test
	public void getUsuarioLogado(){
		Usuario usuario = new Usuario();
		usuario.setId(1l);
		SessaoUtils.addUsuarioSessao(request, usuario);
		Assert.assertEquals(usuario, SessaoUtils.getUsuarioLogado(request));
	}
}
