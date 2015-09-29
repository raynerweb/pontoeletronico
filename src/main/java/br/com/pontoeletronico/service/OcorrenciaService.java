package br.com.pontoeletronico.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

	public OcorrenciaDTO registrarOcorrencia(OcorrenciaDTO ocorrenciaDto) throws NegocioException {
		Ponto ponto = pontoService.recuperarPorDataRegistroIdUsuario(ocorrenciaDto.getIdUsuario(), ocorrenciaDto.getDataRegistro());
		Ocorrencia ocorrencia = ocorrenciaDto.toOcorrencia();
		ocorrencia.setPonto(ponto);
		ocorrencia.setStatusOcorrencia(StatusOcorrencia.ABERTO);
		return armazenarOcorrencia(ocorrencia);
	}
	
	public OcorrenciaDTO armazenarOcorrencia(Ocorrencia ocorrencia){
		return new OcorrenciaDTO(getOcorrenciaRepository().save(ocorrencia));
	}

	public List<OcorrenciaDTO> recuperaPorIdUsuarioIntervaloDataRegistroStatusOcorrencia(Long idUsuario, Date dataInicial,
			Date dataFinal, List<String> siglasStatusOcorrencia) {
		
		List<StatusOcorrencia> statusOcorrencia = converteListaSiglasParaListaStatusOcorrencia(siglasStatusOcorrencia);
		validaPesquisaOcorrencia(idUsuario, dataInicial, dataFinal);
		List<Ocorrencia> ocorrencias = ocorrenciaRepository.findByUsuarioIdAndPontoDataRegistroBetweenAndStatusOcorrenciaInOrderByPontoDataRegistroDesc(idUsuario, dataInicial, dataFinal, statusOcorrencia);
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
	
	private void validaPesquisaOcorrencia(Long idUsuario, Date dataInicial, Date dataFinal){
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
