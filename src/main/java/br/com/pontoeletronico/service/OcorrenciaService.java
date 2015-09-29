package br.com.pontoeletronico.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.pontoeletronico.dominio.Ocorrencia;
import br.com.pontoeletronico.dominio.Ponto;
import br.com.pontoeletronico.dominio.StatusOcorrencia;
import br.com.pontoeletronico.dto.OcorrenciaDTO;
import br.com.pontoeletronico.exception.NegocioException;
import br.com.pontoeletronico.repository.OcorrenciaRepository;

@Service
public class OcorrenciaService {
	
	private final Logger log = Logger.getLogger(OcorrenciaService.class);

	@Autowired
	private OcorrenciaRepository ocorrenciaRepository;

	@Autowired
	private PontoService pontoService;

	private OcorrenciaRepository getOcorrenciaRepository() {
		return ocorrenciaRepository;
	}

	public List<Ocorrencia> consultarStatusOcorrencia(StatusOcorrencia status) throws NegocioException {
		if (status != null)
			return getOcorrenciaRepository().findByStatusOcorrencia(status);
		else
			throw new NegocioException("Status Ocorrência não informado");
	}

	public List<Ocorrencia> consultarStatusOcorrenciaUsuarioId(Ocorrencia ocorrencia) throws NegocioException {
		return getOcorrenciaRepository().findByStatusOcorrenciaAndUsuarioId(ocorrencia.getStatusOcorrencia(),
				ocorrencia.getUsuario().getId());
	}

	public List<Ocorrencia> consultarUsuarioId(Long usuarioId) throws NegocioException {
		return getOcorrenciaRepository().findByUsuarioIdOrderByPontoDataRegistroDesc(usuarioId);
	}

	public OcorrenciaDTO registrarOcorrencia(OcorrenciaDTO ocorrenciaDto) {
		Ponto ponto = pontoService.recuperarPorDataRegistroIdUsuario(ocorrenciaDto.getIdUsuario(),
				ocorrenciaDto.getDataRegistro());
		if (ponto == null) {
			String mensagem = String.format("Não houve ponto registrado em %s",
					new SimpleDateFormat("dd/MM/yyyy").format(ocorrenciaDto.getDataRegistro()));
			throw new NegocioException(mensagem);
		}
		Ocorrencia ocorrencia = ocorrenciaDto.toOcorrencia();
		ocorrencia.setPonto(ponto);
		ocorrencia.setStatusOcorrencia(StatusOcorrencia.ABERTO);
		return armazenarOcorrencia(ocorrencia);
	}

	public OcorrenciaDTO armazenarOcorrencia(Ocorrencia ocorrencia) {
		try {
			return new OcorrenciaDTO(getOcorrenciaRepository().save(ocorrencia));
		} catch (Throwable e) {
			log.error(e.getMessage());
			throw new NegocioException("Erro inexperado. Favor contactar administrador.");
		}
	}

	public List<OcorrenciaDTO> recuperaPorIdUsuarioIntervaloDataRegistroStatusOcorrencia(Long idUsuario,
			Date dataInicial, Date dataFinal, List<String> siglasStatusOcorrencia) {

		List<StatusOcorrencia> statusOcorrencia = converteListaSiglasParaListaStatusOcorrencia(siglasStatusOcorrencia);
		validaPesquisaOcorrencia(idUsuario, dataInicial, dataFinal);
		List<Ocorrencia> ocorrencias = ocorrenciaRepository
				.findByUsuarioIdAndPontoDataRegistroBetweenAndStatusOcorrenciaInOrderByPontoDataRegistroDesc(idUsuario,
						dataInicial, dataFinal, statusOcorrencia);
		List<OcorrenciaDTO> ocorrenciasDTO = new ArrayList<OcorrenciaDTO>();
		for (Ocorrencia ocorrencia : ocorrencias) {
			ocorrenciasDTO.add(new OcorrenciaDTO(ocorrencia));
		}
		return ocorrenciasDTO;
	}

	private List<StatusOcorrencia> converteListaSiglasParaListaStatusOcorrencia(List<String> siglasStatusOcorrencia) {
		List<StatusOcorrencia> status = new ArrayList<StatusOcorrencia>();
		for (String sigla : siglasStatusOcorrencia) {
			if (StatusOcorrencia.getStatusOcorrenciaSigla(sigla) != null) {
				status.add(StatusOcorrencia.getStatusOcorrenciaSigla(sigla));
			}
		}
		if (status.isEmpty()) {
			status = Arrays.asList(StatusOcorrencia.values());
		}
		return status;
	}

	private void validaPesquisaOcorrencia(Long idUsuario, Date dataInicial, Date dataFinal) {
		Map<String, String> errors = new HashMap<String, String>();
		if (idUsuario == null) {
			errors.put("usuario", "Usuário não informado");
		}
		if (dataInicial == null) {
			errors.put("dataInicial", "Data inicial não informada");
		}
		if (dataFinal == null) {
			errors.put("dataFinal", "Data final não informada");
		}
		if (!errors.isEmpty()) {
			throw new NegocioException(errors);
		}
	}

}
