package br.com.pontoeletronico.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.com.pontoeletronico.dominio.Ponto;

public interface PontoRepository extends CrudRepository<Ponto, Long> {

	Ponto findByDataRegistroAndUsuarioId(Date dataRegistro, Long idUsuario);

	List<Ponto> findByUsuarioIdOrderByDataRegistroDesc(Long idUsuario);

}
