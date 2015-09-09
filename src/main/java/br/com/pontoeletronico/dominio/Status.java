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

}
