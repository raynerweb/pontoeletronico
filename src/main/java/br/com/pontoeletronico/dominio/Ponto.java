package br.com.pontoeletronico.dominio;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import br.com.pontoeletronico.exception.NegocioException;

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
	@Temporal(TemporalType.DATE)
	private Date dataRegistro;

	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date entradaPrimeiroTurno;

	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date saidaPrimeiroTurno;

	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date entradaSegundoTurno;

	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date saidaSegundoTurno;

	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date entradaTerceiroTurno;

	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date saidaTerceiroTurno;

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

	public Date getDataRegistro() {
		return dataRegistro;
	}

	public void setDataRegistro(Date dataRegistro) {
		this.dataRegistro = dataRegistro;
	}

	public Date getEntradaPrimeiroTurno() {
		return entradaPrimeiroTurno;
	}

	public void setEntradaPrimeiroTurno(Date entradaPrimeiroTurno) {
		this.entradaPrimeiroTurno = entradaPrimeiroTurno;
	}

	public Date getSaidaPrimeiroTurno() {
		return saidaPrimeiroTurno;
	}

	public void setSaidaPrimeiroTurno(Date saidaPrimeiroTurno) {
		this.saidaPrimeiroTurno = saidaPrimeiroTurno;
	}

	public Date getEntradaSegundoTurno() {
		return entradaSegundoTurno;
	}

	public void setEntradaSegundoTurno(Date entradaSegundoTurno) {
		this.entradaSegundoTurno = entradaSegundoTurno;
	}

	public Date getSaidaSegundoTurno() {
		return saidaSegundoTurno;
	}

	public void setSaidaSegundoTurno(Date saidaSegundoTurno) {
		this.saidaSegundoTurno = saidaSegundoTurno;
	}

	public Date getEntradaTerceiroTurno() {
		return entradaTerceiroTurno;
	}

	public void setEntradaTerceiroTurno(Date entradaTerceiroTurno) {
		this.entradaTerceiroTurno = entradaTerceiroTurno;
	}

	public Date getSaidaTerceiroTurno() {
		return saidaTerceiroTurno;
	}

	public void setSaidaTerceiroTurno(Date saidaTerceiroTurno) {
		this.saidaTerceiroTurno = saidaTerceiroTurno;
	}

	public boolean isPermitidoHoraExtra() {
		return permitidoHoraExtra;
	}

	public void setPermitidoHoraExtra(boolean permitidoHoraExtra) {
		this.permitidoHoraExtra = permitidoHoraExtra;
	}

	public void registraProximoPonto(Date proximoRegistro) {
		if (getEntradaPrimeiroTurno() == null) {
			setEntradaPrimeiroTurno(proximoRegistro);
			return;
		}
		if (getSaidaPrimeiroTurno() == null) {
			setSaidaPrimeiroTurno(proximoRegistro);
			return;
		}

		if (getEntradaSegundoTurno() == null) {
			setEntradaSegundoTurno(proximoRegistro);
			return;
		}
		if (getSaidaSegundoTurno() == null) {
			setSaidaSegundoTurno(proximoRegistro);
			return;
		}

		if (isPermitidoHoraExtra()) {
			if (getEntradaTerceiroTurno() == null) {
				setEntradaTerceiroTurno(proximoRegistro);
				return;
			}
			if (getSaidaTerceiroTurno() == null) {
				setSaidaTerceiroTurno(proximoRegistro);
				return;
			}
		}
		throw new NegocioException("Todos os registros foram realizados");
	}

}
