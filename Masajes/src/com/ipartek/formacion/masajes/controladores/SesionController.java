package com.ipartek.formacion.masajes.controladores;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

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

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");

		if (id != null) {
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

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		String idCliente = request.getParameter("cliente");
		String idTrabajador = request.getParameter("trabajador");
		String idServicio = request.getParameter("servicio");
		String fecha = request.getParameter("fecha");
		String resena = request.getParameter("resena");
		String calificacion = request.getParameter("calificacion");

		Cliente cliente;
		Trabajador trabajador;
		Servicio servicio;
		Sesion sesion;
		
		try {
			// Partes
			cliente = new Cliente(Integer.parseInt(idCliente), null, null, null);

			trabajador = new Trabajador(Integer.parseInt(idTrabajador), null, null, null);

			servicio = new Servicio(Integer.parseInt(idServicio), null, null);

			// Completo

			sesion = new Sesion(null, cliente, trabajador, servicio,
					new SimpleDateFormat("yyyy-MM-dd'T'HH:mm").parse(fecha), resena, calificacion);
			
			if (id == null || id.trim().length() == 0) {
				Globales.daoSesion.insert(sesion);
			} else {
				sesion.setId(Integer.parseInt(id));
				Globales.daoSesion.update(sesion);
			}

		} catch (NumberFormatException | ParseException e) {
			throw new ServletException("Formato de ids o fecha incorrectos", e);
		}

		
		response.sendRedirect(request.getContextPath() + "/");
	}

}
