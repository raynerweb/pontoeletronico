package br.com.pontoeletronico.dominio;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Perfil {

	SUPERVISOR("S", "Supervisor"), OPERADOR("O", "Operador"), ROOT("R", "Root");

	private String sigla;
	private String descricao;

	private Perfil(String sigla, String descricao) {
		this.sigla = sigla;
		this.descricao = descricao;
	}

	public String getSigla() {
		return this.sigla;
	}

	public String getDescricao() {
		return this.descricao;
	}

	public static Perfil[] getPerfis() {
		return new Perfil[] { SUPERVISOR, OPERADOR };
	}

	public static Perfil getPerfilSigla(String sigla) {
		for (Perfil perfil : Perfil.values()) {
			if (perfil.getSigla().equals(sigla))
				return perfil;
		}
		return null;
	}

	public static Perfil getPerfilDescricao(String descricao) {
		for (Perfil perfil : Perfil.values()) {
			if (perfil.getDescricao().equals(descricao))
				return perfil;
		}
		return null;
	}
}
