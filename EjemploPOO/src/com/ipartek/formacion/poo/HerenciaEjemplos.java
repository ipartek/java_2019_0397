package com.ipartek.formacion.poo;

public class HerenciaEjemplos {
	public static void main(String[] args) {
		Persona p = new Persona();
		
		//Empleado e = (Empleado) p; //NO SE PUEDE
		
		Empleado e2 = new Empleado();
		
		Persona p2 = e2;
		
		System.out.println(p2 == e2);
		
		System.out.println(p2.getClass());
		System.out.println(e2.getClass());
		
		System.out.println(p2 instanceof Empleado);
		
		Empleado e3 = (Empleado) p2;
		
		System.out.println(e3.getSueldo());
		
		System.out.println( ((Empleado) p2).getSueldo() );
		
		System.out.println("OK");
		
		Object o = p2;
		
		System.out.println(o);
		
		System.out.println(e2);
	}
}
