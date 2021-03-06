package br.com.pontoeletronico.rest;

import java.util.List;

import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.pontoeletronico.dominio.Ocorrencia;
import br.com.pontoeletronico.dominio.StatusOcorrencia;
import br.com.pontoeletronico.dto.OcorrenciaDTO;
import br.com.pontoeletronico.exception.NegocioException;
import br.com.pontoeletronico.service.OcorrenciaService;
import br.com.pontoeletronico.utils.DateUtils;

@Path("/ocorrencia")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class OcorrenciaRest {

	@Autowired
	private OcorrenciaService ocorrenciaService;
	private Ocorrencia ocorrencia;

	@Path("/consultar")
	@GET
	public Response consultar(@NotNull(message="{campo.obrigatorio}") @QueryParam("idUsuario") Long idUsuario, @QueryParam("dataInicial") Long dataInicial,
			@QueryParam("dataFinal") Long dataFinal, @QueryParam("status") List<String> siglasStatusOcorrencia) {
		return Response.ok(ocorrenciaService.recuperaPorIdUsuarioIntervaloDataRegistroStatusOcorrencia(idUsuario,
				DateUtils.toDate(dataInicial), DateUtils.toDate(dataFinal), siglasStatusOcorrencia)).build();
	}

	@Path("/consultarStatusOcorrencia")
	@GET
	public List<Ocorrencia> consultarStatusOcorrencia(@QueryParam("status") String status) throws NegocioException {
		StatusOcorrencia statusOcorrencia = StatusOcorrencia.getStatusOcorrenciaSigla(status);
		return getOcorrenciaService().consultarStatusOcorrencia(statusOcorrencia);
	}

	@Path("/consultarStatusOcorrenciaUsuarioId")
	public List<Ocorrencia> consultarStatusOcorrenciaUsuarioId(@QueryParam("status") String status,
			@QueryParam("usuarioId") Long usuarioId) throws NegocioException {
		StatusOcorrencia statusOcorrencia = StatusOcorrencia.getStatusOcorrenciaSigla(status);
		getOcorrencia().setStatusOcorrencia(statusOcorrencia);
		getOcorrencia().setId(usuarioId);
		return getOcorrenciaService().consultarStatusOcorrenciaUsuarioId(getOcorrencia());
	}

	@Path("/consultarUsuarioId")
	@GET
	public List<Ocorrencia> consultarUsuarioId(@QueryParam("usuarioId") Long usuarioId) throws NegocioException {
		return getOcorrenciaService().consultarUsuarioId(usuarioId);
	}

	@Path("/registrarOcorrencia")
	@POST
	public Response registrarOcorrencia(OcorrenciaDTO ocorrencia) throws NegocioException {
		return Response.ok(getOcorrenciaService().registrarOcorrencia(ocorrencia)).build();
	}

	private OcorrenciaService getOcorrenciaService() {
		return ocorrenciaService;
	}

	private Ocorrencia getOcorrencia() {
		if (ocorrencia == null)
			ocorrencia = new Ocorrencia();
		return ocorrencia;
	}

}
