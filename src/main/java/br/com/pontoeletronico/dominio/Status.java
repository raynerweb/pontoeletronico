package br.com.pontoeletronico.dominio;

public enum Status {

	ATIVO("A"), INATIVO("I");
	private String sigla;

	private Status(String sigla) {
		this.sigla = sigla;
	}

	public String getSigla() {
		return this.sigla;
	}

	public static Status getStatus(String sigla) {
		for (Status status : Status.values()) {
			if (status.getSigla().equals(sigla))
				return status;
		}
		return null;
	}

	public static Status getStatusPeloNome(String nome) {
		for (Status status : Status.values()) {
			if (status.name().equals(nome))
				return status;
		}
		return null;
	}

}
