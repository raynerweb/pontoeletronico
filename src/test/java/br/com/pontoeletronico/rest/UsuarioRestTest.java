package br.com.pontoeletronico.rest;

import org.junit.Test;
import org.springframework.http.ResponseEntity;

import br.com.pontoeletronico.RestTest;

public class UsuarioRestTest extends RestTest {

	@Test
	public void inativarUsuario() {
		params.put("idUsuario", 1l);
		ResponseEntity<String> response = rest.getForEntity("http://localhost:8080/rest/usuario/inativar", String.class, params);
	}

}
