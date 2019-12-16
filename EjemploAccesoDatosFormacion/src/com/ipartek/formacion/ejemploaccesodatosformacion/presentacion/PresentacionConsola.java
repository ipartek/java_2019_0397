package com.ipartek.formacion.ejemploaccesodatosformacion.presentacion;

import com.ipartek.formacion.ejemploaccesodatosformacion.accesodatos.AlumnoArrayList;
import com.ipartek.formacion.ejemploaccesodatosformacion.accesodatos.Dao;
import com.ipartek.formacion.ejemploaccesodatosformacion.entidades.Alumno;
import com.ipartek.formacion.ejemploaccesodatosformacion.utilidades.Biblioteca;

public class PresentacionConsola {

	private static final int OPCION_AGREGAR = 2;

	private static final int OPCION_LISTADO = 1;

	private static final int OPCION_SALIR = 0;

	private static final Dao<Alumno> dao = AlumnoArrayList.getInstancia();

	public static void main(String[] args) {
		try {
			int opcion = 0;

			do {
				mostrarOpciones();
				opcion = pedirOpcion();
				procesarOpcion(opcion);
			} while (opcion != OPCION_SALIR);

			saludoFinal();
		} catch (Exception e) {
			mostrar("Error no esperado");
			// TODO Enviar los errores a fichero de log
			e.printStackTrace();
		}
	}

	private static void procesarOpcion(int opcion) {
		switch (opcion) {
		case OPCION_SALIR:
			mostrar("SALIR");
			break;
		case OPCION_LISTADO:
			mostrar("LISTADO");

			listadoAlumnos();

			break;
		case OPCION_AGREGAR:
			mostrar("PENDIENTE-------AÑADIR ALUMNO");
			
			break;
		default:
			mostrar("NO IMPLEMENTADO");
		}
	}

	private static void listadoAlumnos() {
		for (Alumno alumno : dao.obtenerTodos()) {
			mostrar(alumno);
		}
	}

	private static void saludoFinal() {
		System.out.println("Gracias por usar nuestro programa");
	}

	private static int pedirOpcion() {
		return Biblioteca.leerEntero("Dime la opción: ");
	}

	private static void mostrarOpciones() {
		mostrar("1. Listado");
		mostrar("2. Añadir alumno");
		mostrar("0. Salir");
	}

	private static void mostrar(Object o) {
		System.out.println(o);
	}

}
