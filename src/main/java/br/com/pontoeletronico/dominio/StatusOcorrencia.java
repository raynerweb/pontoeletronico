package br.com.pontoeletronico.dominio;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum StatusOcorrencia {

	ABERTO("AB", "Aberta"), ACEITO("AC", "Aceita"), REJEITADO("RJ", "Rejeitada");
	private String sigla;
	private String descricao;

	private StatusOcorrencia(String sigla, String descricao) {
		this.sigla = sigla;
		this.descricao = descricao;
	}

	public String getSigla() {
		return this.sigla;
	}

	public String getDescricao() {
		return this.descricao;
	}

	public static StatusOcorrencia getStatusOcorrenciaSigla(String sigla) {
		for (StatusOcorrencia status : StatusOcorrencia.values()) {
			if (status.getSigla().equals(sigla))
				return status;
		}
		return null;
	}
}
