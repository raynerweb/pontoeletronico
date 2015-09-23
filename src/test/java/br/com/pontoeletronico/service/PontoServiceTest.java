package br.com.pontoeletronico.service;

import java.util.Date;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.pontoeletronico.AbstractTest;
import br.com.pontoeletronico.dominio.Ponto;
import br.com.pontoeletronico.dominio.Usuario;
import br.com.pontoeletronico.exception.NegocioException;
import br.com.pontoeletronico.repository.PontoRepository;
import br.com.pontoeletronico.repository.UsuarioRepository;
import br.com.pontoeletronico.service.PontoService;

public class PontoServiceTest extends AbstractTest {

	@Autowired
	private PontoRepository pontoRepository;

	@Autowired
	private PontoService pontoService;

	@Autowired
	private UsuarioRepository usuarioRepository;

	private Usuario usuario;

	private Long idUsuario = 1l;

	@Before
	public void recuperarUsuario() {
		usuario = usuarioRepository.findOne(idUsuario);
	}

	@Test
	public void registrarPontoSucesso() {
		Ponto novoPonto = getPontoService().registrarPonto(idUsuario, new Date(), usuario);
		Assert.assertNotNull(novoPonto);
	}

	@Test(expected = NegocioException.class)
	public void registrarPontoError() {
		getPontoService().registrarPonto(new Long(12345678), new Date(), usuario);
	}

	@Test
	public void recuperarPorDataRegistroIdUsuario() {
		Ponto ponto = getPontoService().recuperarPonto(new Long(1), new Date(), usuario);
		Assert.assertNull(ponto);
	}

	public PontoRepository getPontoRepository() {
		return pontoRepository;
	}

	public PontoService getPontoService() {
		return pontoService;
	}

}
