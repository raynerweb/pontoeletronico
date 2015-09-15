package br.com.pontoeletronico.test.rest;

import org.junit.Test;
import org.springframework.http.ResponseEntity;

import br.com.pontoeletronico.test.RestTest;

public class UsuarioRestTest extends RestTest {

	@Test
	public void inativarUsuario() {
		params.put("idUsuario", 1l);
		ResponseEntity<String> response = rest.getForEntity("http://localhost:8080/rest/usuario/inativar", String.class, params);
	}

}
