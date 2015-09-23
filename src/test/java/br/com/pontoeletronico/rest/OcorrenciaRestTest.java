package br.com.pontoeletronico.rest;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.pontoeletronico.RestTest;
import br.com.pontoeletronico.dominio.Ocorrencia;
import br.com.pontoeletronico.dominio.StatusOcorrencia;

public class OcorrenciaRestTest extends RestTest {

	@Test
	public void consultarStatusOcorrenciaSucesso() {
		UriComponentsBuilder builder = UriComponentsBuilder
				.fromHttpUrl("http://localhost:8080/rest/ocorrencia/consultarStatusOcorrencia")
				.queryParam("status", "AB");
		ResponseEntity<String> resposta = rest.getForEntity(builder.build().encode().toUri(), String.class);
		Assert.assertNotNull(resposta);
		Assert.assertEquals(HttpStatus.OK, resposta.getStatusCode());

	}

	@Test
	public void consultarStatusOcorrenciaErro() {
		UriComponentsBuilder builder = UriComponentsBuilder
				.fromHttpUrl("http://localhost:8080/rest/ocorrencia/consultarStatusOcorrencia")
				.queryParam("status", "INEXISTENTE");
		ResponseEntity<String> resposta = rest.getForEntity(builder.build().encode().toUri(), String.class);
		Assert.assertNotNull(resposta);
		Assert.assertEquals(HttpStatus.UNAUTHORIZED, resposta.getStatusCode());
	}

	@Test
	public void consultarStatusOcorrenciaUsuarioId() {
		UriComponentsBuilder builder = UriComponentsBuilder
				.fromHttpUrl("http://localhost:8080/rest/ocorrencia/consultarStatusOcorrenciaUsuarioId")
				.queryParam("status", "AC").queryParam("usuarioId", 4444L);
		ResponseEntity<String> resposta = rest.getForEntity(builder.build().encode().toUri(), String.class);
		Assert.assertNotNull(resposta);
	}

	@Test
	public void consultarUsuarioId() {
		UriComponentsBuilder builder = UriComponentsBuilder
				.fromHttpUrl("http://localhost:8080/rest/ocorrencia/consultarUsuarioId").queryParam("usuarioId", 4444L);
		ResponseEntity<String> resposta = rest.getForEntity(builder.build().encode().toUri(), String.class);
		Assert.assertNotNull(resposta);
	}

	@Test
	@Ignore
	public void registrarOcorrencia() {
		Ocorrencia ocorrencia = new Ocorrencia();
		ocorrencia.setId(4444L);
		ocorrencia.setJustificativa("Motivos pessoais familiares");
		ocorrencia.setStatusOcorrencia(StatusOcorrencia.ACEITO);

		ResponseEntity<String> resposta = rest
				.postForEntity("http://localhost:8080/rest/ocorrencia/registrarOcorrencia", ocorrencia, String.class);
		Assert.assertEquals(HttpStatus.OK, resposta.getStatusCode());
	}

}
