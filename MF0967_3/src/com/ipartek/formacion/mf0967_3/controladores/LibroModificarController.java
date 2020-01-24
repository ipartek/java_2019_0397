package com.ipartek.formacion.mf0967_3.controladores;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ipartek.formacion.mf0967_3.modelos.Libro;
import com.ipartek.formacion.mf0967_3.modelos.Mensaje;
import com.ipartek.formacion.mf0967_3.repositorios.Dao;
import com.ipartek.formacion.mf0967_3.repositorios.LibroTreeMap;

@WebServlet("/admin/libro/modificar")
public class LibroModificarController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final String ADMIN_LIBRO_JSP = "/WEB-INF/vistas/admin/libro.jsp";

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

		String op = request.getParameter("op");

		String id = request.getParameter("id");
		String nombre = request.getParameter("nombre");
		String precio = request.getParameter("precio");
		String descuento = request.getParameter("descuento");
		String autor = request.getParameter("autor");
		String imagen = request.getParameter("imagen");

		Libro libro = new Libro(id, nombre, precio, descuento, autor, imagen);

		String ruta = request.getServletContext().getRealPath("/imgs/") + libro.getImagen();

		System.out.println(ruta);

		boolean existeImagen = new File(ruta).exists();

		if (!existeImagen) {
			libro.setImagen(null); // Pongo la imagen por defecto
		}

		Mensaje mensaje;

		request.setAttribute("primeravez", false);

		if (libro.isCorrecto()) {
			Dao<Libro> dao = LibroTreeMap.getInstancia();

			dao.modificar(libro);

			mensaje = new Mensaje("Libro modificado correctamente", Mensaje.Nivel.INFORMATIVO);

			request.getSession().setAttribute("mensaje", mensaje);

			response.sendRedirect(request.getContextPath() + "/admin/listado");
		} else {
			request.setAttribute("op", op);
			request.setAttribute("libro", libro);

			mensaje = new Mensaje("El libro no se ha podido modificar. Revisa los errores.", Mensaje.Nivel.ERROR);

			request.setAttribute("mensaje", mensaje);

			request.getRequestDispatcher(ADMIN_LIBRO_JSP).forward(request, response);
		}
	}

}
