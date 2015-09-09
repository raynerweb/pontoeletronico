package br.com.pontoeletronico.exception;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class NegocioException extends WebApplicationException {

	private static final long serialVersionUID = 5562204433408374183L;
	private static Map<String, String> errors;

	public NegocioException(Map<String, String> erros) {
		super(Response.status(Response.Status.UNAUTHORIZED).entity(erros).type(MediaType.APPLICATION_JSON).build());
	}

	public NegocioException(String error) {
		super(Response.status(Response.Status.UNAUTHORIZED).entity(addError(error)).type(MediaType.APPLICATION_JSON)
				.build());
	}

	private static Map<String, String> errorsMap() {
		if (errors == null) {
			errors = new HashMap<String, String>();
		}
		return errors;
	}

	private static Map<String, String> addError(String error) {
		errorsMap().put("ERROR", error);
		return errors;
	}

}