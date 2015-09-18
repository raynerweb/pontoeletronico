package br.com.pontoeletronico.test.rest;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.com.pontoeletronico.dominio.Ocorrencia;
import br.com.pontoeletronico.dominio.StatusOcorrencia;
import br.com.pontoeletronico.exception.NegocioException;
import br.com.pontoeletronico.test.RestTest;

public class OcorrenciaRestTest extends RestTest {

	@Test
	public void consultarStatusOcorrenciaSucesso() {
		params.put("status", "AB");
		ResponseEntity<String> resposta = rest.getForEntity(
				"http://localhost:8080/rest/ocorrencia/consultarStatusOcorrencia", 
						String.class, params);
		Assert.assertNotNull(resposta);
		
	}
	
	@Test(expected=NegocioException.class)
	public void consultarStatusOcorrenciaErro() {
		params.put("status", "AB");
		ResponseEntity<String> resposta = rest.getForEntity(
				"http://localhost:8080/rest/ocorrencia/consultarStatusOcorrencia", 
						String.class, params);
	}
	
	@Test
	public void consultarStatusOcorrenciaUsuarioId() {
		params.put("status", "AC");
		params.put("usuarioId", 4444L);
		ResponseEntity<String> resposta = rest.getForEntity(
				"http://localhost:8080/rest/ocorrencia/consultarStatusOcorrenciaUsuarioId", 
						String.class, params);
		Assert.assertNotNull(resposta);
	}
	
	@Test
	public void consultarUsuarioId(){
		params.put("usuarioId", 4444L);
		ResponseEntity<String> resposta = rest.getForEntity(
				"http://localhost:8080/rest/ocorrencia/consultarUsuarioId", 
						String.class, params);
		Assert.assertNotNull(resposta);
	}
	
	@Test
	public void registrarOcorrencia(){
		Ocorrencia ocorrencia = new Ocorrencia();
		ocorrencia.setId(4444L);
		ocorrencia.setJustificativa("Motivos pessoais familiares");
		ocorrencia.setStatusOcorrencia(StatusOcorrencia.ACEITO);
		
		ResponseEntity<String> resposta = rest.postForEntity(
				"http://localhost:8080/rest/ocorrencia/consultarUsuarioId", 
						ocorrencia, String.class);
		Assert.assertEquals(HttpStatus.OK, resposta.getStatusCode());
	}

}
