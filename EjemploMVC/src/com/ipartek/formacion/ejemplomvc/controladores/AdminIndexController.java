package com.ipartek.formacion.ejemplomvc.controladores;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ipartek.formacion.ejemplomvc.repositorios.VideoTreeMap;

// La ruta /admin/index se puede crear ya que podemos inventarnos cualquier ruta
// http://localhost:8080/EjemploMVC/admin/index
// NO hay ninguna carpeta admin
@WebServlet("/admin/index")
public class AdminIndexController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("videos", VideoTreeMap.getInstancia().obtenerTodos());
		request.getRequestDispatcher("/WEB-INF/vistas/admin/index.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
