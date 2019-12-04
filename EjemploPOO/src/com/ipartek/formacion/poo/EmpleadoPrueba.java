package com.ipartek.formacion.poo;

import java.math.BigDecimal;

public class EmpleadoPrueba {
	public static void main(String[] args) {
		Empleado e; 
		
		e = new Empleado();
		
		System.out.println(e.getNombreCompleto());
		
		e.setNombre("Javier");
		
		e.setSueldo(new BigDecimal("123.23"));
		
		System.out.println(e.getNombreCompleto());
		
		System.out.println(e.getSueldo());
	}
}
