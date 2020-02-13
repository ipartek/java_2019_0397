package com.ipartek.formacion.masajes.controladores;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ipartek.formacion.masajes.modelos.Sesion;
import com.ipartek.formacion.masajes.repositorios.Dao;
import com.ipartek.formacion.masajes.repositorios.FabricaDao;
import com.ipartek.formacion.masajes.repositorios.FabricaDaoProperties;

@WebServlet("/index")
public class IndexController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String pathConfiguracion = getServletContext().getRealPath("/WEB-INF/") + "dao.properties";

		FabricaDao fabricaDao = FabricaDaoProperties.getInstancia(pathConfiguracion);
		
		Dao<Sesion> daoSesion = fabricaDao.getSesionDao();
		
		Iterable<Sesion> sesiones = daoSesion.getAll();
		
		request.setAttribute("sesiones", sesiones);
		
		request.getRequestDispatcher("/WEB-INF/vistas/index.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
