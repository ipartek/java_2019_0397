package com.ipartek.formacion.masajes.repositorios;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.ipartek.formacion.masajes.modelos.Cliente;

public class ClienteMySQL implements Dao<Cliente> {

	private static final String SQL_GET_ALL = "SELECT * FROM clientes";
		
	private final String url, usuario, password;

	// "SINGLETON"
	private ClienteMySQL(String url, String usuario, String password) {
		this.url = url;
		this.usuario = usuario;
		this.password = password;
	}

	private static ClienteMySQL instancia;

	/**
	 * Se usará para inicializar la instancia
	 * 
	 * @param url
	 * @param usuario
	 * @param password
	 * @return La instancia
	 */
	public static ClienteMySQL getInstancia(String url, String usuario, String password) {
		// Si no existe la instancia...
		if (instancia == null) {
			// ...la creamos
			instancia = new ClienteMySQL(url, usuario, password);
			// Si existe la instancia, pero sus valores no concuerdan...
		} else if (!instancia.url.equals(url) || !instancia.usuario.equals(usuario)
				|| !instancia.password.contentEquals(password)) {
			// ...lanzar un error
			throw new RepositoriosException("No se pueden cambiar los valores de la instancia una vez inicializada");
		}

		// Devolver la instancia recién creada o la existente (cuyos datos coinciden con
		// los que tiene)
		return instancia;
	}

	/**
	 * Se usará para recuperar la instancia ya existente
	 * 
	 * @return devuelve la instancia ya existente
	 */
	public static ClienteMySQL getInstancia() {
		// Si no existe la instancia...
		if (instancia == null) {
			// ...no se puede obtener porque no sabemos los datos de URL, usuario y password
			throw new RepositoriosException("Necesito que me pases URL, usuario y password");
		}

		// Si ya existe, se devuelve
		return instancia;
	}
	// FIN "SINGLETON"

	private Connection getConexion() {
		try {
			new com.mysql.cj.jdbc.Driver();
			return DriverManager.getConnection(url, usuario, password);
		} catch (SQLException e) {
			throw new RepositoriosException(
					"No se ha podido conectar a la base de datos " + url + ":" + usuario + ":" + password, e);
		}
	}

	@Override
	public Iterable<Cliente> getAll() {
		try (Connection con = getConexion()) {
			try (Statement s = con.createStatement()) {
				try (ResultSet rs = s.executeQuery(SQL_GET_ALL)) {
					ArrayList<Cliente> clientes = new ArrayList<>();

					Cliente cliente;
					
					while (rs.next()) {
						cliente = new Cliente(rs.getInt("idclientes"), rs.getString("nombre"),
								rs.getString("apellidos"), rs.getString("dni"));

						clientes.add(cliente);
					}
					
					return clientes;
				} catch (SQLException e) {
					throw new RepositoriosException("Error al acceder a los registros", e);
				}
			} catch (SQLException e) {
				throw new RepositoriosException("Error al crear la sentencia", e);
			}
		} catch (SQLException e) {
			throw new RepositoriosException("Error al conectar", e);
		}
	}


	@Override
	public Cliente getById(Integer id) {
		throw new UnsupportedOperationException("NO ESTA IMPLEMENTADO");
	}

	@Override
	public void insert(Cliente objeto) {
		throw new UnsupportedOperationException("NO ESTA IMPLEMENTADO");
	}

	@Override
	public void update(Cliente objeto) {
		throw new UnsupportedOperationException("NO ESTA IMPLEMENTADO");
	}

	@Override
	public void delete(Integer id) {
		throw new UnsupportedOperationException("NO ESTA IMPLEMENTADO");
	}

}
