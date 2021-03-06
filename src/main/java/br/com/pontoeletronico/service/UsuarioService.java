package br.com.pontoeletronico.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.pontoeletronico.dominio.Perfil;
import br.com.pontoeletronico.dominio.Status;
import br.com.pontoeletronico.dominio.Usuario;
import br.com.pontoeletronico.dto.UsuarioDTO;
import br.com.pontoeletronico.exception.NegocioException;
import br.com.pontoeletronico.repository.UsuarioRepository;

@Service
public class UsuarioService {

	private final Logger log = Logger.getLogger(UsuarioService.class);
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	public List<UsuarioDTO> recuperarPorStatusEPerfil(String siglaStatus, String siglaPerfil) {
		Status[] status = StringUtils.isBlank(siglaStatus) || Status.getStatusSigla(siglaStatus) == null
				? Status.values() : new Status[] { Status.getStatusSigla(siglaStatus) };
		Perfil[] perfis = StringUtils.isBlank(siglaPerfil) || Perfil.getPerfilSigla(siglaPerfil) == null
				? Perfil.getPerfis() : new Perfil[] { Perfil.getPerfilSigla(siglaPerfil) };
		List<UsuarioDTO> usuariosDto = new ArrayList<UsuarioDTO>();
		List<Usuario> usuarios = usuarioRepository.findByStatusInAndPerfilIn(status, perfis);
		for (Usuario usuario : usuarios) {
			usuariosDto.add(new UsuarioDTO(usuario));
		}
		return usuariosDto;
	}

	public Usuario realizarLogin(Usuario usuario) {
		Usuario usuarioLogado = usuarioRepository.findByMatriculaAndSenha(usuario.getMatricula(), usuario.getSenha());
		if (usuarioLogado == null) {
			throw new NegocioException("Usuário e senha nao confere.");
		}
		usuarioLogado.setSenha(null);
		return usuarioLogado;
	}

	public UsuarioDTO realizarCadastroUsuario(UsuarioDTO usuario) {
		Usuario usuarioRecuperado = usuarioRepository.findByMatricula(usuario.getMatricula());
		if (usuarioRecuperado != null) {
			throw new NegocioException("Usuário já cadastrado.");
		}
		validarCamposObrigatorios(usuario);
		return new UsuarioDTO(armazenarUsuario(usuario));
	}

	public UsuarioDTO atualizarUsuario(UsuarioDTO usuario) {
		if (usuario.getId() == null) {
			throw new NegocioException("Erro ao atualizar usuario");
		}
		return new UsuarioDTO(armazenarUsuario(usuario));
	}

	public Usuario armazenarUsuario(UsuarioDTO usuario) {
		validarCamposObrigatorios(usuario);
		try {
			return usuarioRepository.save(usuario.toUsuario());
		} catch(DataIntegrityViolationException e){
			log.warn(e.getMessage());
			throw new NegocioException("Erro ao armazenar usuario");
		}
	}

	public void limparSenha(Long idUsuario) {
		Usuario user = usuarioRepository.findOne(idUsuario);
		user.setSenha(null);
		usuarioRepository.save(user);
	}

	private void validarCamposObrigatorios(UsuarioDTO usuario) {
		Map<String, String> errors = new HashMap<String, String>();
		if (StringUtils.isBlank(usuario.getMatricula())) {
			errors.put("matricula", "Matrícula não informada");
		}
		if (StringUtils.isBlank(usuario.getNome())) {
			errors.put("nome", "Nome não informado");
		}
		if (StringUtils.isBlank(usuario.getPerfil())) {
			errors.put("perfil", "Perfil não informado");
		}
		if (StringUtils.isBlank(usuario.getStatus())) {
			errors.put("status", "Status não informado");
		}
		if (!errors.isEmpty()) {
			throw new NegocioException(errors);
		}
	}

}
