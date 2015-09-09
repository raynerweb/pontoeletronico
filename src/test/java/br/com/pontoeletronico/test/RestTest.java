package br.com.pontoeletronico.test;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.junit.Before;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;

import br.com.pontoeletronico.JerseyConfig;

@SpringApplicationConfiguration(classes = JerseyConfig.class)
@WebAppConfiguration
@IntegrationTest
public abstract class RestTest extends AbstractTest {

	protected RestTemplate rest;
	protected Map<String, Object> params;
	protected HttpServletRequest request;

	@Before
	public void setUp() {
		rest = new TestRestTemplate();
		params = new HashMap<String, Object>();
		request = new MockHttpServletRequest();
	}

	protected void hasText(String text, String message) {
		org.springframework.util.Assert.hasText(text, message);
	}
}
