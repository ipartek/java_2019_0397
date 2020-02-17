package com.ipartek.formacion.masajes.controladores.test;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ipartek.formacion.masajes.modelos.Cliente;
import com.ipartek.formacion.masajes.repositorios.ClienteMySQL;
import com.ipartek.formacion.masajes.repositorios.Dao;

@WebServlet("/ClienteMySQLTest")
public class ClienteMySQLTest extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Dao<Cliente> dao = ClienteMySQL.getInstancia("jdbc/masajes");
		
		Cliente cliente = new Cliente(null, "Nombre Cliente Nuevo","Apellidos Cliente Nuevo","12345678Z");
		
		Integer id = dao.insert(cliente);
		
		response.getWriter().append("INSERTADO CON EL ID ").append(id.toString());
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
