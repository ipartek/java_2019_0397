package com.ipartek.formacion.mf0967_3.controladores;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ipartek.formacion.mf0967_3.modelos.Libro;
import com.ipartek.formacion.mf0967_3.modelos.Mensaje;
import com.ipartek.formacion.mf0967_3.repositorios.AccesoDatosException;
import com.ipartek.formacion.mf0967_3.repositorios.Dao;
import com.ipartek.formacion.mf0967_3.repositorios.LibroTreeMap;

@WebServlet("/admin/borrar")
public class LibroBorrarController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		
		Dao<Libro> dao = LibroTreeMap.getInstancia();
		
		Mensaje mensaje;
		
		try {
			dao.borrar(Long.parseLong(id));
			mensaje = new Mensaje(
					"Libro borrado correctamente", 
					Mensaje.Nivel.INFORMATIVO);
		} catch (AccesoDatosException e) {
			mensaje = new Mensaje(
				"No se ha encontrado el libro a borrar",
				Mensaje.Nivel.ALERTA);
		} catch(NumberFormatException e) {
			mensaje = new Mensaje(
					"No se ha recibido id válido para borrar",
					Mensaje.Nivel.ERROR);
		}
		
		request.getSession().setAttribute("mensaje", mensaje);

		response.sendRedirect(request.getContextPath() + "/admin/listado");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
