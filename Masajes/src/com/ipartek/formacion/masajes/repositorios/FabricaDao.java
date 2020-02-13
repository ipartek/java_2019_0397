package com.ipartek.formacion.masajes.repositorios;

import com.ipartek.formacion.masajes.modelos.Cliente;
import com.ipartek.formacion.masajes.modelos.Servicio;
import com.ipartek.formacion.masajes.modelos.Sesion;
import com.ipartek.formacion.masajes.modelos.Trabajador;

public interface FabricaDao {
	Dao<Sesion> getSesionDao();
	Dao<Cliente> getClienteDao();
	Dao<Trabajador> getTrabajadorDao();
	Dao<Servicio> getServicioDao();
}
