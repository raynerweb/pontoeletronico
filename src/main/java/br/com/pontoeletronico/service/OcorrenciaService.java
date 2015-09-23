package br.com.pontoeletronico.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.pontoeletronico.dominio.Ocorrencia;
import br.com.pontoeletronico.dominio.StatusOcorrencia;
import br.com.pontoeletronico.exception.NegocioException;
import br.com.pontoeletronico.repository.OcorrenciaRepository;

@Service
public class OcorrenciaService {

	@Autowired
	private OcorrenciaRepository ocorrenciaRepository;

	private OcorrenciaRepository getOcorrenciaRepository() {
		return ocorrenciaRepository;
	}

	public List<Ocorrencia> consultarStatusOcorrencia(StatusOcorrencia status) throws NegocioException {
		if (status != null)
			return getOcorrenciaRepository().findByStatusOcorrencia(status);
		else
			throw new NegocioException("Status Ocorrência não informado");
	}

	public List<Ocorrencia> consultarStatusOcorrenciaUsuarioId(Ocorrencia ocorrencia) throws NegocioException {
		return getOcorrenciaRepository().findByStatusOcorrenciaAndUsuarioId(ocorrencia.getStatusOcorrencia(),
				ocorrencia.getUsuario().getId());
	}

	public List<Ocorrencia> consultarUsuarioId(Long usuarioId) throws NegocioException {
		return getOcorrenciaRepository().findByUsuarioId(usuarioId);
	}

	public void registrarOcorrencia(Ocorrencia ocorrencia) throws NegocioException {
		getOcorrenciaRepository().save(ocorrencia);
	}

}
