package com.ipartek.formacion.ejemplorestservlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.TreeMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

@WebServlet("/api/usuarios/*")
public class UsuariosApi extends HttpServlet {
	private static final String URL_ID_VALIDA = "^/\\d+$";

	private static final long serialVersionUID = 1L;

	private static TreeMap<Long, Usuario> usuarios = new TreeMap<>();

	static {
		usuarios.put(1L, new Usuario(1L, "javier@email.net", "javier"));
		usuarios.put(2L, new Usuario(2L, "pepe@email.net", "pepe"));
	}
	
	private static Gson gson = new Gson();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();

		String path = request.getPathInfo();

		if (path == null || path.equals("/")) {
			out.write(gson.toJson(usuarios.values()));
			// response.getWriter().write(new Gson().toJson(usuarios.values()));
			
			return;
		}

		if (path.matches(URL_ID_VALIDA)) {
			Long id = Long.parseLong(path.substring(1));

			Usuario usuario = usuarios.get(id);
			
			if(usuario == null) {
				response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			} else {
				out.write(gson.toJson(usuario));
			}

			return;
		}

		response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String json = extraerJSON(request);
		
		Usuario usuario = gson.fromJson(json, Usuario.class);
		
		Long id = usuarios.size() == 0 ? 1 : usuarios.lastKey() + 1;
		usuario.setId(id);
		
		usuarios.put(id, usuario);
		
		response.getWriter().write(gson.toJson(usuario));
		
		response.setStatus(HttpServletResponse.SC_CREATED);
	}


	private String extraerJSON(HttpServletRequest request) throws IOException {
		BufferedReader br = request.getReader();
		
		StringBuffer sb = new StringBuffer();
		String linea;
		
		while( ( linea = br.readLine() ) != null ) {
			sb.append(linea);
		}
		
		return sb.toString();
	}

	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getPathInfo();
		String json = extraerJSON(request);
		
		Usuario usuario = gson.fromJson(json, Usuario.class);
		
		if(path == null || !path.matches(URL_ID_VALIDA)) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}
		
		Long id = Long.parseLong(path.substring(1));
		
		if(id != usuario.getId()) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}
		
		usuarios.put(id, usuario);
		
		response.getWriter().write(gson.toJson(usuario));
	}

	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getPathInfo();
		
		if(path == null || !path.matches(URL_ID_VALIDA)) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}
		
		Long id = Long.parseLong(path.substring(1));
		
		usuarios.remove(id);
		
		response.getWriter().write("{}");
	}
}
