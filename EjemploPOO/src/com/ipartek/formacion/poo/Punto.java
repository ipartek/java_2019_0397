package com.ipartek.formacion.poo;

// POJO
// Plain Old Java Objects
public class Punto {
	// Constantes (pueden ser públicas)
	private static final int X_POR_DEFECTO = 0;
	private static final int Y_POR_DEFECTO = 0;
	private static final int Z_POR_DEFECTO = 0;
	
	// Variables de instancia (siempre privadas)
	private int x, y, z;

	// Constructor de todos los campos (si existen, con setters)
	public Punto(int x, int y, int z) {
		setX(x); setY(y); setZ(z);
	}
	
	// Constructor vacío (suele hacer falta para diferentes APIs)
	public Punto() {
		this(X_POR_DEFECTO, Y_POR_DEFECTO, Z_POR_DEFECTO);
	}

	// Setters y getters (normalmente con validaciones en setters y excepciones si fallan)
	public int getX() {
		return x;
	}

	public void setX(int x) {
		if(x < 0) {
			throw new PuntoException("No se admiten valores de X menores de 0");
		}
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getZ() {
		return z;
	}

	public void setZ(int z) {
		this.z = z;
	}

	// hashCode e equals son necesarios para ciertos métodos de la API
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		result = prime * result + z;
		return result;
	}

	// equals permite la comparación por contenido y no por referencia
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Punto other = (Punto) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		if (z != other.z)
			return false;
		return true;
	}

	// Conversión del objeto a un texto resumido
	@Override
	public String toString() {
		return "Punto [x=" + x + ", y=" + y + ", z=" + z + "]";
	}
}
