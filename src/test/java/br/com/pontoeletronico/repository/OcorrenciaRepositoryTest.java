package br.com.pontoeletronico.repository;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.pontoeletronico.AbstractTest;
import br.com.pontoeletronico.dominio.Ocorrencia;
import br.com.pontoeletronico.dominio.Ponto;
import br.com.pontoeletronico.dominio.StatusOcorrencia;
import br.com.pontoeletronico.dominio.Usuario;
import br.com.pontoeletronico.service.PontoService;
import br.com.pontoeletronico.utils.DateUtils;

public class OcorrenciaRepositoryTest extends AbstractTest {

	private final Logger log = Logger.getLogger(OcorrenciaRepositoryTest.class);

	private Usuario usuario;
	private Ponto pontoRegistrado;
	private Ocorrencia ocorrencia;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private PontoService pontoService;

	@Autowired
	private OcorrenciaRepository ocorrenciaRepository;

	@Before
	public void registraOcorrencia() {
		Long idUsuario = 1l;

		usuario = usuarioRepository.findOne(idUsuario);
		Assert.assertNotNull(usuario);

		pontoRegistrado = pontoService.registrarPonto(idUsuario, new Date(), usuario);
		Assert.assertNotNull(pontoRegistrado);

		ocorrencia = new Ocorrencia();
		ocorrencia.setPonto(pontoRegistrado);
		ocorrencia.setJustificativa("JUSTIFICATIVA");
		ocorrencia.setStatusOcorrencia(StatusOcorrencia.ABERTO);
		ocorrencia.setUsuario(usuario);
		ocorrenciaRepository.save(ocorrencia);
		Assert.assertNotNull(ocorrencia.getId());
	}

	@Test
	public void recuperaPorIdUsuarioIntervaloDataRegistroStatusOcorrencia() {
		LocalDate hoje = DateUtils.toLocalDate(ocorrencia.getPonto().getDataRegistro());
		Date ontem = DateUtils.toDate(hoje.minusDays(1l));
		Date amanha = DateUtils.toDate(hoje.plusDays(1l));
		List<Ocorrencia> ocorrencias = ocorrenciaRepository
				.findByUsuarioIdAndPontoDataRegistroBetweenAndStatusOcorrenciaInOrderByPontoDataRegistroDesc(
						usuario.getId(), ontem, amanha, Arrays.asList(StatusOcorrencia.values()));
		Assert.assertNotNull(ocorrencias);
		Assert.assertFalse(ocorrencias.isEmpty());
		for (Ocorrencia ocorrencia : ocorrencias) {
			log.debug(ocorrencia.getJustificativa());
			log.debug(ocorrencia.getStatusOcorrencia().name());
		}
	}
	
}
