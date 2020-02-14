package com.ipartek.formacion.masajes.controladores;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ipartek.formacion.masajes.modelos.Sesion;

@WebServlet("/index")
public class IndexController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		try {
			Iterable<Sesion> sesiones = Globales.daoSesion.getAll();
			
			request.setAttribute("sesiones", sesiones);
			
			request.getRequestDispatcher("/WEB-INF/vistas/index.jsp").forward(request, response);
		} catch (Exception e) {
			request.setAttribute("alertatexto", e.getMessage());
			request.setAttribute("alertanivel", "danger");

			e.printStackTrace();

			request.getRequestDispatcher("/WEB-INF/vistas/index.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
