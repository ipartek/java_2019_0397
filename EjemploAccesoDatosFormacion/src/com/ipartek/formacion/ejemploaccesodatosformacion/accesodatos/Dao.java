package com.ipartek.formacion.ejemploaccesodatosformacion.accesodatos;

public interface Dao<T> {
	public Iterable<T> obtenerTodos();
	public T obtenerPorId(Long id);
	
	public T agregar(T t);
	public T modificar(T t);
	public void borrar(Long id);
}
