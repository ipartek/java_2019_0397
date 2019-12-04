package com.ipartek.formacion.poo;

public class OficinaPrueba {

	public static void main(String[] args) {
		Oficina o = new Oficina("Ipartek", "Bilbao");
		
		o.addPersona(new Persona());
		//o.addPersona(null); //No se puede. Da una excepción
		o.addPersona(new Persona("Javier", "Lete"));
		
		Persona p = o.getPersonas().iterator().next();
		
		p.setNombre("CAMBIADO");
		
		System.out.println(p.getNombreCompleto());
		
		for(Persona per: o.getPersonas()) {
			System.out.println(per.getNombreCompleto());
		}
	}

}
