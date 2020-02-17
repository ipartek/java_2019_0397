package com.ipartek.formacion.masajes.repositorios;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.ipartek.formacion.masajes.modelos.Cliente;

class ClienteMySQLTest {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void insert() {
		Dao<Cliente> dao = ClienteMySQL.getInstancia("jdbc:mysql://localhost:3306/masajes?serverTimezone=Europe/Madrid", "root", "admin");
		Cliente cliente = new Cliente(null, "Nombre Cliente Nuevo","Apellidos Cliente Nuevo","12345678Z");
		
		Integer primerId = dao.insert(cliente);
		Integer segundoId = dao.insert(cliente);
		
		assertTrue(segundoId == primerId + 1);
	}

}
