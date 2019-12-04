package com.ipartek.formacion.poo;

import java.util.ArrayList;

public class Oficina {
	private String nombre;
	private String poblacion;
	
	private ArrayList<Persona> personas = new ArrayList<>();
	
	public Oficina(String nombre, String poblacion) {
		super();
		this.nombre = nombre;
		this.poblacion = poblacion;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getPoblacion() {
		return poblacion;
	}
	public void setPoblacion(String poblacion) {
		this.poblacion = poblacion;
	}
	
	public void addPersona(Persona persona) {
		if(persona == null) {
			throw new RuntimeException("No se admiten valores nulos");
		}
		
		personas.add(persona);
	}
	
	public Iterable<Persona> getPersonas() {
		return personas;
	}
}
