package br.com.pontoeletronico.converter;

public interface EnumConverter {
	
	String getSigla();
	
	EnumConverter getEnum(String sigla);
	
}
