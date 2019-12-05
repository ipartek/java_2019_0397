package com.ipartek.formacion.poo;

import java.math.BigDecimal;

public class Empleado extends Persona {
	private static final BigDecimal SUELDO_POR_DEFECTO = BigDecimal.ZERO;
	private BigDecimal sueldo;
	
	public Empleado(String nombre, String apellidos, BigDecimal sueldo) {
		super(nombre, apellidos);
		setSueldo(sueldo);
	}

	public Empleado(Persona p, BigDecimal sueldo) {
		this(p.getNombre(), p.getApellidos(), sueldo);
	}
	
	public Empleado(Persona p) {
		this(p.getNombre(), p.getApellidos(), SUELDO_POR_DEFECTO);
	}
	
	public Empleado() {
		this(NOMBRE_POR_DEFECTO, APELLIDOS_POR_DEFECTO, SUELDO_POR_DEFECTO);	
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
	
	@Override
	public String toString() {
		return "Empleado [sueldo=" + sueldo + ", toString()=" + super.toString() + "]";
	}
}
