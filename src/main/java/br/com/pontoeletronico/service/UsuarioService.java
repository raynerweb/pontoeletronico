package br.com.pontoeletronico.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.pontoeletronico.dominio.Status;
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

	public void realizarCadastroUsuario(Usuario usuario) {
		validarCamposObrigatorios(usuario);
		Usuario usuarioLogado = usuarioRepository.findByMatriculaAndSenha(usuario.getMatricula(), usuario.getSenha());
		if (usuarioLogado != null) {
			throw new NegocioException("Usuário já cadastrado.");
		}
		usuario.setStatus(Status.ATIVO);
		armazenarUsuario(usuario);
	}

	public void armazenarUsuario(Usuario usuario) {
		validarCamposObrigatorios(usuario);
		usuarioRepository.save(usuario);
	}

	public void limparSenha(Long idUsuario) {

	}

	public void inativaUsuario(Long idUsuario) {

	}

	public void ativaUsuario(Long idUsuario) {

	}

	private void validarCamposObrigatorios(Usuario usuario) {
		Map<String, String> errors = new HashMap<String, String>();
		if (StringUtils.isBlank(usuario.getMatricula())) {
			errors.put("matricula", "Matrícula não informada");
		}
		if (StringUtils.isBlank(usuario.getNome())) {
			errors.put("nome", "Nome não informado");
		}
		if (usuario.getPerfil() == null) {
			errors.put("perfil", "Perfil não informado");
		}
		if (usuario.getStatus() == null) {
			errors.put("status", "Status não informado");
		}
		if (!errors.isEmpty()) {
			throw new NegocioException(errors);
		}
	}

}
