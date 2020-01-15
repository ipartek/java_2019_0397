package com.ipartek.formacion.ejemploaccesodatosformacion.accesodatos;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.ipartek.formacion.ejemploaccesodatosformacion.entidades.Alumno;

public class AlumnoFicheroObjetos implements Backup<Alumno> {

	private static final String FICHERO = "C:\\trabajos\\alumnos.dat";
	
	private AlumnoFicheroObjetos() {}
	
	private static final AlumnoFicheroObjetos INSTANCIA = new AlumnoFicheroObjetos();

	public static AlumnoFicheroObjetos getInstancia() { return INSTANCIA; }
	
	@Override
	public void guardar(Iterable<Alumno> alumnos) {
		try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FICHERO))){
			oos.writeObject(alumnos);
		} catch (FileNotFoundException e) {
			throw new AccesoDatosException("No se ha podido escribir el fichero", e);
		} catch (IOException e) {
			throw new AccesoDatosException("Ha habido un error al escribir en el fichero", e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public Iterable<Alumno> recuperar() {
		try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FICHERO))){
			return (Iterable<Alumno>) ois.readObject();
		} catch (FileNotFoundException e) {
			throw new AccesoDatosException("No se ha podido leer el fichero", e);
		} catch (IOException e) {
			throw new AccesoDatosException("Ha habido un error al leer el fichero", e);
		} catch (ClassNotFoundException e) {
			throw new AccesoDatosException("No se ha podido adaptar la información a la aplicación", e);
		}
	}

	

}
