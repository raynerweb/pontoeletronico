package br.com.pontoeletronico.dto;

import java.util.Calendar;
import java.util.Date;

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
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(dataRegistro);
		this.dataRegistro = cal.getTime();
	}

}
