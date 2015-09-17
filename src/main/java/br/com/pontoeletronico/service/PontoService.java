package br.com.pontoeletronico.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.pontoeletronico.dominio.Ponto;
import br.com.pontoeletronico.dominio.Usuario;
import br.com.pontoeletronico.exception.NegocioException;
import br.com.pontoeletronico.repository.PontoRepository;
import br.com.pontoeletronico.repository.UsuarioRepository;
import br.com.pontoeletronico.utils.DateUtils;

@Service
public class PontoService {

	@Autowired
	private PontoRepository pontoRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;

	public Ponto registrarPonto(Long idUsuario, Date dataRegistro, Usuario usuarioLogado) throws NegocioException {
		Map<String, String> errors = validarRegistroPonto(idUsuario, dataRegistro, usuarioLogado);
		if (!errors.isEmpty()) {
			throw new NegocioException(errors);
		}
		Ponto ponto = recuperarPorDataRegistroIdUsuario(idUsuario, dataRegistro);
		if (ponto == null) {
			ponto = novoRegistroDePonto(idUsuario, dataRegistro);
		}
		ponto.registraProximoPonto(DateUtils.toLocalTime(dataRegistro));
		pontoRepository.save(ponto);
		return ponto;
	}

	public Ponto recuperarPonto(Long idUsuario, Date dataRegistro, Usuario usuarioLogado) {
		Map<String, String> errors = new HashMap<String, String>();
		if (idUsuario == null) {
			errors.put("usuario", "Usuário não informado");
		}
		if (dataRegistro == null) {
			errors.put("dataRegistro", "Data do registro não informado");
		}
		if (usuarioLogado == null) {
			errors.put("usuario", "Usuario não encontrado");
		}
		if (!errors.isEmpty()) {
			throw new NegocioException(errors);
		}
		return recuperarPorDataRegistroIdUsuario(idUsuario, dataRegistro);
	}

	public List<Ponto> recuperarRegistrosPontoUsuarioId(Long idUsuario) {
		return pontoRepository.findByUsuarioIdOrderByDataRegistroDesc(idUsuario);
	}

	private Ponto recuperarPorDataRegistroIdUsuario(Long idUsuario, Date dataRegistro) {
		return pontoRepository.findByDataRegistroAndUsuarioId(DateUtils.toLocalDate(dataRegistro), idUsuario);
	}

	private Ponto novoRegistroDePonto(Long idUsuario, Date dataRegistro) {
		Ponto ponto = new Ponto();
		Usuario usuario = new Usuario();
		usuario.setId(idUsuario);
		ponto.setUsuario(usuario);
		ponto.setDataRegistro(DateUtils.toLocalDate(dataRegistro));
		return ponto;
	}

	private Map<String, String> validarRegistroPonto(Long idUsuario, Date dataRegistro, Usuario usuarioLogado) {
		Map<String, String> errors = new HashMap<String, String>();
		if (idUsuario == null) {
			errors.put("usuario", "Usuário não informado");
		}
		if (dataRegistro == null) {
			errors.put("dataRegistro", "Data do registro não informado");
		}
		if (usuarioLogado == null) {
			errors.put("usuario", "Usuario não encontrado");
		}
		if (!usuarioLogado.getId().equals(idUsuario)) {
			errors.put("usuario", "Erro ao registrar ponto");
		}

		Usuario usuarioCadastrado = usuarioRepository.findOne(idUsuario);
		if (usuarioCadastrado == null) {
			errors.put("usuario", "Usuario não encontrado");
		}
		return errors;
	}

}
