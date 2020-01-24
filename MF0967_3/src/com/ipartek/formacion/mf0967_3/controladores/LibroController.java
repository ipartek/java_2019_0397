package com.ipartek.formacion.mf0967_3.controladores;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ipartek.formacion.mf0967_3.modelos.Libro;
import com.ipartek.formacion.mf0967_3.repositorios.Dao;
import com.ipartek.formacion.mf0967_3.repositorios.LibroTreeMap;

@WebServlet("/admin/libro")
public class LibroController extends HttpServlet {
	private static final String ADMIN_LIBRO_JSP = "/WEB-INF/vistas/admin/libro.jsp";
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String op = request.getParameter("op");
		String id = request.getParameter("id");
		
		if(id != null && id.trim().length() > 0) {
			Dao<Libro> dao = LibroTreeMap.getInstancia();
			
			Libro libro = dao.obtenerPorId(Long.parseLong(id));
			
			request.setAttribute("libro", libro);
		}
		
		request.setAttribute("op", op);
		request.setAttribute("primeravez", true);
		request.getRequestDispatcher(ADMIN_LIBRO_JSP).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
