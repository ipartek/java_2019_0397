package com.ipartek.formacion.mf0967_3.repositorios;

import java.math.BigDecimal;
import java.util.TreeMap;

import com.ipartek.formacion.mf0967_3.modelos.Libro;

public class LibroTreeMap implements Dao<Libro> {

	private TreeMap<Long, Libro> libros = new TreeMap<>();

	// SINGLETON
	private static final LibroTreeMap INSTANCIA = new LibroTreeMap();

	private LibroTreeMap() {
		agregar(new Libro(null, "Futbolísimos", new BigDecimal("123.12"), new Byte("12"), "Javier", "1.jpg"));
		agregar(new Libro(null, "Futbolísimos", new BigDecimal("123.12"), new Byte("12"), "Javier", "1.jpg"));
		agregar(new Libro(null, "Futbolísimos", new BigDecimal("123.12"), new Byte("12"), "Javier", "1.jpg"));
	}

	public static LibroTreeMap getInstancia() {
		return INSTANCIA;
	}
	// FIN SINGLETON

	@Override
	public Iterable<Libro> obtenerTodos() {
		return libros.values();
	}

	@Override
	public Libro obtenerPorId(Long id) {
		return libros.get(id);
	}

	@Override
	public void agregar(Libro libro) {
		Long id = libros.size() == 0L ? 1L : libros.lastKey() + 1L;
		libro.setId(id);
		libros.put(id, libro);
	}

	@Override
	public void modificar(Libro libro) {
		libros.put(libro.getId(), libro);
	}

	@Override
	public void borrar(Long id) {
		if (!libros.containsKey(id)) {
			throw new AccesoDatosException("No se ha encontrado el libro a borrar");
		}

		libros.remove(id);
	}
}
