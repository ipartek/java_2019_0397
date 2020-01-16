package com.ipartek.formacion.ejemplomvc.controladores;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ipartek.formacion.ejemplomvc.modelos.Video;
import com.ipartek.formacion.ejemplomvc.repositorios.VideoTreeMap;

@WebServlet("/admin/video")
public class AdminVideoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String op = request.getParameter("op");
		
		if(id != null) {
			Video video = VideoTreeMap.getInstancia().obtenerPorId(Long.parseLong(id));
			request.setAttribute("video", video);
		}
		
		request.setAttribute("op", op);
		
		request.getRequestDispatcher("/WEB-INF/vistas/admin/video.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(request.getParameter("op"));
	}

}
