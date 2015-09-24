package br.com.pontoeletronico.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.pontoeletronico.dominio.Perfil;
import br.com.pontoeletronico.dominio.Status;
import br.com.pontoeletronico.dominio.Usuario;
import br.com.pontoeletronico.exception.NegocioException;
import br.com.pontoeletronico.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	public List<Usuario> recuperarPorStatusEPerfil(String siglaStatus, String siglaPerfil) {
		Status[] status = StringUtils.isBlank(siglaStatus) || Status.getStatus(siglaStatus) == null ? Status.values()
				: new Status[] { Status.getStatus(siglaStatus) };
		Perfil[] perfis = StringUtils.isBlank(siglaPerfil) || Perfil.getPerfil(siglaPerfil) == null ? Perfil.getPerfis()
				: new Perfil[] { Perfil.getPerfil(siglaPerfil) };
		return usuarioRepository.findByStatusInAndPerfilIn(status, perfis);
	}

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
	
	public void atualizarUsuario(Usuario usuario){
		if(usuario.getId() == null){
			throw new NegocioException("Erro ao atualizar usuario");
		}
		armazenarUsuario(usuario);
	}

	public void armazenarUsuario(Usuario usuario) {
		validarCamposObrigatorios(usuario);
		usuarioRepository.save(usuario);
	}

	public void limparSenha(Long idUsuario) {
		Usuario user = usuarioRepository.findOne(idUsuario);
		user.setSenha(null);
		usuarioRepository.save(user);
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
