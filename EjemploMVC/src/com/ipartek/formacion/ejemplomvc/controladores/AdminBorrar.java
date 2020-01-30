package com.ipartek.formacion.ejemplomvc.controladores;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ipartek.formacion.ejemplomvc.modelos.Video;
import com.ipartek.formacion.ejemplomvc.repositorios.Dao;

@WebServlet("/admin/borrar")
public class AdminBorrar extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		
		@SuppressWarnings("unchecked")
		Dao<Video> dao = (Dao<Video>) getServletContext().getAttribute("dao");
		dao.borrar(Long.parseLong(id));
		
		response.sendRedirect(request.getContextPath() + "/admin/index");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
