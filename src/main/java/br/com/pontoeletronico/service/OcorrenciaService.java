package br.com.pontoeletronico.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.pontoeletronico.dominio.Ocorrencia;
import br.com.pontoeletronico.dominio.StatusOcorrencia;
import br.com.pontoeletronico.repository.OcorrenciaRepository;

@Service
public class OcorrenciaService {

	@Autowired
	private OcorrenciaRepository ocorrenciaRepository;
	
	public List<Ocorrencia> consultarStatusOcorrencia(StatusOcorrencia status){
		return getOcorrenciaRepository().findByStatusOcorrencia(status);
	}
	
	public List<Ocorrencia> consultarStatusOcorrenciaUsuarioId(
			Ocorrencia ocorrencia){
		return getOcorrenciaRepository().findByStatusOcorrenciaAndUsuarioId(
				ocorrencia.getStatusOcorrencia(), ocorrencia.getId());
	}
	
	public List<Ocorrencia> consultarUsuarioId(Long usuarioId){
		return getOcorrenciaRepository().findByUsuarioId(usuarioId);
	}
	
	public void registrarOcorrencia(Ocorrencia ocorrencia){
		getOcorrenciaRepository().save(ocorrencia);
	}

	public OcorrenciaRepository getOcorrenciaRepository() {
		return ocorrenciaRepository;
	}
	
}
