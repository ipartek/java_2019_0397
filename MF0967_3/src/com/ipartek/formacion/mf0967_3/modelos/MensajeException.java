package com.ipartek.formacion.mf0967_3.modelos;

public class MensajeException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public MensajeException() {
		super();
	}

	public MensajeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public MensajeException(String message, Throwable cause) {
		super(message, cause);
	}

	public MensajeException(String message) {
		super(message);
	}

	public MensajeException(Throwable cause) {
		super(cause);
	}

}
