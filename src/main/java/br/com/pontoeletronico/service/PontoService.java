package br.com.pontoeletronico.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.pontoeletronico.dominio.Ponto;
import br.com.pontoeletronico.dominio.Usuario;
import br.com.pontoeletronico.exception.NegocioException;
import br.com.pontoeletronico.repository.PontoRepository;
import br.com.pontoeletronico.utils.DateUtils;

@Service
public class PontoService {

	@Autowired
	private PontoRepository pontoRepository;

	public Ponto registrarPonto(Long idUsuario, Date dataRegistro) throws NegocioException {
		Ponto ponto = recuperarPorDataRegistroIdUsuario(idUsuario, dataRegistro);
		if (ponto == null) {
			ponto = novoRegistroDePonto(idUsuario, dataRegistro);
		}
		try {
			ponto.registraProximoPonto(DateUtils.toLocalTime(dataRegistro));
			pontoRepository.save(ponto);
		}catch(Exception ex){
			throw new NegocioException("");
		}
		return ponto;
	}

	public Ponto recuperarPorDataRegistroIdUsuario(Long idUsuario, Date dataRegistro) {
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

}
