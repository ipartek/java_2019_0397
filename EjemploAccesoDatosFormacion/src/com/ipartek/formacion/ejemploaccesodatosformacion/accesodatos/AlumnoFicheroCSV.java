package com.ipartek.formacion.ejemploaccesodatosformacion.accesodatos;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;

import com.ipartek.formacion.ejemploaccesodatosformacion.entidades.Alumno;

public class AlumnoFicheroCSV implements Backup<Alumno> {

	// SINGLETON
	private final static AlumnoFicheroCSV INSTANCIA = new AlumnoFicheroCSV();
	
	private AlumnoFicheroCSV() {}
	
	public static AlumnoFicheroCSV getInstancia() { return INSTANCIA; }
	// FIN SINGLETON
	
	private static final SimpleDateFormat FECHA_CORTA = new SimpleDateFormat("dd-MM-yyyy");

	@Override
	public void guardar(Iterable<Alumno> alumnos) {
		try (PrintWriter pw = new PrintWriter(new FileWriter("C:\\trabajos\\alumnos.csv"))) {
			
			pw.append("Id;Nombre;Apellidos;Dni;Fecha de nacimiento\n");
			
			for (Alumno alumno : alumnos) {
				pw.append(alumno.getId().toString());
				pw.append(';');
				pw.append(alumno.getNombre());
				pw.append(';');
				pw.append(alumno.getApellidos());
				pw.append(';');
				pw.append(alumno.getDni());
				pw.append(';');
				pw.append(FECHA_CORTA.format(alumno.getFechaNacimiento()));

				pw.append('\n');
			}

		} catch (IOException e) {
			throw new AccesoDatosException("Ha habido un error al guardar los datos", e);
		}
	}

	@Override
	public Iterable<Alumno> recuperar() {
		// TODO Auto-generated method stub
		return null;
	}

}
