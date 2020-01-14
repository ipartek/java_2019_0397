package com.ipartek.formacion.uf2218.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "Hola", urlPatterns = { "/hola" })
public class HolaMundo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		response.setContentType("text/plain");
		PrintWriter out = response.getWriter();
		out.println("Hola");
		out.println(new Date());
		
		String dato = request.getParameter("dato");
		String dato2 = request.getParameter("dato2");
		out.println("dato: " + dato);
		out.println("dato2: " + dato2);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//doGet(request, response);
		response.getWriter().write("Llama por el formulario, listillo");
	}

}
