package com.ipartek.formacion.masajes.repositorios;

public class RepositoriosException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public RepositoriosException() {
		super();
	}

	public RepositoriosException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public RepositoriosException(String message, Throwable cause) {
		super(message, cause);
	}

	public RepositoriosException(String message) {
		super(message);
	}

	public RepositoriosException(Throwable cause) {
		super(cause);
	}
}
