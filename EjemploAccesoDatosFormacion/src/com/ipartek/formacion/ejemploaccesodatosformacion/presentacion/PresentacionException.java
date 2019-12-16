package com.ipartek.formacion.ejemploaccesodatosformacion.presentacion;

public class PresentacionException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public PresentacionException() {
	}

	public PresentacionException(String message) {
		super(message);
	}

	public PresentacionException(Throwable cause) {
		super(cause);
	}

	public PresentacionException(String message, Throwable cause) {
		super(message, cause);
	}

	public PresentacionException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
