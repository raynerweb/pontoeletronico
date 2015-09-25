package br.com.pontoeletronico.dominio;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Status {

	ATIVO("A", "Ativo"), INATIVO("I", "Inativo");
	private String sigla;
	private String descricao;

	private Status(String sigla, String descricao) {
		this.sigla = sigla;
		this.descricao = descricao;
	}

	public String getSigla() {
		return this.sigla;
	}

	public String getDescricao() {
		return this.descricao;
	}

	public static Status getStatusSigla(String sigla) {
		for (Status status : Status.values()) {
			if (status.getSigla().equals(sigla))
				return status;
		}
		return null;
	}

	public static Status getStatusDescricao(String descricao) {
		for (Status status : Status.values()) {
			if (status.getDescricao().equals(descricao))
				return status;
		}
		return null;
	}

}
