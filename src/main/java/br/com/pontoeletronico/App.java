package br.com.pontoeletronico;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;

public class App extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(
			SpringApplicationBuilder application) {
		return application.sources(SpringConfig.class);
	}
}
