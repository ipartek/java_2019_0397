package com.ipartek.formacion.ejemploaccesodatosformacion.presentacion;

import com.ipartek.formacion.ejemploaccesodatosformacion.accesodatos.AlumnoArrayList;
import com.ipartek.formacion.ejemploaccesodatosformacion.accesodatos.Dao;
import com.ipartek.formacion.ejemploaccesodatosformacion.entidades.Alumno;
import com.ipartek.formacion.ejemploaccesodatosformacion.entidades.EntidadesException;
import com.ipartek.formacion.ejemploaccesodatosformacion.utilidades.Biblioteca;

public class PresentacionConsola {

	// TODO Podría hacerse con enumeraciones
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
			
			return;
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
			mostrar("AÑADIR ALUMNO");
			agregarAlumno();
			break;
		default:
			mostrar("NO IMPLEMENTADO");
		}
	}

	private static void agregarAlumno() {
		Alumno alumno = new Alumno();
		
		final int NOMBRE = 0;
		final int APELLIDOS = 1;
		final int DNI = 2;
		final int FECHA = 3;
		
		int campo = 0;
		
		while(campo <= FECHA) {
			try {
				switch(campo) {
				case NOMBRE: 
					alumno.setNombre(Biblioteca.leerLinea("Nombre: "));
					break;
				case APELLIDOS:
					alumno.setApellidos(Biblioteca.leerLinea("Apellidos: "));
					break;
				case DNI:
					alumno.setDni(Biblioteca.leerLinea("DNI: "));
					break;
				case FECHA:
					alumno.setFechaNacimiento(Biblioteca.leerDate("Fecha de nacimiento: "));
					break;
				default:
					throw new PresentacionException("CAMPO NO DEFINIDO");	
				}
				
				campo++;
				
			} catch (EntidadesException e) {
				System.out.println(e.getMessage());
			}
		}
		
		dao.agregar(alumno);
	}

	private static void listadoAlumnos() {
		for (Alumno alumno : dao.obtenerTodos()) {
			// TODO: Mejorar visualización de registros de alumnos
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
		mostrar(OPCION_LISTADO + ". Listado");
		mostrar(OPCION_AGREGAR + ". Añadir alumno");
		mostrar(OPCION_SALIR + ". Salir");
	}

	private static void mostrar(Object o) {
		System.out.println(o);
	}

}
