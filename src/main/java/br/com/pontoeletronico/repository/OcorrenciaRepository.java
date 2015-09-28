package br.com.pontoeletronico.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.com.pontoeletronico.dominio.Ocorrencia;
import br.com.pontoeletronico.dominio.StatusOcorrencia;
import br.com.pontoeletronico.exception.NegocioException;

public interface OcorrenciaRepository extends CrudRepository<Ocorrencia, Long> {

	public List<Ocorrencia> findByStatusOcorrencia(StatusOcorrencia status) throws NegocioException;

	public List<Ocorrencia> findByStatusOcorrenciaAndUsuarioId(StatusOcorrencia status, Long usuarioId)
			throws NegocioException;

	List<Ocorrencia> findByUsuarioIdOrderByPontoDataRegistroDesc(Long usuarioId);

	List<Ocorrencia> findByUsuarioIdAndPontoDataRegistroBetweenAndStatusOcorrenciaInOrderByPontoDataRegistroDesc(
			Long usuarioId, Date dataInicial, Date dataFinal, List<StatusOcorrencia> statusOcorrencia);
}
