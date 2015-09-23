package br.com.pontoeletronico.service;

import java.util.Date;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.pontoeletronico.AbstractTest;
import br.com.pontoeletronico.dominio.Ocorrencia;
import br.com.pontoeletronico.dominio.Ponto;
import br.com.pontoeletronico.dominio.StatusOcorrencia;
import br.com.pontoeletronico.dominio.Usuario;
import br.com.pontoeletronico.repository.UsuarioRepository;

public class OcorrenciaServiceTest extends AbstractTest {

	@Autowired
	private OcorrenciaService ocorrenciaService;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private PontoService pontoService;

	private Ponto pontoRegistrado;

	private Ocorrencia ocorrencia;

	private Usuario usuario;

	private Long idUsuario = 1l;

	@Before
	public void recuperarUsuario() {
		usuario = usuarioRepository.findOne(idUsuario);
		pontoRegistrado = pontoService.registrarPonto(idUsuario, new Date(), usuario);
		ocorrencia = new Ocorrencia();
		ocorrencia.setPonto(pontoRegistrado);
		ocorrencia.setJustificativa("JUSTIFICATIVA");
		ocorrencia.setStatusOcorrencia(StatusOcorrencia.ABERTO);
		ocorrencia.setUsuario(usuario);
	}

	@Test
	public void registrarOcorrencia() {
		ocorrenciaService.registrarOcorrencia(ocorrencia);
		Assert.assertNotNull(ocorrencia.getId());
	}
}
