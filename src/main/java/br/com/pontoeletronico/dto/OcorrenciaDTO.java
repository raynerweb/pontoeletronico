package br.com.pontoeletronico.dto;

import java.util.Date;

import br.com.pontoeletronico.dominio.Ocorrencia;
import br.com.pontoeletronico.dominio.Ponto;
import br.com.pontoeletronico.dominio.StatusOcorrencia;
import br.com.pontoeletronico.dominio.Usuario;

public class OcorrenciaDTO {

	public OcorrenciaDTO() {
	}

	public OcorrenciaDTO(Ocorrencia ocorrencia) {
		setIdOcorrencia(ocorrencia.getId());
		setJustificativa(ocorrencia.getJustificativa());
		setDescricaoStatusOcorrencia(ocorrencia.getStatusOcorrencia().getDescricao());
		setRejeitada(StatusOcorrencia.REJEITADO.equals(ocorrencia.getStatusOcorrencia()));
		setIdUsuario(ocorrencia.getUsuario().getId());
		
		Ponto ponto = ocorrencia.getPonto();
		setDataRegistro(ponto.getDataRegistro());
		setEntradaPrimeiroTurno(ponto.getEntradaPrimeiroTurno());
		setSaidaPrimeiroTurno(ponto.getSaidaPrimeiroTurno());
		setEntradaSegundoTurno(ponto.getEntradaSegundoTurno());
		setSaidaSegundoTurno(ponto.getSaidaSegundoTurno());
		setEntradaTerceiroTurno(ponto.getEntradaTerceiroTurno());
		setSaidaTerceiroTurno(getSaidaTerceiroTurno());
		setPermitidoHoraExtra(ponto.isPermitidoHoraExtra());
	}

	private Long idOcorrencia;
	private Long idUsuario;
	private String justificativa;
	private Date dataRegistro;
	private Date entradaPrimeiroTurno;
	private Date saidaPrimeiroTurno;
	private Date entradaSegundoTurno;
	private Date saidaSegundoTurno;
	private Date entradaTerceiroTurno;
	private Date saidaTerceiroTurno;
	private boolean permitidoHoraExtra;
	private boolean rejeitada;
	private String descricaoStatusOcorrencia;

	public Long getIdOcorrencia() {
		return idOcorrencia;
	}

	public void setIdOcorrencia(Long idOcorrencia) {
		this.idOcorrencia = idOcorrencia;
	}
	
	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getJustificativa() {
		return justificativa;
	}

	public void setJustificativa(String justificativa) {
		this.justificativa = justificativa;
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

	public boolean isRejeitada() {
		return rejeitada;
	}

	public void setRejeitada(boolean rejeitada) {
		this.rejeitada = rejeitada;
	}

	public String getDescricaoStatusOcorrencia() {
		return descricaoStatusOcorrencia;
	}

	public void setDescricaoStatusOcorrencia(String descricaoStatusOcorrencia) {
		this.descricaoStatusOcorrencia = descricaoStatusOcorrencia;
	}

	public Ocorrencia toOcorrencia() {
		Ocorrencia o = new Ocorrencia();
		o.setJustificativa(getJustificativa());
		o.setStatusOcorrencia(StatusOcorrencia.getStatusOcorrenciaDescricao(getDescricaoStatusOcorrencia()));
		Usuario usuario = new Usuario();
		usuario.setId(getIdUsuario());
		
		Ponto p = new Ponto();
		p.setEntradaPrimeiroTurno(getEntradaPrimeiroTurno());
		p.setSaidaPrimeiroTurno(getSaidaPrimeiroTurno());
		p.setEntradaSegundoTurno(getEntradaSegundoTurno());
		p.setSaidaSegundoTurno(getSaidaSegundoTurno());
		p.setEntradaTerceiroTurno(getEntradaTerceiroTurno());
		p.setSaidaTerceiroTurno(getSaidaTerceiroTurno());
		p.setPermitidoHoraExtra(isPermitidoHoraExtra());
		
		o.setUsuario(usuario);
		o.setPonto(p);
		return o;
	}
}
