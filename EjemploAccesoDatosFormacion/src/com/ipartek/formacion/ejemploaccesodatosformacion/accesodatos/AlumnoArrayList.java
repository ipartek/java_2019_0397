package com.ipartek.formacion.ejemploaccesodatosformacion.accesodatos;

import java.util.ArrayList;
import java.util.Date;

import com.ipartek.formacion.ejemploaccesodatosformacion.entidades.Alumno;

public class AlumnoArrayList implements Dao<Alumno> {

	private ArrayList<Alumno> alumnos = new ArrayList<>();

	private static Long ultimoId = 0L;

	// SINGLETON
	private static final AlumnoArrayList INSTANCIA = new AlumnoArrayList();

	@SuppressWarnings("deprecation")
	private AlumnoArrayList() {
		// TODO eliminar obsoletos
		agregar(new Alumno(null, "Javier", "Lete García", "12345678Z", new Date(1980 - 1900, 5 - 1, 5)));
		agregar(new Alumno(null, "Pepe", "Pérez García", "84908444B", new Date(1982 - 1900, 1 - 1, 10)));
	}

	public static AlumnoArrayList getInstancia() {
		return INSTANCIA;
	}
	// FIN SINGLETON

	@Override
	public Iterable<Alumno> obtenerTodos() {
		return alumnos;
	}

	private Integer obtenerIndicePorId(Long id) {
		for (int i = 0; i < alumnos.size(); i++) {
			if (alumnos.get(i).getId() == id) {
				return i;
			}
		}

		return null;
	}

	@Override
	public Alumno obtenerPorId(Long id) {
		Integer i = obtenerIndicePorId(id);

		if (i == null) {
			return null;
		}

		return alumnos.get(i);
		
//		for (int i = 0; i < alumnos.size(); i++) {
//			if (alumnos.get(i).getId() == id) {
//				return alumnos.get(i);
//			}
//		}
//
//		return null;
	}

	@Override
	public Alumno agregar(Alumno alumno) {
		if (alumno == null) {
			throw new AccesoDatosException("No se aceptan alumnos nulos");
		}

		Long id = ++ultimoId;

		// TODO Comprobar si se ha recibido ID y lanzar excepción
		alumno.setId(id);

		alumnos.add(alumno);

		return alumno;
	}

	@Override
	public Alumno modificar(Alumno alumno) {
		Integer i = obtenerIndicePorId(alumno.getId());

		if (i == null) {
			throw new AccesoDatosException("No se ha encontrado ese ID");
		}

		return alumnos.set((int) i, alumno);
	}

	@Override
	public void borrar(Long id) {
		Integer i = obtenerIndicePorId(id);

		if (i == null) {
			throw new AccesoDatosException("No se ha encontrado ese ID");
		}

		alumnos.remove((int) i);
	}

}
