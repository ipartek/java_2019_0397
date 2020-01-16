package com.ipartek.formacion.ejemplomvc.controladores;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ipartek.formacion.ejemplomvc.modelos.Video;
import com.ipartek.formacion.ejemplomvc.repositorios.Dao;
import com.ipartek.formacion.ejemplomvc.repositorios.VideoTreeMap;

@WebServlet("/admin/video")
public class AdminVideoController extends HttpServlet {
	private static final String VIDEO_JSP = "/WEB-INF/vistas/admin/video.jsp";
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		String op = request.getParameter("op");

		if (id != null) {
			Video video = VideoTreeMap.getInstancia().obtenerPorId(Long.parseLong(id));
			request.setAttribute("video", video);
		}

		request.setAttribute("op", op);
		request.setAttribute("primeravez", true);
		request.getRequestDispatcher(VIDEO_JSP).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// NECESARIO EN TODAS LAS RECEPCIONES DE DATOS DE FORMULARIO
		// SI QUEREMOS QUE SE GUARDEN CORRECTAMENTE LOS CARACTERES
		// Ññá...
		request.setCharacterEncoding("UTF-8");
		
		Dao<Video> dao = VideoTreeMap.getInstancia();

		String op = request.getParameter("op");
		String id = request.getParameter("id");
		String nombre = request.getParameter("nombre");
		String url = request.getParameter("url");

		Video video = null;

		switch (op) {
		case "agregar":
			video = new Video(nombre, url);
			if (video.isCorrecto()) {
				dao.agregar(video);
			}

			break;
		case "modificar":
			video = new Video(Long.parseLong(id), nombre, url);
			if (video.isCorrecto()) {
				dao.modificar(video);
			}

			break;
		default:
			throw new RuntimeException("Operación no reconocida");
		}

		// response.getWriter().println(video);

		// request.getRequestDispatcher("/admin/index").forward(request, response);

		if (video.isCorrecto()) {

			HttpSession session = request.getSession();
			session.setAttribute("alertatexto", "La operación " + op + " se ha realizado correctamente");
			session.setAttribute("alertanivel", "success");
			// A diferencia del requestDispatcher, el response.sendRedirect lo que hace
			// es pedir al navegador que cargue la URL que le enviamos
			// SE PIERDEN TODAS LAS VARIABLES DE REQUEST
			response.sendRedirect(request.getContextPath() + "/admin/index");
		} else {
			request.setAttribute("alertatexto", "Hay un error en el formulario. Revise los datos.");
			request.setAttribute("alertanivel", "danger");
			request.setAttribute("primeravez", false);
			request.setAttribute("op", op);
			request.setAttribute("video", video);
			request.getRequestDispatcher(VIDEO_JSP).forward(request, response);
		}
	}

}
