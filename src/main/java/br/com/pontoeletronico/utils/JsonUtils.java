package br.com.pontoeletronico.utils;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.ObjectWriter;

public class JsonUtils {

	public static <T> String toJson(T t) {
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		try {
			return ow.writeValueAsString(t);
		} catch (JsonProcessingException e) {
			throw new RuntimeException(
					String.format("Erro ao converter %s em JsonString.", t.getClass().getCanonicalName()));
		}
	}

	public static <T> T toObject(Class<T> classe, String json) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			ObjectReader reader = mapper.reader(classe);
			return reader.readValue(json);
		} catch (IOException e) {
			throw new RuntimeException(String.format("Erro ao converter JsonString em %s.", classe.getCanonicalName()));
		}
	}
}
