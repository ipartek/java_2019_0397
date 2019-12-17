package com.ipartek.formacion.ejemploaccesodatosformacion.entidades;

import java.io.Serializable;
import java.util.Date;

import com.ipartek.formacion.ejemploaccesodatosformacion.utilidades.Biblioteca;

public class Alumno implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final String REGEX_NOMBRE = "[\\p{L} ]+"; //"[a-zA-Z¡…Õ”⁄·ÈÌÛ˙—ÒÁ«‡ËÏÚ˘¿»Ã“Ÿ' ]+";
	private static final String REGEX_APELLIDOS = "[\\p{L} ']+";
	private static final String REGEX_DNI = "[XYZ\\d]\\d{7}[A-Z]";
	
	private Long id;
	private String nombre, apellidos;
	private String dni;
	private Date fechaNacimiento;

	public Alumno(Long id, String nombre, String apellidos, String dni, Date fechaNacimiento) {
		setId(id);
		setNombre(nombre);
		setApellidos(apellidos);
		setDni(dni);
		setFechaNacimiento(fechaNacimiento);
	}

	public Alumno() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		if(id != null && id < 0) {
			throw new EntidadesException("No se admiten id's negativos");
		}
		
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	/**
	 * SÛlo se admitir·n nombres 
	 * @param nombre El nombre que se quiere guardar
	 * @throws EntidadesException Si el nombre no son letras o espacios
	 */
	public void setNombre(String nombre) {
		if(nombre == null || nombre.trim().length() == 0) {//"".equals(nombre))//nombre.equals("")) {
			throw new EntidadesException("El nombre es obligatorio");
		}
		
		if(!nombre.matches(REGEX_NOMBRE) ) {
			throw new EntidadesException("SÛlo se admiten caracteres de letras y espacios");
		}
		
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		if(apellidos != null && apellidos.trim().length() == 0) {
			this.apellidos = null;
			return;
		}
		
		if(!apellidos.matches(REGEX_APELLIDOS)) {
			throw new EntidadesException("Los apellidos deben ser letras, espacios y/o apÛstrofes");
		}

		this.apellidos = apellidos;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		if(!dni.matches(REGEX_DNI)) {
			throw new EntidadesException("No concuerda con el formato de un DNI");
		}
		
		if(!Biblioteca.dniValido(dni)) {
			throw new EntidadesException("El DNI introducido no es v·lido");
		}
		
		this.dni = dni;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		if(fechaNacimiento != null && fechaNacimiento.after(new Date())) {
			throw new EntidadesException("No puedes usar una fecha futura para la fecha de nacimiento");
		}
		
		this.fechaNacimiento = fechaNacimiento;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((apellidos == null) ? 0 : apellidos.hashCode());
		result = prime * result + ((dni == null) ? 0 : dni.hashCode());
		result = prime * result + ((fechaNacimiento == null) ? 0 : fechaNacimiento.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Alumno other = (Alumno) obj;
		if (apellidos == null) {
			if (other.apellidos != null)
				return false;
		} else if (!apellidos.equals(other.apellidos))
			return false;
		if (dni == null) {
			if (other.dni != null)
				return false;
		} else if (!dni.equals(other.dni))
			return false;
		if (fechaNacimiento == null) {
			if (other.fechaNacimiento != null)
				return false;
		} else if (!fechaNacimiento.equals(other.fechaNacimiento))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Alumno [id=" + id + ", nombre=" + nombre + ", apellidos=" + apellidos + ", dni=" + dni
				+ ", fechaNacimiento=" + fechaNacimiento + "]";
	}
}
