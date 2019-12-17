package com.ipartek.formacion.ejemploaccesodatosformacion.presentacion;

import java.text.SimpleDateFormat;

import com.ipartek.formacion.ejemploaccesodatosformacion.accesodatos.AccesoDatosException;
import com.ipartek.formacion.ejemploaccesodatosformacion.accesodatos.AlumnoArrayList;
import com.ipartek.formacion.ejemploaccesodatosformacion.accesodatos.Dao;
import com.ipartek.formacion.ejemploaccesodatosformacion.entidades.Alumno;
import com.ipartek.formacion.ejemploaccesodatosformacion.entidades.EntidadesException;
import com.ipartek.formacion.ejemploaccesodatosformacion.utilidades.Biblioteca;

public class PresentacionConsola {

	private static final SimpleDateFormat FECHA_CORTA = new SimpleDateFormat("dd-MM-yyyy");

	private static final int OPCION_AGREGAR = 2;

	private static final int OPCION_LISTADO = 1;

	private static final int OPCION_SALIR = 0;

	private static final Dao<Alumno> dao = AlumnoArrayList.getInstancia();

	private static final int OPCION_MODIFICAR = 3;

	private static final int OPCION_BORRAR = 4;

	private static final int OPCION_BUSCAR = 5;

	public static void main(String[] args) {
		try {
			int opcion = 0;

			do {
				//BORRAR CONSOLA DE MSDOS (Sólo funciona en el MSDOS, no en Eclipse)
				//new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
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
		case OPCION_MODIFICAR:
			mostrar("MODIFICAR ALUMNO");
			modificarAlumno();
			break;
		case OPCION_BORRAR:
			mostrar("BORRAR ALUMNO");
			borrarAlumno();
			break;
		case OPCION_BUSCAR:
			mostrar("BUSCAR");
			buscarAlumno();
			break;
		default:
			mostrar("NO IMPLEMENTADO");
		}
	}

	private static void buscarAlumno() {
		Long id = (long) Biblioteca.leerEntero("Dime el ID a buscar: ");

		Alumno alumno = dao.obtenerPorId(id);

		if (alumno != null) {
			mostrarAlumno(alumno);
		} else {
			mostrar("No se ha encontrado el alumno");
		}
	}

	private static void mostrarAlumno(Alumno alumno) {
		System.out.println("                 ID: " + alumno.getId());
		System.out.println("             Nombre: " + alumno.getNombre());
		System.out.println("          Apellidos: " + alumno.getApellidos());
		System.out.println("                DNI: " + alumno.getDni());
		System.out.println("Fecha de nacimiento: " + 
				FECHA_CORTA.format(alumno.getFechaNacimiento()));
	}

	private static void borrarAlumno() {
		listadoAlumnos();

		// TODO: Hacer con long
		Long id = (long) Biblioteca.leerEntero("Dime el ID a borrar: ");

		try {
			dao.borrar(id);

			mostrar("Alumno borrado");
		} catch (AccesoDatosException e) {
			mostrar("ERROR: No se ha encontrado el alumno a borrar");
		}
	}

	private static void modificarAlumno() {
		listadoAlumnos();

		// TODO: Hacer con long
		Long id = (long) Biblioteca.leerEntero("Dime el ID a modificar: ");

		if(dao.obtenerPorId(id) == null) {
			mostrar("ERROR: No se ha encontrado el alumno a modificar");
			return;
		}
		
		Alumno alumno = pedirDatosAlumno();

		alumno.setId(id);
		
		try {
			dao.modificar(alumno);
			mostrar("Alumno modificado");
		} catch (AccesoDatosException e) {
			mostrar("ERROR: No se ha encontrado el alumno a modificar");
		}
	}

	private static void agregarAlumno() {
		Alumno alumno = pedirDatosAlumno();

		dao.agregar(alumno);
	}

	private static Alumno pedirDatosAlumno() {
		Alumno alumno = new Alumno();

		final int NOMBRE = 0;
		final int APELLIDOS = 1;
		final int DNI = 2;
		final int FECHA = 3;

		int campo = 0;

		while (campo <= FECHA) {
			try {
				switch (campo) {
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
		return alumno;
	}

	private static void listadoAlumnos() {
		// TODO: Usar columnas de tamaño fijo
		mostrarCabeceraListado();
		for (Alumno alumno : dao.obtenerTodos()) {
			mostrarAlumnoFila(alumno);
		}
	}

	private static void mostrarCabeceraListado() {
		System.out.print("ID");
		System.out.print('\t');
		System.out.print("NOMBRE");
		System.out.print('\t');
		System.out.print("APELLIDOS");
		System.out.print('\t');
		System.out.print("DNI");
		System.out.print("\t\t");
		System.out.print("FECHA DE NACIMIENTO");
		System.out.println();
	}

	private static void mostrarAlumnoFila(Alumno alumno) {
		System.out.print(alumno.getId());
		System.out.print('\t');
		System.out.print(alumno.getNombre());
		System.out.print('\t');
		System.out.print(alumno.getApellidos());
		System.out.print('\t');
		System.out.print(alumno.getDni());
		System.out.print('\t');
		System.out.print(
				FECHA_CORTA.format(alumno.getFechaNacimiento()));
		System.out.println();
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
		mostrar(OPCION_MODIFICAR + ". Modificar alumno");
		mostrar(OPCION_BORRAR + ". Borrar alumno");
		mostrar(OPCION_BUSCAR + ". Buscar alumno");
		mostrar(OPCION_SALIR + ". Salir");
	}

	private static void mostrar(Object o) {
		System.out.println(o);
	}

}
