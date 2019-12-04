package com.ipartek.formacion.poo;

import java.math.BigDecimal;

public class Empleado extends Persona {
	private BigDecimal sueldo;
	
	public Empleado() {
		this(NOMBRE_POR_DEFECTO, APELLIDOS_POR_DEFECTO, new BigDecimal(0));	
	}

	public Empleado(String nombre, String apellidos, BigDecimal sueldo) {
		super(nombre, apellidos);
		setSueldo(sueldo);
	}

	public BigDecimal getSueldo() {
		return sueldo;
	}

	public void setSueldo(BigDecimal sueldo) {
		this.sueldo = sueldo;
	}

	@Override
	public String getNombreCompleto() {
		return super.getNombreCompleto() + ":" + getSueldo();
	}
	
	
}
