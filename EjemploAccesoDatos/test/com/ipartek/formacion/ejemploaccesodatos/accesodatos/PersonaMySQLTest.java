package com.ipartek.formacion.ejemploaccesodatos.accesodatos;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Properties;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.ipartek.formacion.ejemploaccesodatos.entidades.Persona;

public class PersonaMySQLTest {

	private static Crudable<Persona> dao = null;
	
	private static Statement s = null;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		dao = PersonaMySQL.getInstancia();
		
		Properties configuracion = new Properties();
		configuracion.load(new FileInputStream("crudable.properties"));
		
		Connection con = DriverManager.getConnection(
				configuracion.getProperty("mysql.url"),
				configuracion.getProperty("mysql.usuario"),
				configuracion.getProperty("mysql.password"));
		
		s = con.createStatement();
		
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		s.close();
	}

	@BeforeEach
	void setUp() throws Exception {
		s.executeUpdate("TRUNCATE personas");
		
		s.executeUpdate("INSERT INTO personas (nombre, apellidos) VALUES ('Primero', 'Primerez'), ('Segundo', 'Segundez')");
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testGetInstancia() {
		assertNotNull(PersonaMySQL.getInstancia());	
	}

	@Test
	void testGetAll() {
		for(Persona persona: dao.getAll()) {
			switch(persona.getId().intValue()) {
			case 1: assertEquals(new Persona(1L, "Primero", "Primerez"), persona); break;
			case 2: assertEquals(new Persona(2L, "Segundo", "Segundez"), persona); break;
			default: fail("Se ha encontrado un registro no esperado: " + persona);
			}
		}
	}

	@Test
	void testGetById() {
		Persona persona = dao.getById(-1L);
		assertNull(persona);
		persona = dao.getById(2L);
		assertNotNull(persona);
		assertEquals(new Persona(2L, "Segundo", "Segundez"), persona);
	}

	@Test
	void testInsert() {
		Persona insertada = new Persona(null, "Desde", "Test");
		dao.insert(insertada);
		Persona persona = dao.getById(3L);
		
		insertada.setId(3L);
		
		assertNotNull(persona, "Fila no insertada");
		assertEquals(insertada, persona, "Fila no concuerda con lo esperado");
	}

	@Test
	void testUpdate() {
		Persona modificada = new Persona(1L, "Modificado", "Desde test");
		dao.update(modificada);
		Persona persona = dao.getById(1L);
		
		assertEquals(modificada, persona, "Fila no concuerda con lo esperado");

		final Persona modificadaErronea = new Persona(5L, "Modificado", "Desde test");
		
		// Le pasamos el código "dao.update(modificadaErronea) a la función assertThrows
		// para que assertThrows ejecute dicho código y capte las excepciones lanzadas.
		assertThrows(AccesoDatosException.class, () -> dao.update(modificadaErronea) );
		
		/*
		//OPCION 2
		assertThrows(AccesoDatosException.class, new Executable() {

			@Override
			public void execute() throws Throwable {
				dao.update(new Persona(5L, "Modificado", "Desde test"));
			}});
		
		//OPCION 1
		assertThrows(AccesoDatosException.class, new MiMetodo());
		*/
	}

	/*
	//OPCION 1
	class MiMetodo implements Executable {

		@Override
		public void execute() throws Throwable {
			dao.update(new Persona(5L, "Modificado", "Desde test"));
		}
		
	}
	*/
	
	@Test
	void testDeletePersona() {
		assertNotNull(dao.getById(2L));
		dao.delete(new Persona(2L, null, null));
		assertNull(dao.getById(2L));
	}

	@Test
	void testDeleteLong() {
		assertNotNull(dao.getById(2L));
		dao.delete(2L);
		assertNull(dao.getById(2L));
	}
}
