package br.com.pontoeletronico.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.pontoeletronico.dominio.Usuario;

@Repository
public interface UsuarioRepository extends CrudRepository<Usuario, Long> {

	Usuario findByMatriculaAndSenha(String matricula, String senha);
}
