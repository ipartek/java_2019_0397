package com.ipartek.formacion.ejemploaccesodatos.presentacionconsola;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import com.ipartek.formacion.ejemploaccesodatos.accesodatos.Crudable;
import com.ipartek.formacion.ejemploaccesodatos.accesodatos.FabricaCrudable;
import com.ipartek.formacion.ejemploaccesodatos.entidades.Persona;

public class PresentacionConsola {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		Properties configuracion = new Properties();
		configuracion.load(new FileInputStream("crudable.properties"));
		
		//DAO: Data Access Object
		Crudable<Persona> dao = FabricaCrudable.getInstancia(
				configuracion.getProperty("crudable"));
		
		System.out.println(dao.insert(new Persona(3L, "Nuevo", "Nuevez")));
		
		for(Persona persona: dao.getAll()) {
			System.out.println(persona);
		}
		
		// TODO Men� con opciones num�ricas (1. Listado, 2. Crear ... 0. Salir)
		// TODO Exportaci�n/Importaci�n Excel
		// TODO Guardar/Cargar
	}

}
