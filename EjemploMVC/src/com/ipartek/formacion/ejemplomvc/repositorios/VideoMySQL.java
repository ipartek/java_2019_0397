package com.ipartek.formacion.ejemplomvc.repositorios;

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

import com.ipartek.formacion.ejemplomvc.modelos.Video;

class VideoMySQL implements Dao<Video>{
	private static final String SQL_SELECT = "SELECT * FROM videos";
	private static final String SQL_SELECT_BY_ID = "SELECT * FROM videos WHERE id=?";

	private static final String SQL_INSERT = "INSERT INTO videos (nombre, url) VALUES (?,?)";
	private static final String SQL_UPDATE = "UPDATE videos SET nombre=?, url=? WHERE id=?";
	private static final String SQL_DELETE = "DELETE FROM videos WHERE id=?";
	
	private static String url, usuario, password;
	
	// SINGLETON
	private static VideoMySQL instancia;
	
	private VideoMySQL(String url, String usuario, String password) {
		VideoMySQL.url = url;
		VideoMySQL.usuario = usuario;
		VideoMySQL.password = password;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new AccesoDatosException("No se ha encontrado el driver de MySQL");
		}
	}
	
	public static VideoMySQL getInstancia(String pathConfiguracion) {
		try {
			if (instancia == null) {
				Properties configuracion = new Properties();
				configuracion.load(new FileInputStream(pathConfiguracion));

				instancia = new VideoMySQL(configuracion.getProperty("mysql.url"),
						configuracion.getProperty("mysql.usuario"), configuracion.getProperty("mysql.password"));
			}

			return instancia;
		} catch (FileNotFoundException e) {
			throw new AccesoDatosException("Fichero de configuración no encontrado", e);
		} catch (IOException e) {
			throw new AccesoDatosException("Fallo de lectura/escritura al fichero", e);
		}
	}
	
	// FIN SINGLETON
	
	private Connection getConexion() {
		try {
			return DriverManager.getConnection(url, usuario, password);
		} catch (SQLException e) {
			throw new AccesoDatosException("Error en la conexión a la base de datos");
		}
	}
	
	@Override
	public Iterable<Video> obtenerTodos() {
		try (Connection con = getConexion()) {
			try(PreparedStatement ps = con.prepareStatement(SQL_SELECT)) {
				try(ResultSet rs = ps.executeQuery()){
					ArrayList<Video> videos = new ArrayList<>();
					
					while(rs.next()) {
						videos.add(new Video(rs.getLong("id"), rs.getString("nombre"), rs.getString("url")));
					}
					
					return videos;
				}
			}
		} catch (SQLException e) {
			throw new AccesoDatosException("Error al obtener todos los videos", e);
		}
	}

	@Override
	public Video obtenerPorId(Long id) {
		try (Connection con = getConexion()) {
			try(PreparedStatement ps = con.prepareStatement(SQL_SELECT_BY_ID)) {
				ps.setLong(1, id);
				
				try(ResultSet rs = ps.executeQuery()){
									
					if(rs.next()) {
						return new Video(rs.getLong("id"), rs.getString("nombre"), rs.getString("url"));
					} else {
						return null;
					}
				}
			}
		} catch (SQLException e) {
			throw new AccesoDatosException("Error al obtener el video id: " + id, e);
		}
	}

	@Override
	public void agregar(Video video) {
		try (Connection con = getConexion()) {
			try(PreparedStatement ps = con.prepareStatement(SQL_INSERT)) {
				ps.setString(1, video.getNombre());
				ps.setString(2, video.getUrl());
				
				int numeroRegistrosModificados = ps.executeUpdate();
				
				if(numeroRegistrosModificados != 1) {
					throw new AccesoDatosException("Se ha hecho más o menos de una insert");
				}
			}
		} catch (SQLException e) {
			throw new AccesoDatosException("Error al insertar el video", e);
		}
	}

	@Override
	public void modificar(Video video) {
		try (Connection con = getConexion()) {
			try(PreparedStatement ps = con.prepareStatement(SQL_UPDATE)) {
				ps.setString(1, video.getNombre());
				ps.setString(2, video.getUrl());
				ps.setLong(3, video.getId());
				
				int numeroRegistrosModificados = ps.executeUpdate();
				
				if(numeroRegistrosModificados != 1) {
					throw new AccesoDatosException("Se ha hecho más o menos de una update");
				}
			}
		} catch (SQLException e) {
			throw new AccesoDatosException("Error al modificar el video", e);
		}
	}

	@Override
	public void borrar(Long id) {
		try (Connection con = getConexion()) {
			try(PreparedStatement ps = con.prepareStatement(SQL_DELETE)) {
				ps.setLong(1, id);
				
				int numeroRegistrosModificados = ps.executeUpdate();
				
				if(numeroRegistrosModificados != 1) {
					throw new AccesoDatosException("Se ha hecho más o menos de una delete");
				}
			}
		} catch (SQLException e) {
			throw new AccesoDatosException("Error al borrar el video", e);
		}
	}

}
