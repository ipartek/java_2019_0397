package com.ipartek.formacion.mf0967_3.controladores;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ipartek.formacion.mf0967_3.modelos.Libro;
import com.ipartek.formacion.mf0967_3.repositorios.LibroTreeMap;

@WebServlet("/admin/listado")
public class AdminListadoController extends HttpServlet {
	private static final String ADMIN_LISTADO_JSP = "/WEB-INF/vistas/admin/listado.jsp";
	
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Iterable<Libro> libros = LibroTreeMap.getInstancia().obtenerTodos();
		
		request.setAttribute("libros", libros);
		
		request.getRequestDispatcher(ADMIN_LISTADO_JSP).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
