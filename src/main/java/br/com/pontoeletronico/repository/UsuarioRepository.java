package br.com.pontoeletronico.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.pontoeletronico.dominio.Perfil;
import br.com.pontoeletronico.dominio.Status;
import br.com.pontoeletronico.dominio.Usuario;

@Repository
public interface UsuarioRepository extends CrudRepository<Usuario, Long> {

	Usuario findByMatriculaAndSenha(String matricula, String senha);
	
	List<Usuario> findByStatusInAndPerfilIn(Status[] status, Perfil[] perfil);
	
}
