package com.ipartek.formacion.jdbc;

import java.sql.*;

public class EjemploJDBC {
	private static final String usuario = "root";
	private static final String password = "admin";
	private static final String URL = "jdbc:mysql://localhost:3306/uf2213?serverTimezone=UTC"; // Cadena de conexión de
																								// MySQL

	public static void main(String[] args) throws SQLException {
		System.out.println("Conectando");

		Connection con = DriverManager.getConnection(URL, usuario, password);

		System.out.println("Conectado");

		System.out.println("LISTADO DE USUARIOS");

		String sql = "SELECT * FROM usuarios";

		Statement s = con.createStatement();

		ResultSet rs = s.executeQuery(sql);

		while (rs.next()) {
			System.out.println(rs.getString("email") + "\t" + rs.getString("password"));
		}

		System.out.println("BUSQUEDA DE USUARIO POR ID");

		Long id = 2L;

		sql = "SELECT * FROM usuarios WHERE id = ?";

		PreparedStatement ps = con.prepareStatement(sql);
		
		ps.setLong(1, id);
		
		rs = ps.executeQuery();

		if (rs.next()) {
			System.out.println(rs.getString("email") + "\t" + rs.getString("password"));
		} else {
			System.out.println("No encontrado");
		}

		System.out.println("INSERT");
		
		String email = "nuevo@email.net";
		String password = "nuevo";

		sql = "INSERT INTO usuarios (email, password) VALUES (?, ?)";
		
		ps = con.prepareStatement(sql);
		
		ps.setString(1, email);
		ps.setString(2, password);
		
		int numeroRegistrosModificados = ps.executeUpdate();
		
		if(numeroRegistrosModificados == 1) {
			System.out.println("Insertado correctamente");
		} else {
			System.err.println("No se ha insertado correctamente");
			System.err.println(numeroRegistrosModificados);
		}
		
		System.out.println("UPDATE");
		
		String emailAModificar = "nuevo@email.net";
		email = "modificado@email.net";
		password = "modificado";

		sql = "UPDATE usuarios SET email = ?, password = ? WHERE email = ?";
		
		ps = con.prepareStatement(sql);
		
		ps.setString(1, email);
		ps.setString(2, password);
		ps.setString(3, emailAModificar);
		
		numeroRegistrosModificados = ps.executeUpdate();
		
		if(numeroRegistrosModificados == 1) {
			System.out.println("Modificado correctamente");
		} else {
			System.err.println("No se ha modificado correctamente");
			System.err.println(numeroRegistrosModificados);
		}
		
		System.out.println("DELETE");
		
		sql = "DELETE FROM usuarios WHERE email = ?";
		
		ps = con.prepareStatement(sql);
		
		ps.setString(1, email);
		
		numeroRegistrosModificados = ps.executeUpdate();
		
		if(numeroRegistrosModificados == 1) {
			System.out.println("Borrado correctamente");
		} else {
			System.err.println("No se ha borrado correctamente");
			System.err.println(numeroRegistrosModificados);
		}
		
		ps.close();
		rs.close();
		s.close();
		con.close();
	}
}
