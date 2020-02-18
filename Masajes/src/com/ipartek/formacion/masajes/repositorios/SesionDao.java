package com.ipartek.formacion.masajes.repositorios;

import java.util.Date;

import com.ipartek.formacion.masajes.modelos.Sesion;

public interface SesionDao extends Dao<Sesion> {
	void citaPeriodicaSemanal(Integer idCliente, Integer idTrabajador, Integer idServicio, Date fechaInicial, int repeticiones);
}
