package com.ipartek.formacion.masajes.repositorios;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import com.ipartek.formacion.masajes.modelos.Cliente;
import com.ipartek.formacion.masajes.modelos.Servicio;
import com.ipartek.formacion.masajes.modelos.Sesion;
import com.ipartek.formacion.masajes.modelos.Trabajador;

class SesionMySQL implements SesionDao {
	private static final String SQL_GET_ALL = "SELECT * \r\n" + "FROM sesiones ses\r\n"
			+ "JOIN clientes c ON c.idclientes = ses.clientes_idclientes\r\n"
			+ "JOIN trabajadores t ON t.idtrabajadores = ses.trabajadores_idtrabajadores\r\n"
			+ "JOIN servicios s ON s.idservicios = ses.servicios_idservicios;";

	private static final String SQL_GET_ID = "SELECT * FROM sesiones WHERE id=?";

	private static final String SQL_INSERT = "INSERT INTO sesiones "
			+ "(clientes_idclientes, trabajadores_idtrabajadores, servicios_idservicios, "
			+ "fecha, resena, calificacion) VALUES (?, ?, ?, ?, ?, ?)";

	private static final String SQL_UPDATE = "UPDATE sesiones SET "
			+ "clientes_idclientes=?, trabajadores_idtrabajadores=?, servicios_idservicios=?, "
			+ "fecha=?, resena=?, calificacion=? WHERE id=?";

	private static final String SQL_DELETE = "DELETE FROM sesiones WHERE id=?";

	private final String url, usuario, password;

	// "SINGLETON"
	private SesionMySQL(String url, String usuario, String password) {
		this.url = url;
		this.usuario = usuario;
		this.password = password;
	}

	private static SesionMySQL instancia;

