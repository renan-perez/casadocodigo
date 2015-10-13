package org.casadocodigo.loja.enums;

public enum ValidationsTypes {

	FIELD_REQUIRED("field.required");

	private String tipoValidacao;

	ValidationsTypes(String tipoValidacao) {
		this.tipoValidacao = tipoValidacao;
	}
	
	public String getTipoValidacao() {
		return this.tipoValidacao;
	}

}
