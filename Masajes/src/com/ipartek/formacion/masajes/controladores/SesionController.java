package com.ipartek.formacion.masajes.controladores;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ipartek.formacion.masajes.modelos.Cliente;
import com.ipartek.formacion.masajes.modelos.Servicio;
import com.ipartek.formacion.masajes.modelos.Sesion;
import com.ipartek.formacion.masajes.modelos.Trabajador;

@WebServlet("/sesion")
public class SesionController extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		
		if(id != null) {
			Integer idInteger = new Integer(id);
			
			Sesion sesion = Globales.daoSesion.getById(idInteger);
			
			request.setAttribute("sesion", sesion);
		}
		
		Iterable<Cliente> clientes = Globales.daoClientes.getAll();
		Iterable<Trabajador> trabajadores = Globales.daoTrabajadores.getAll();
		Iterable<Servicio> servicios = Globales.daoServicios.getAll();
		
		request.setAttribute("clientes", clientes);
		request.setAttribute("trabajadores", trabajadores);
		request.setAttribute("servicios", servicios);
		
		request.getRequestDispatcher("/WEB-INF/vistas/sesion.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect(request.getContextPath() + "/");
	}

}
