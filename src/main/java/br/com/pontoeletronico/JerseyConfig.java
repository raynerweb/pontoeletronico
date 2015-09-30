package br.com.pontoeletronico;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;

@ApplicationPath("/rest")
public class JerseyConfig extends ResourceConfig {

	public JerseyConfig() {

		// add Bean Validation
		property(ServerProperties.BV_SEND_ERROR_IN_RESPONSE, true);
		property(ServerProperties.BV_DISABLE_VALIDATE_ON_EXECUTABLE_OVERRIDE_CHECK, true);

		packages("br.com.pontoeletronico.rest");
	}

}

