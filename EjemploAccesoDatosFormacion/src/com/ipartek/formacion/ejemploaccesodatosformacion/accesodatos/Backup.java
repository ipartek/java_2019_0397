package com.ipartek.formacion.ejemploaccesodatosformacion.accesodatos;

public interface Backup<T> {
	public void guardar(Iterable<T> datos);
	public Iterable<T> recuperar();
}
