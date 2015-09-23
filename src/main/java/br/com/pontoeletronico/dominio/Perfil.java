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

	public static Perfil[] getPerfis() {
		return new Perfil[] { SUPERVISOR, OPERADOR };
	}

	public static Perfil getPerfil(String sigla) {
		for (Perfil perfil : Perfil.values()) {
			if (perfil.getSigla().equals(sigla))
				return perfil;
		}
		return null;
	}
}
