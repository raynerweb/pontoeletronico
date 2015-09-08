package br.com.pontoeletronico;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("/rest")
public class JerseyConfig extends ResourceConfig {

	public JerseyConfig() {
		packages("br.com.pontoeletronico.rest");
	}
	
}