	/**
	 * Se usará para inicializar la instancia
	 * 
	 * @param url
	 * @param usuario
	 * @param password
	 * @return La instancia
	 */
	public static SesionMySQL getInstancia(String url, String usuario, String password) {
		// Si no existe la instancia...
		if (instancia == null) {
			// ...la creamos
			instancia = new SesionMySQL(url, usuario, password);
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
	public static SesionMySQL getInstancia() {
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
			System.err
					.println("IPARTEK: Error de conexión a la base de datos: " + url + ":" + usuario + ":" + password);
			e.printStackTrace();

			throw new RepositoriosException("No se ha podido conectar a la base de datos", e);
		}
	}

	@Override
	public Iterable<Sesion> getAll() {
		try (Connection con = getConexion()) {
			try (Statement s = con.createStatement()) {
				try (ResultSet rs = s.executeQuery(SQL_GET_ALL)) {
					ArrayList<Sesion> sesiones = new ArrayList<>();

					Cliente cliente;
					Trabajador trabajador;
					Servicio servicio;
					Sesion sesion;

					while (rs.next()) {
						// Partes
						cliente = new Cliente(rs.getInt("clientes_idclientes"), rs.getString("c.nombre"),
								rs.getString("c.apellidos"), rs.getString("c.dni"));

						trabajador = new Trabajador(rs.getInt("trabajadores_idtrabajadores"), rs.getString("t.nombre"),
								rs.getString("t.apellidos"), rs.getString("t.dni"));

						servicio = new Servicio(rs.getInt("servicios_idservicios"), rs.getString("s.nombre"),
								rs.getBigDecimal("s.precio"));

						// Completo
						sesion = new Sesion(rs.getInt("id"), cliente, trabajador, servicio, rs.getTimestamp("fecha"),
								rs.getString("resena"), rs.getString("calificacion"));

						// Agregar
						sesiones.add(sesion);
					}

					return sesiones;
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
	public Sesion getById(Integer id) {
		try (Connection con = getConexion()) {
			try (PreparedStatement ps = con.prepareStatement(SQL_GET_ID)) {
				ps.setInt(1, id);

				try (ResultSet rs = ps.executeQuery()) {
					Cliente cliente;
					Trabajador trabajador;
					Servicio servicio;

					Sesion sesion = null;

					if (rs.next()) {
						// Partes
						cliente = new Cliente(rs.getInt("clientes_idclientes"), null, null, null);

						trabajador = new Trabajador(rs.getInt("trabajadores_idtrabajadores"), null, null, null);

						servicio = new Servicio(rs.getInt("servicios_idservicios"), null, null);

						// Completo
						sesion = new Sesion(rs.getInt("id"), cliente, trabajador, servicio, rs.getTimestamp("fecha"),
								rs.getString("resena"), rs.getString("calificacion"));
					}

					return sesion;
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
	public Integer insert(Sesion sesion) {
		try (Connection con = getConexion()) {
			try (PreparedStatement ps = con.prepareStatement(SQL_INSERT)) {

				ps.setInt(1, sesion.getCliente().getId());
				ps.setInt(2, sesion.getTrabajador().getId());
				ps.setInt(3, sesion.getServicio().getId());
				ps.setTimestamp(4, new Timestamp(sesion.getFecha().getTime()));
				ps.setString(5, sesion.getResena());
				ps.setString(6, sesion.getCalificacion());

				int numeroRegistrosModificados = ps.executeUpdate();

				if (numeroRegistrosModificados != 1) {
					throw new RepositoriosException("Número de registros modificados: " + numeroRegistrosModificados);
				}

				return null;
			} catch (SQLException e) {
				throw new RepositoriosException("Error al crear la sentencia", e);
			}
		} catch (SQLException e) {
			throw new RepositoriosException("Error al conectar", e);
		}
	}

	@Override
	public void update(Sesion sesion) {
		try (Connection con = getConexion()) {
			try (PreparedStatement ps = con.prepareStatement(SQL_UPDATE)) {

				ps.setInt(1, sesion.getCliente().getId());
				ps.setInt(2, sesion.getTrabajador().getId());
				ps.setInt(3, sesion.getServicio().getId());
				ps.setTimestamp(4, new Timestamp(sesion.getFecha().getTime()));
				ps.setString(5, sesion.getResena());
				ps.setString(6, sesion.getCalificacion());
				ps.setInt(7, sesion.getId());

				int numeroRegistrosModificados = ps.executeUpdate();

				if (numeroRegistrosModificados != 1) {
					throw new RepositoriosException("Número de registros modificados: " + numeroRegistrosModificados);
				}
			} catch (SQLException e) {
				throw new RepositoriosException("Error al crear la sentencia", e);
			}
		} catch (SQLException e) {
			throw new RepositoriosException("Error al conectar", e);
		}
	}

	@Override
	public void delete(Integer id) {
		try (Connection con = getConexion()) {
			try (PreparedStatement ps = con.prepareStatement(SQL_DELETE)) {

				ps.setInt(1, id);

				int numeroRegistrosModificados = ps.executeUpdate();

				if (numeroRegistrosModificados != 1) {
					throw new RepositoriosException("Número de registros modificados: " + numeroRegistrosModificados);
				}
			} catch (SQLException e) {
				throw new RepositoriosException("Error al crear la sentencia", e);
			}
		} catch (SQLException e) {
			throw new RepositoriosException("Error al conectar", e);
		}
	}

	@Override
	public void citaPeriodicaSemanal(Integer idCliente, Integer idTrabajador, Integer idServicio, Date fechaInicial,
			int repeticiones) {
		try (Connection con = getConexion()) {
			con.setAutoCommit(false);
			
			try (PreparedStatement ps = con.prepareStatement(SQL_INSERT)) {

				ps.setInt(1, idCliente);
				ps.setInt(2, idTrabajador);
				ps.setInt(3, idServicio);
				
				ps.setString(5, null);
				ps.setString(6, null);

				for (int repeticion = 1; repeticion <= repeticiones; repeticion++) {
					ps.setTimestamp(4, new Timestamp(fechaInicial.getTime()+1000L*60*60*24*7*(repeticion-1)));
					
					int numeroRegistrosModificados = ps.executeUpdate();

					if (numeroRegistrosModificados != 1) {
						throw new RepositoriosException(
								"Número de registros modificados: " + numeroRegistrosModificados);
					}
					
					//SIMULAMOS UN ERROR
//					if(repeticion == 3) {
//						throw new SQLException("LA INSERT HA 'FALLADO'");
//					}
				}
				
				con.commit();

			} catch (SQLException e) {
				con.rollback();
				throw new RepositoriosException("Error al crear la sentencia", e);
			} finally {
				con.setAutoCommit(true);
			}
		} catch (SQLException e) {
			throw new RepositoriosException("Error al conectar", e);
		}

	}

}
