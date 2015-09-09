package br.com.pontoeletronico.test.rest;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.com.pontoeletronico.dominio.Usuario;
import br.com.pontoeletronico.test.RestTest;

public class UsuarioRestTest extends RestTest {

	@Test
	public void realizarLoginSucesso() {
		Usuario u = new Usuario();
		u.setMatricula("root");
		u.setSenha("123456");

		ResponseEntity<String> resposta = rest.postForEntity("http://localhost:8080/rest/usuario/cadastrar", u,
				String.class);
		Assert.assertEquals(HttpStatus.OK, resposta.getStatusCode());
		Assert.assertEquals("OK", resposta.getBody());
	}

	@Test(expected = Exception.class)
	public void matriculaSenhaNaoConfere() {
		Usuario u = new Usuario();
		u.setMatricula("toor");
		u.setSenha("654321");
		rest.postForEntity("http://localhost:8080/rest/usuario/cadastrar", u, String.class);
	}

}
