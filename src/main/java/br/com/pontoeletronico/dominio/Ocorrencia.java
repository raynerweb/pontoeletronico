package br.com.pontoeletronico.dominio;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

@Entity
public class Ocorrencia {

	@Id
	@SequenceGenerator(name = "ocorrencia_id_seq", sequenceName = "ocorrencia_id_seq")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "ocorrencia_id_seq")
	private Long id;

	@ManyToOne
	@JoinColumn(nullable = false)
	private Usuario usuario;

	@ManyToOne
	@JoinColumn(nullable = false)
	private Ponto ponto;

	@Column(nullable = false)
	private String justificativa;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Ponto getPonto() {
		return ponto;
	}

	public void setPonto(Ponto ponto) {
		this.ponto = ponto;
	}

	public String getJustificativa() {
		return justificativa;
	}

	public void setJustificativa(String justificativa) {
		this.justificativa = justificativa;
	}

}
