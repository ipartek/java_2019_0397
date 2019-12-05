package com.ipartek.formacion.poo;

import java.util.ArrayList;
import java.util.Date;

public class HerenciaEjemplos {
	public static void main(String[] args) {
		@SuppressWarnings("unused")
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
		
		Oficina of = new Oficina("Pruebas", "Bilbao");
		
		of.addPersona(new Persona());
		of.addPersona(new Empleado());
		
		for(Persona per: of.getPersonas()) {
			System.out.println(per);
			System.out.println(per.getNombre() + " " + per.getApellidos());
		}
		
		System.out.println("" + e2 + p2);
		
		String log = "";
		
		log += "texto1\n";
		log = new StringBuffer(log).append("texto1\n").toString();
		
		log += new Date() + ":" + "texto2\n"; 
		log = new StringBuffer(log).append(new Date()).append(":").append("texto2\n").toString();
		
		StringBuffer sbLog = new StringBuffer();
		
		sbLog.append("texto1\n");
		sbLog.append(new Date()).append(":").append("texto2\n");
		
		String logConvertido = sbLog.toString();
		
		System.out.println(logConvertido);
		
		o.equals(o);
		
		System.out.println(new Persona().equals(new Persona()));
		
		ArrayList<Persona> personas = (ArrayList<Persona>) of.getPersonas();
		
		System.out.println("ANTES\n" + getNombresOficina(of));
		
		personas.remove(new Persona());
		
		System.out.println("DESPUES\n" + getNombresOficina(of));
	}
	
	private static String getNombresOficina(Oficina o) {
		StringBuffer sb = new StringBuffer();
		
		for(Persona p: o.getPersonas()) {
			sb.append(p.getNombre()).append('\n');
		}
		
		return sb.toString();
	}
}
