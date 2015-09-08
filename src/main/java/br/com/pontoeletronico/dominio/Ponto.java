package br.com.pontoeletronico.dominio;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = { "usuario_id", "dataRegistro" }) )
public class Ponto {

	@Id
	@SequenceGenerator(name = "ponto_id_seq", sequenceName = "ponto_id_seq")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "ponto_id_seq")
	private Long id;

	@ManyToOne
	@JoinColumn(nullable = false)
	private Usuario usuario;

	@Column(nullable = false)
	private LocalDate dataRegistro;

	@Column(nullable = false)
	private LocalTime entradaPrimeiroTurno;

	@Column
	private LocalTime saidaPrimeiroTurno;

	@Column
	private LocalTime entradaSegundoTurno;

	@Column
	private LocalTime saidaSegundoTurno;

	@Column
	private LocalTime entradaTerceiroTurno;

	@Column
	private LocalTime saidaTerceiroTurno;

	@Column(nullable = false)
	private boolean permitidoHoraExtra;

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

	public LocalDate getDataRegistro() {
		return dataRegistro;
	}

	public void setDataRegistro(LocalDate dataRegistro) {
		this.dataRegistro = dataRegistro;
	}

	public LocalTime getEntradaPrimeiroTurno() {
		return entradaPrimeiroTurno;
	}

	public void setEntradaPrimeiroTurno(LocalTime entradaPrimeiroTurno) {
		this.entradaPrimeiroTurno = entradaPrimeiroTurno;
	}

	public LocalTime getSaidaPrimeiroTurno() {
		return saidaPrimeiroTurno;
	}

	public void setSaidaPrimeiroTurno(LocalTime saidaPrimeiroTurno) {
		this.saidaPrimeiroTurno = saidaPrimeiroTurno;
	}

	public LocalTime getEntradaSegundoTurno() {
		return entradaSegundoTurno;
	}

	public void setEntradaSegundoTurno(LocalTime entradaSegundoTurno) {
		this.entradaSegundoTurno = entradaSegundoTurno;
	}

	public LocalTime getSaidaSegundoTurno() {
		return saidaSegundoTurno;
	}

	public void setSaidaSegundoTurno(LocalTime saidaSegundoTurno) {
		this.saidaSegundoTurno = saidaSegundoTurno;
	}

	public LocalTime getEntradaTerceiroTurno() {
		return entradaTerceiroTurno;
	}

	public void setEntradaTerceiroTurno(LocalTime entradaTerceiroTurno) {
		this.entradaTerceiroTurno = entradaTerceiroTurno;
	}

	public LocalTime getSaidaTerceiroTurno() {
		return saidaTerceiroTurno;
	}

	public void setSaidaTerceiroTurno(LocalTime saidaTerceiroTurno) {
		this.saidaTerceiroTurno = saidaTerceiroTurno;
	}

	public boolean isPermitidoHoraExtra() {
		return permitidoHoraExtra;
	}

	public void setPermitidoHoraExtra(boolean permitidoHoraExtra) {
		this.permitidoHoraExtra = permitidoHoraExtra;
	}

}
