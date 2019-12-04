package com.ipartek.formacion.poo;

import java.util.ArrayList;

public class PersonaPrueba {

	private static String prueba = "PrUEBa";
	
	public static void main(String[] args) {
		System.out.println(prueba);
		
		Persona p;
		
		p = new Persona();
		
		System.out.println(p.getNombreCompleto());
		
		p = new Persona("Pepe", "Pérez");
		
		System.out.println(p.getNombreCompleto());
		
		p.setNombre("Javier");
		p.setApellidos("Lete");
		
		System.out.println(p.getNombre());
		System.out.println(p.getApellidos());
		
		System.out.println(p.getNombreCompleto());
		
		System.out.println(p.getNombreCompleto(Persona.SIN_FORMATO));
		System.out.println(p.getNombreCompleto(Persona.CON_FORMATO));
		
		System.out.println(Persona.getNombreCompletoPorDefecto());
		
		Persona[] personas = new Persona[2];
		
		personas[0] = new Persona("Pepe", "Pérez");
		personas[1] = p;
		
		for(Persona persona: personas) {
			System.out.println(persona.getNombreCompleto());
		}
		
		ArrayList<Persona> alPersonas = new ArrayList<>();
		
		alPersonas.add(new Persona("Juan", "Gómez"));
		alPersonas.add(null);
		alPersonas.add(p);
		
		for(Persona persona: alPersonas) {
			if(persona != null) {
				System.out.println(persona.getNombreCompleto());
			} else {
				System.out.println("La persona vale null");
			}
		}
	}
}
