package br.com.pontoeletronico.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.pontoeletronico.dominio.Usuario;
import br.com.pontoeletronico.exception.NegocioException;
import br.com.pontoeletronico.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	public Usuario realizarLogin(Usuario usuario) {
		Usuario usuarioLogado = usuarioRepository.findByMatriculaAndSenha(usuario.getMatricula(), usuario.getSenha());
		if (usuarioLogado == null) {
			throw new NegocioException("Usuário e senha nao confere.");
		}
		usuarioLogado.setSenha(null);
		return usuarioLogado;
	}

	public Usuario armazenarUsuario(Usuario usuario) {
		Map<String, String> errors = new HashMap<String, String>();
		if (StringUtils.isBlank(usuario.getMatricula())) {
			errors.put("matricula", "Matrícula não informada");
		}
		if (StringUtils.isBlank(usuario.getNome())) {
			errors.put("nome", "Nome não informado");
		}
		if (StringUtils.isBlank(usuario.getSenha())) {
			errors.put("senha", "Senha não informada");
		}
		if (usuario.getPerfil() == null) {
			errors.put("perfil", "Perfil não informado");
		}
		if (!errors.isEmpty()) {
			throw new NegocioException(errors);
		}
		usuarioRepository.save(usuario);
		return usuario;
	}

}
