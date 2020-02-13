package com.ipartek.formacion.masajes.repositorios;

public interface Dao<T> {
	Iterable<T> getAll();
	T getById(Integer id);
	
	void insert(T objeto);
	void update(T objeto);
	void delete(Integer id);
}
