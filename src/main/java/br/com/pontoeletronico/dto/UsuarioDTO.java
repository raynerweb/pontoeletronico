package br.com.pontoeletronico.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.pontoeletronico.dominio.Perfil;
import br.com.pontoeletronico.dominio.Status;
import br.com.pontoeletronico.dominio.Usuario;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UsuarioDTO {

	private Long id;
	private String nome;
//	private String senha;
	private String matricula;
	private String perfil;
	private String status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

//	public String getSenha() {
//		return senha;
//	}
//
//	public void setSenha(String senha) {
//		this.senha = senha;
//	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getPerfil() {
		return perfil;
	}

	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Usuario toUsuario() {
		Usuario usuario = new Usuario();
		usuario.setId(getId());
		usuario.setNome(getNome());
		usuario.setMatricula(getMatricula());
		usuario.setPerfil(Perfil.getPerfilPeloNome(getPerfil()));
		usuario.setStatus(Status.getStatusPeloNome(getStatus()));
//		usuario.setSenha(getSenha());
		return usuario;
	}

}
