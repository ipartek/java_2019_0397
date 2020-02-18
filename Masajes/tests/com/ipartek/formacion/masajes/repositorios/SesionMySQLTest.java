package com.ipartek.formacion.masajes.repositorios;

import java.util.Date;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SesionMySQLTest {

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
	void citaPeriodica() {
		SesionDao dao = SesionMySQL.getInstancia("jdbc:mysql://localhost:3306/masajes?serverTimezone=Europe/Madrid", "root", "admin");
		
		dao.citaPeriodicaSemanal(2, 2, 2, new Date(), 5);
	}
}
