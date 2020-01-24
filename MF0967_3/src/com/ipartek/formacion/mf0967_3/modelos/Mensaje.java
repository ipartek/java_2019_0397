package com.ipartek.formacion.mf0967_3.modelos;

public class Mensaje {
	public static enum Nivel {
		ERROR, INFORMATIVO, ALERTA
	}

	private String texto;
	private Nivel nivel;

	public Mensaje(String texto, Nivel nivel) {
		this.texto = texto;
		this.nivel = nivel;
	}

	public String getTexto() {
		return texto;
	}

	public String getNivelBootstrap() {
		switch (nivel) {
		case ERROR:
			return "danger";
		case ALERTA:
			return "warning";
		case INFORMATIVO:
			return "info";
		default:
			throw new MensajeException("Tipo de mensaje no reconocido");
		}
	}
}
