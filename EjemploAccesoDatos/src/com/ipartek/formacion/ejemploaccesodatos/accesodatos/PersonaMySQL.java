package com.ipartek.formacion.ejemploaccesodatos.accesodatos;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import com.ipartek.formacion.ejemploaccesodatos.entidades.Persona;

public class PersonaMySQL implements Crudable<Persona> {
	private final String sqlSelect = "SELECT * FROM personas";
	private final String sqlSelectById = "SELECT * FROM personas WHERE id=?";

	private final String sqlInsert = "INSERT INTO personas (nombre, apellidos) VALUES (?,?)";
	private final String sqlUpdate = "UPDATE personas SET nombre=?, apellidos=? WHERE id=?";
	private final String sqlDelete = "DELETE FROM personas WHERE id=?";

	private String url;
	private String usuario;
	private String password;

	// SINGLETON
	private PersonaMySQL(String url, String usuario, String password) {
		this.url = url;
		this.usuario = usuario;
		this.password = password;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new AccesoDatosException("No se ha encontrado el driver de MySQL");
		}
	}

	private static PersonaMySQL INSTANCIA = null;

	public static PersonaMySQL getInstancia() {
		try {
			if (INSTANCIA == null) {
				Properties configuracion = new Properties();
				configuracion.load(new FileInputStream("crudable.properties"));

				INSTANCIA = new PersonaMySQL(configuracion.getProperty("mysql.url"),
						configuracion.getProperty("mysql.usuario"), configuracion.getProperty("mysql.password"));
			}

			return INSTANCIA;
		} catch (FileNotFoundException e) {
			throw new AccesoDatosException("Fichero de configuración no encontrado", e);
		} catch (IOException e) {
			throw new AccesoDatosException("Fallo de lectura/escritura al fichero", e);
		}
	}
	// FIN SINGLETON

	private Connection getConexion() throws SQLException {
		return DriverManager.getConnection(url, usuario, password);
	}

	@Override
	public Iterable<Persona> getAll() {
		ArrayList<Persona> personas = new ArrayList<>();

		try (Connection con = getConexion()) {
			try (PreparedStatement ps = con.prepareStatement(sqlSelect)) {
				try (ResultSet rs = ps.executeQuery()) {
					while (rs.next()) {
						personas.add(new Persona(rs.getLong("id"), rs.getString("nombre"), rs.getString("apellidos")));
					}
				}
			}
			return personas;
		} catch (SQLException e) {
			throw new AccesoDatosException("Error al obtener todos los registros", e);
		}
	}

	@Override
	public Persona getById(Long id) {
		try (Connection con = getConexion()) {
			try (PreparedStatement ps = con.prepareStatement(sqlSelectById)) {
				ps.setLong(1, id);
				
				try (ResultSet rs = ps.executeQuery()) {
					if (rs.next()) {
						return new Persona(rs.getLong("id"), rs.getString("nombre"), rs.getString("apellidos"));
					} else {
						return null;
					}
				}
			}
		} catch (SQLException e) {
			throw new AccesoDatosException("Error al obtener el registro por su id", e);
		}
	}

	@Override
	public Persona insert(Persona persona) {
		try (Connection con = getConexion()) {
			try (PreparedStatement ps = con.prepareStatement(sqlInsert)) {
				ps.setString(1, persona.getNombre());
				ps.setString(2, persona.getApellidos());

				int numeroRegistrosModificados = ps.executeUpdate();

				if (numeroRegistrosModificados != 1) {
					throw new AccesoDatosException("Resultado no esperado en la INSERT: " + numeroRegistrosModificados);
				}

				persona.setId(null);

				return persona;
			}
		} catch (SQLException e) {
			throw new AccesoDatosException("Error al hacer la INSERT", e);
		}
	}

	@Override
	public Persona update(Persona persona) {
		try (Connection con = getConexion()) {
			try (PreparedStatement ps = con.prepareStatement(sqlUpdate)) {
				ps.setString(1, persona.getNombre());
				ps.setString(2, persona.getApellidos());
				ps.setLong(3, persona.getId());

				int numeroRegistrosModificados = ps.executeUpdate();

				if (numeroRegistrosModificados != 1) {
					throw new AccesoDatosException("Resultado no esperado en la UPDATE: " + numeroRegistrosModificados);
				}

				return persona;
			}
		} catch (SQLException e) {
			throw new AccesoDatosException("Error al hacer la UPDATE", e);
		}
	}

	@Override
	public Persona delete(Persona persona) {
		return delete(persona.getId());
	}

	@Override
	public Persona delete(Long id) {
		try (Connection con = getConexion()) {
			try (PreparedStatement ps = con.prepareStatement(sqlDelete)) {
				ps.setLong(1, id);

				int numeroRegistrosModificados = ps.executeUpdate();

				if (numeroRegistrosModificados != 1) {
					throw new AccesoDatosException("Resultado no esperado en la DELETE: " + numeroRegistrosModificados);
				}

				return null;
			}
		} catch (SQLException e) {
			throw new AccesoDatosException("Error al hacer DELETE", e);
		}
	}

}
