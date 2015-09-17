package br.com.pontoeletronico.dto;

import java.util.Date;

import br.com.pontoeletronico.utils.DateUtils;

public class RegistrarPonto {

	private Long idUsuario;
	private Date dataRegistro;

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Date getDataRegistro() {
		return dataRegistro;
	}

	public void setDataRegistro(Long dataRegistro) {
		this.dataRegistro = DateUtils.toDate(dataRegistro);
	}

}
