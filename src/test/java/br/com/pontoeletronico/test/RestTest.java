package br.com.pontoeletronico.test;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;

import br.com.pontoeletronico.JerseyConfig;

@SpringApplicationConfiguration(classes = JerseyConfig.class)
@WebAppConfiguration
@IntegrationTest
public abstract class RestTest extends AbstractTest {

	protected RestTemplate rest;
	protected Map<String, Object> params;

	@Before
	public void setUp() {
		rest = new TestRestTemplate();
		params = new HashMap<String, Object>();
	}

	protected void hasText(String text, String message) {
		org.springframework.util.Assert.hasText(text, message);
	}
}
