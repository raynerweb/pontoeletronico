package br.com.pontoeletronico.rest;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.com.pontoeletronico.RestTest;
import br.com.pontoeletronico.dominio.Usuario;
import br.com.pontoeletronico.repository.UsuarioRepository;
import br.com.pontoeletronico.utils.JsonUtils;

public class LoginRestTest extends RestTest {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Test
	public void realizarLoginSucesso() {
		Usuario u = new Usuario();
		u.setMatricula("root");
		u.setSenha("123456");
		ResponseEntity<String> resposta = rest.postForEntity("http://localhost:8080/rest/login", u, String.class);
		Assert.assertEquals(HttpStatus.OK, resposta.getStatusCode());
		Usuario usuarioRecuperado = JsonUtils.toObject(Usuario.class, resposta.getBody());
		Assert.assertEquals(usuarioRepository.findOne(1l).getId(), usuarioRecuperado.getId());
	}

	@Test(expected = Exception.class)
	public void matriculaSenhaNaoConfere() {
		Usuario u = new Usuario();
		u.setMatricula("toor");
		u.setSenha("654321");
		rest.postForEntity("http://localhost:8080/rest/login", u, String.class);
	}

}
