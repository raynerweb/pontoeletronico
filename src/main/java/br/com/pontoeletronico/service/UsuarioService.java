package br.com.pontoeletronico.service;

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

}
