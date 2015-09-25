package br.com.pontoeletronico.dto;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.pontoeletronico.dominio.Perfil;
import br.com.pontoeletronico.dominio.Status;
import br.com.pontoeletronico.dominio.Usuario;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UsuarioDTO {

	private Long id;
	private String nome;
	private String matricula;
	private String perfil;
	private String status;
	private boolean possuiSenha;

	public UsuarioDTO() {
	}

	public UsuarioDTO(Usuario usuario) {
		setId(usuario.getId());
		setNome(usuario.getNome());
		setMatricula(usuario.getMatricula());
		setPerfil(usuario.getPerfil().getDescricao());
		setStatus(usuario.getStatus().getDescricao());
		setPossuiSenha(StringUtils.isNotBlank(usuario.getSenha()));
	}

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

	public void setPossuiSenha(boolean possuiSenha) {
		this.possuiSenha = possuiSenha;
	}

	public boolean isPossuiSenha() {
		return possuiSenha;
	}

	public Usuario toUsuario() {
		Usuario usuario = new Usuario();
		usuario.setId(getId());
		usuario.setNome(getNome());
		usuario.setMatricula(getMatricula());
		usuario.setPerfil(Perfil.getPerfilDescricao(getPerfil()));
		usuario.setStatus(Status.getStatusDescricao(getStatus()));
		return usuario;
	}

}
