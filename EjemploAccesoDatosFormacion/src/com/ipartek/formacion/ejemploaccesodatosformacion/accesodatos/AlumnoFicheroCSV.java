package com.ipartek.formacion.ejemploaccesodatosformacion.accesodatos;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

import com.ipartek.formacion.ejemploaccesodatosformacion.entidades.Alumno;

public class AlumnoFicheroCSV implements Backup<Alumno> {

	private static final String FICHERO = "C:\\trabajos\\alumnos.csv";
	// SINGLETON
	private final static AlumnoFicheroCSV INSTANCIA = new AlumnoFicheroCSV();
	
	private AlumnoFicheroCSV() {}
	
	public static AlumnoFicheroCSV getInstancia() { return INSTANCIA; }
	// FIN SINGLETON
	
	private static final SimpleDateFormat FECHA_CORTA = new SimpleDateFormat("dd-MM-yyyy");

	@Override
	public void guardar(Iterable<Alumno> alumnos) {
		try (PrintWriter pw = new PrintWriter(new FileWriter(FICHERO))) {
			
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
		try(Scanner s = new Scanner(new FileReader(FICHERO))){
			String linea;
			ArrayList<Alumno> alumnos = new ArrayList<>();
			
			if(s.hasNextLine()) {
				s.nextLine();
			}
			
			while(s.hasNextLine()) {
				linea = s.nextLine();
				
				String[] datos = linea.split(";");
				
				alumnos.add(new Alumno(
						null, //Long.parseLong(datos[0]), // No incluyo el id porque no lo permito en agregar 
						datos[1], 
						datos[2], 
						datos[3], 
						FECHA_CORTA.parse(datos[4]))
				);
			}
			
			return alumnos;
		} catch (FileNotFoundException e) {
			throw new AccesoDatosException("No se ha encontrado el fichero", e);
		} catch (NumberFormatException e) {
			throw new AccesoDatosException("El id no es un número o hay fallo en la fecha", e);
		} catch (ParseException e) {
			throw new AccesoDatosException("No se ha podido traducir la fecha", e);
		}
	}

}
