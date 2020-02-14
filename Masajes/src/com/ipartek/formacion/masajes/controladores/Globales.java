package com.ipartek.formacion.masajes.controladores;

import com.ipartek.formacion.masajes.modelos.Cliente;
import com.ipartek.formacion.masajes.modelos.Servicio;
import com.ipartek.formacion.masajes.modelos.Sesion;
import com.ipartek.formacion.masajes.modelos.Trabajador;
import com.ipartek.formacion.masajes.repositorios.Dao;

public class Globales {
	public static Dao<Cliente> daoClientes;
	public static Dao<Sesion> daoSesion;
	public static Dao<Trabajador> daoTrabajadores;
	public static Dao<Servicio> daoServicios;
}
