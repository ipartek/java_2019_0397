package com.ipartek.formacion.poo;

public class Persona {
	//CONSTANTES
	protected static final String APELLIDOS_POR_DEFECTO = "ANÓNIMEZ";
	protected static final String NOMBRE_POR_DEFECTO = "ANÓNIMO";
	
	public static final boolean CON_FORMATO = true;
	public static final boolean SIN_FORMATO = false;
	
	//VARIABLES DE INSTANCIA
	private String nombre;
	private String apellidos;
	
	//CONSTRUCTORES
	public Persona(String nombre, String apellidos) {
		setNombre(nombre);
		setApellidos(apellidos);
	}
	
	public Persona() {
		this(NOMBRE_POR_DEFECTO, APELLIDOS_POR_DEFECTO);
	}

	//GETTERS Y SETTERS
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		if(nombre == null) {
			throw new RuntimeException("No se acepta un nombre nulo");
		}
		
		if(nombre.trim().length() == 0) {
			throw new RuntimeException("No se aceptan nombres vacíos");
		}
		this.nombre = nombre.trim();
	}
	
	public String getApellidos() {
		return apellidos;
	}
	
	public void setApellidos(String apellidos) {
		if(apellidos == null) {
			throw new RuntimeException("No se aceptan apellidos nulos");
		}
		
		this.apellidos = apellidos.trim();
	}
	
	//EJEMPLO DE SOBRECARGAS DE MÉTODOS
	public String getNombreCompleto() {
		return getNombre() + " " + getApellidos();
	}
	
	public String getNombreCompleto(boolean conFormato) {
		if (conFormato) {
			return "[" + getNombre() + ", " + getApellidos() + "]";
		} else {
			return getNombreCompleto();
		}
	}
	
	//MÉTODO ESTÁTICO
	public static String getNombreCompletoPorDefecto() {
		return NOMBRE_POR_DEFECTO + " " + APELLIDOS_POR_DEFECTO;
	}
}
