package com.ipartek.formacion.uf2218.controladores;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ipartek.formacion.ejemploaccesodatosformacion.accesodatos.AlumnoArrayList;
import com.ipartek.formacion.ejemploaccesodatosformacion.accesodatos.Dao;
import com.ipartek.formacion.ejemploaccesodatosformacion.entidades.Alumno;

@WebServlet("/alumno")
public class AlumnoController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// CONTROLADOR
		// 1. Recoger datos de la petición
		String id = request.getParameter("id");
		String nombre = request.getParameter("nombre");
		String apellidos = request.getParameter("apellidos");
		String dni = request.getParameter("dni");
		String fechaNacimiento = request.getParameter("fechanacimiento");

		// Verificación de datos recibidos (vacíos o no)
		if (id == null || nombre == null || apellidos == null || dni == null || fechaNacimiento == null) {
			throw new RuntimeException("Faltan datos para la petición");
		}

		// 2. Crear un objeto del modelo (entidades) desde los datos
		Alumno alumno = null;
		
		try {
			alumno = new Alumno(
					id == "" ? null : Long.parseLong(id), 
					nombre, 
					apellidos, 
					dni, 
					new SimpleDateFormat("yyyy-MM-dd").parse(fechaNacimiento)
			);
			
		} catch (NumberFormatException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// 3. Llamar a la lógica de negocio
		Dao<Alumno> dao = AlumnoArrayList.getInstancia();
		
		if(alumno.getId() == null) {
			// AGREGAR
			dao.agregar(alumno);
		} else {
			// MODIFICAR
			dao.modificar(alumno);
		}
		
		request.getRequestDispatcher("alumnos").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
