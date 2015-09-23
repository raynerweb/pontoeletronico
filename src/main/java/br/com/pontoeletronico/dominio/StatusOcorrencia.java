package br.com.pontoeletronico.dominio;

public enum StatusOcorrencia {

	ABERTO("AB"), ACEITO("AC"), REJEITADO("RJ");
	private String sigla;

	private StatusOcorrencia(String sigla) {
		this.sigla = sigla;
	}

	public String getSigla() {
		return this.sigla;
	}

	public static StatusOcorrencia getTipoStatus(String sigla) {
		for (StatusOcorrencia status : StatusOcorrencia.values()) {
			if (status.getSigla().equals(sigla))
				return status;
		}
		return null;
	}
}
