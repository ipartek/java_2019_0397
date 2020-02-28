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

		Long id = null;

		try {
			id = extraerId(request);
		} catch (Exception e) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}

		if (id == null) {
			out.write(gson.toJson(usuarios.values()));
			// response.getWriter().write(new Gson().toJson(usuarios.values()));
			return;
		}

		Usuario usuario = usuarios.get(id);

		if (usuario == null) {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
		} else {
			out.write(gson.toJson(usuario));
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			if (extraerId(request) != null) {
				throw new Exception();
			}
		} catch (Exception e) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}

		String json = extraerJSON(request);

		Usuario usuario = gson.fromJson(json, Usuario.class);

		Long id = usuarios.size() == 0 ? 1 : usuarios.lastKey() + 1;
		usuario.setId(id);

		usuarios.put(id, usuario);

		response.getWriter().write(gson.toJson(usuario));

		response.setStatus(HttpServletResponse.SC_CREATED);
	}

	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String json = extraerJSON(request);

		Usuario usuario = gson.fromJson(json, Usuario.class);

		Long id = null;

		try {
			id = extraerId(request);

			if (id == null) {
				throw new Exception();
			}
		} catch (Exception e) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}

		if (id != usuario.getId()) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}

		usuarios.put(id, usuario);

		response.getWriter().write(gson.toJson(usuario));
	}

	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Long id = null;

		try {
			id = extraerId(request);

			if (id == null) {
				throw new Exception();
			}
		} catch (Exception e) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}

		usuarios.remove(id);

		response.getWriter().write("{}");
	}

	private static String extraerJSON(HttpServletRequest request) throws IOException {
		BufferedReader br = request.getReader();

		StringBuffer sb = new StringBuffer();
		String linea;

		while ((linea = br.readLine()) != null) {
			sb.append(linea);
		}

		return sb.toString();
	}

	private static Long extraerId(HttpServletRequest request) {
		String path = request.getPathInfo();

		if (path == null || path.equals("/")) {
			return null;
		}

		if (!path.matches(URL_ID_VALIDA)) {
			throw new RuntimeException("URL de petición no válida");
		}

		return Long.parseLong(path.substring(1));
	}
}
