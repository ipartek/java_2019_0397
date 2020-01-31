package com.ipartek.formacion.ejemplodynamicwebprojectmaven;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/prueba")
public class Controlador extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] arr = { "Uno", "Dos", "Tres" };

        request.setAttribute("datos", arr);
        request.getRequestDispatcher("/index.jsp").forward(request, response);
	}

}
