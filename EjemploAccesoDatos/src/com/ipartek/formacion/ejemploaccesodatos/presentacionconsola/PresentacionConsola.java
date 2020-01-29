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
		
		System.out.println(dao.insert(new Persona(null, "Creado", "Ahora")));
		
		mostrarPersonas(dao);
		
		System.out.println(dao.getById(1L));
		
		mostrarPersonas(dao);
		
		System.out.println(dao.update(new Persona(1L, "Pepito", "de los Palotes")));
		
		mostrarPersonas(dao);
		
		System.out.println(dao.delete(1L));
		
		mostrarPersonas(dao);
	}

	private static void mostrarPersonas(Crudable<Persona> dao) {
		System.out.println("------------");
		for(Persona persona: dao.getAll()) {
			System.out.println(persona);
		}
		System.out.println("------------");
	}

}
