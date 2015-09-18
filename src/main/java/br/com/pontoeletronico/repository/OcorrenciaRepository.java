package br.com.pontoeletronico.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.com.pontoeletronico.dominio.Ocorrencia;
import br.com.pontoeletronico.dominio.StatusOcorrencia;

public interface OcorrenciaRepository extends CrudRepository<Ocorrencia, Long> {

	public List<Ocorrencia> findByStatusOcorrencia(StatusOcorrencia status);
	public List<Ocorrencia> findByStatusOcorrenciaAndUsuarioId(
			StatusOcorrencia status, Long usuarioId);
	public List<Ocorrencia> findByUsuarioId(Long usuarioId);
}
