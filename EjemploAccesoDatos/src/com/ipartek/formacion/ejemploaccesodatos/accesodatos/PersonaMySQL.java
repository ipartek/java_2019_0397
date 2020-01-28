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
	private String sqlSelect = "SELECT * FROM personas";
	private String sqlInsert = "INSERT INTO personas (nombre, apellidos) VALUES (?,?)";

	private String url;
	private String usuario;
	private String password;

	// SINGLETON
	private PersonaMySQL(String url, String usuario, String password) {
		this.url = url;
		this.usuario = usuario;
		this.password = password;
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
		throw new UnsupportedOperationException("NO ESTÁ IMPLEMENTADO");
	}

	@Override
	public Persona insert(Persona persona) {
		try (Connection con = getConexion()) {
			try (PreparedStatement ps = con.prepareStatement(sqlInsert)) {
				ps.setString(1, persona.getNombre());
				ps.setString(2, persona.getApellidos());
				
				int numeroRegistrosModificados = ps.executeUpdate();
				
				if(numeroRegistrosModificados != 1) {
					throw new AccesoDatosException("Resultado no esperado en la INSERT: " +
							numeroRegistrosModificados);
				}
				
				persona.setId(null);
				
				return persona;
			}
		} catch (SQLException e) {
			throw new AccesoDatosException("Error al obtener todos los registros", e);
		}
	}

	@Override
	public Persona update(Persona t) {
		throw new UnsupportedOperationException("NO ESTÁ IMPLEMENTADO");
	}

	@Override
	public Persona delete(Persona t) {
		throw new UnsupportedOperationException("NO ESTÁ IMPLEMENTADO");
	}

	@Override
	public Persona delete(Long id) {
		throw new UnsupportedOperationException("NO ESTÁ IMPLEMENTADO");
	}

}
