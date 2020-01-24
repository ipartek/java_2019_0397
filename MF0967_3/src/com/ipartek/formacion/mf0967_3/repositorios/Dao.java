package com.ipartek.formacion.mf0967_3.repositorios;

public interface Dao<T> {
	Iterable<T> obtenerTodos();

	T obtenerPorId(Long id);

	void agregar(T objeto);

	void modificar(T objeto);

	void borrar(Long id);
}
