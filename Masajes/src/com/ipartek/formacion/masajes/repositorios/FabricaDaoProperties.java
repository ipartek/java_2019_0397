package com.ipartek.formacion.masajes.repositorios;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import com.ipartek.formacion.masajes.modelos.Cliente;
import com.ipartek.formacion.masajes.modelos.Servicio;
import com.ipartek.formacion.masajes.modelos.Sesion;
import com.ipartek.formacion.masajes.modelos.Trabajador;

public class FabricaDaoProperties implements FabricaDao {
	private String tipo;
	private String url, usuario, password;

	//SINGLETON
	private static FabricaDaoProperties instancia;
	
	/**
	 * Recoger instancia existente
	 * @return (puede valer null)
	 */
	public static FabricaDaoProperties getInstancia() {
		return instancia;
	}
	
	/**
	 * Crear una nueva instancia con un path
	 * @param pathProperties
	 * @return
	 */
	public static FabricaDaoProperties getInstancia(String pathProperties) {
		if(instancia == null) {
			instancia = new FabricaDaoProperties(pathProperties);
		}
		
		return instancia;
	}
	
	private FabricaDaoProperties(String pathProperties) {
		Properties props = new Properties();

		try {
			props.load(new FileReader(pathProperties));

			tipo = props.getProperty("tipo");
			url = props.getProperty(tipo + ".url");
			usuario = props.getProperty(tipo + ".usuario");
			password = props.getProperty(tipo + ".password");
		} catch (IOException e) {
			throw new RepositoriosException("No se ha podido leer el fichero de properties: " + pathProperties, e);
		}
	}
	
	@Override
	public Dao<Sesion> getSesionDao() {
		if(tipo == null) {
			throw new RepositoriosException("No se ha recibido ningún tipo");
		}
		
		switch (tipo) {
		case "mysql": return SesionMySQL.getInstancia(url, usuario, password);
		default:
			throw new RepositoriosException("No reconozco el tipo " + tipo);
		}
	}

	@Override
	public Dao<Cliente> getClienteDao() {
		throw new UnsupportedOperationException("NO ESTA IMPLEMENTADO");
	}

	@Override
	public Dao<Trabajador> getTrabajadorDao() {
		throw new UnsupportedOperationException("NO ESTA IMPLEMENTADO");
	}

	@Override
	public Dao<Servicio> getServicioDao() {
		throw new UnsupportedOperationException("NO ESTA IMPLEMENTADO");
	}
}
