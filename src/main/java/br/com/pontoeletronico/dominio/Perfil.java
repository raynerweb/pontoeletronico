package br.com.pontoeletronico.dominio;

public enum Perfil {

	SUPERVISOR("S"), OPERADOR("O"), ROOT("R");

	private String sigla;

	private Perfil(String sigla) {
		this.sigla = sigla;
	}

	public String getSigla() {
		return this.sigla;
	}
}
