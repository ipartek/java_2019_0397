package com.ipartek.formacion.poo;

public class PuntoException extends RuntimeException {

	private static final long serialVersionUID = -6863963652886951981L;

	public PuntoException() {
	}

	public PuntoException(String message) {
		super(message);
	}

	public PuntoException(Throwable cause) {
		super(cause);
	}

	public PuntoException(String message, Throwable cause) {
		super(message, cause);
	}

	public PuntoException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
