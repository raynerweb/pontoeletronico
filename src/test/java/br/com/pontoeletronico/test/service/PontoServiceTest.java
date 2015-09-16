package br.com.pontoeletronico.test.service;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import br.com.pontoeletronico.dominio.Ponto;

import br.com.pontoeletronico.exception.NegocioException;
import br.com.pontoeletronico.repository.PontoRepository;
import br.com.pontoeletronico.service.PontoService;
import br.com.pontoeletronico.test.AbstractTest;

public class PontoServiceTest extends AbstractTest {

	@Autowired
	private PontoRepository pontoRepository;
	
	@Autowired
	private PontoService pontoService;

	@Test
	public void registrarPontoSucesso() {
		
		try{
			Ponto novoPonto = getPontoService().registrarPonto(new Long(1), 
					new Date());
			Assert.assertNotNull(novoPonto);
		}catch(Exception ex){
			//Assert.assertEquals(ex, new NegocioException("HTTP 401 Unauthorized"));
			Assert.assertEquals(ex.getMessage(), "HTTP 401 Unauthorized");
		}	
		
	}

	@Test(expected=NegocioException.class)
	public void registrarPontoError() {
		Ponto novoPonto = getPontoService().registrarPonto(new Long(12345678), 
				new Date());
	}
	
	@Test
	public void recuperarPorDataRegistroIdUsuario() {	
		Ponto ponto = getPontoService().recuperarPorDataRegistroIdUsuario(
				new Long(1), new Date());
		Assert.assertNull(ponto);
	}

	public PontoRepository getPontoRepository() {
		return pontoRepository;
	}

	public PontoService getPontoService() {
		return pontoService;
	}

}
