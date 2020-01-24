package com.ipartek.formacion.mf0967_3.modelos;

import java.math.BigDecimal;

public class Libro {
	private static final String IMAGEN_POR_DEFECTO = "2.jpg";

	private Long id;
	private String nombre;
	private BigDecimal precio;
	// Descuento de 0 a 100
	private Byte descuento;
	private String autor;
	private String imagen;

	private boolean correcto = true;
	private String errorId, errorNombre, errorPrecio, errorDescuento, errorAutor, errorImagen;

	public Libro(String id, String nombre, String precio, String descuento, String autor, String imagen) {
		setId(id);
		setNombre(nombre);
		setPrecio(precio);
		setDescuento(descuento);
		setAutor(autor);
		setImagen(imagen);
	}

	public Libro(Long id, String nombre, BigDecimal precio, Byte descuento, String autor, String imagen) {
		setId(id);
		setNombre(nombre);
		setPrecio(precio);
		setDescuento(descuento);
		setAutor(autor);
		setImagen(imagen);
	}

	public Libro(Long id, String nombre, BigDecimal precio) {
		this(id, nombre, precio, null, null, null);
	}

	public Libro() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		int largoTexto = nombre.trim().length();

		if (nombre == null || largoTexto < 2 || largoTexto > 150) {
			setErrorNombre("Los nombres deben tener de 2 a 150 caracteres");
		}

		this.nombre = nombre;
	}

	public BigDecimal getPrecio() {
		return precio;
	}

	public void setPrecio(BigDecimal precio) {
		if (precio.compareTo(BigDecimal.ZERO) <= 0) {
			setErrorPrecio("El precio debe ser mayor que cero");
		}
		this.precio = precio;
	}

	public Byte getDescuento() {
		return descuento;
	}

	public void setDescuento(Byte descuento) {
		if (descuento < 0 || descuento > 100) {
			setErrorDescuento("El descuento debe estar entre 0 y 100");
		}
		this.descuento = descuento;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		if (autor == null || autor.trim().length() == 0) {
			this.autor = "Anónimo";
		} else {
			this.autor = autor;
		}
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		if (imagen == null || imagen.trim().length() == 0) {
			this.imagen = IMAGEN_POR_DEFECTO;
			return;
		}
		this.imagen = imagen;
	}

	private void setId(String id) {
		if (id == null || id.trim().length() == 0) {
			this.id = null;
			return;
		}

		try {
			this.id = Long.parseLong(id);
		} catch (NumberFormatException e) {
			setErrorId("No era un número");
		}
	}

	private void setPrecio(String precio) {
		if (precio == null) {
			setErrorPrecio("El precio es obligatorio");
			this.precio = null;
			return;
		}

		try {
			setPrecio(new BigDecimal(precio.replace(",", ".")));

			if (!precio.matches("\\d+[,\\.]\\d{2}")) {
				throw new NumberFormatException();
			}
		} catch (NumberFormatException e) {
			setErrorPrecio("El precio debe tener dos decimales y al menos un entero y debe ser positivo");
		}

	}

	private void setDescuento(String descuento) {
		if (descuento == null || descuento.trim().length() == 0) {
			setErrorDescuento("Se debe rellenar el descuento");
			this.descuento = null;
			return;
		}

		try {
			setDescuento(new Byte(descuento));
		} catch (NumberFormatException e) {
			setErrorDescuento("Debe ser un número entero");
		}
	}

	public boolean isCorrecto() {
		return correcto;
	}

	public void setCorrecto(boolean correcto) {
		this.correcto = correcto;
	}

	public String getErrorId() {
		return errorId;
	}

	public void setErrorId(String errorId) {
		setCorrecto(false);
		this.errorId = errorId;
	}

	public String getErrorNombre() {
		return errorNombre;
	}

	public void setErrorNombre(String errorNombre) {
		setCorrecto(false);
		this.errorNombre = errorNombre;
	}

	public String getErrorPrecio() {
		return errorPrecio;
	}

	public void setErrorPrecio(String errorPrecio) {
		setCorrecto(false);
		this.errorPrecio = errorPrecio;
	}

	public String getErrorDescuento() {
		return errorDescuento;
	}

	public void setErrorDescuento(String errorDescuento) {
		setCorrecto(false);
		this.errorDescuento = errorDescuento;
	}

	public String getErrorAutor() {
		return errorAutor;
	}

	public void setErrorAutor(String errorAutor) {
		setCorrecto(false);
		this.errorAutor = errorAutor;
	}

	public String getErrorImagen() {
		return errorImagen;
	}

	public void setErrorImagen(String errorImagen) {
		setCorrecto(false);
		this.errorImagen = errorImagen;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((autor == null) ? 0 : autor.hashCode());
		result = prime * result + ((descuento == null) ? 0 : descuento.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((imagen == null) ? 0 : imagen.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime * result + ((precio == null) ? 0 : precio.hashCode());
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
		Libro other = (Libro) obj;
		if (autor == null) {
			if (other.autor != null)
				return false;
		} else if (!autor.equals(other.autor))
			return false;
		if (descuento == null) {
			if (other.descuento != null)
				return false;
		} else if (!descuento.equals(other.descuento))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (imagen == null) {
			if (other.imagen != null)
				return false;
		} else if (!imagen.equals(other.imagen))
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		if (precio == null) {
			if (other.precio != null)
				return false;
		} else if (!precio.equals(other.precio))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Libro [id=" + id + ", nombre=" + nombre + ", precio=" + precio + ", descuento=" + descuento + ", autor="
				+ autor + ", imagen=" + imagen + "]";
	}
}
