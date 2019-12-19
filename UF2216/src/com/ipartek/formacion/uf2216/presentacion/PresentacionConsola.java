package com.ipartek.formacion.uf2216.presentacion;

import java.util.Scanner;
import java.util.TreeMap;

import com.ipartek.formacion.uf2216.entidades.Libro;

public class PresentacionConsola {

	private final static TreeMap<Long, Libro> libros = new TreeMap<>();
	
	public static void main(String[] args) {
		Scanner scanner;
		long ultimoId = 1L;
		boolean esNuevoRegistro;
		boolean esTituloCorrecto, esIsbnCorrecto, esNumeroPaginasCorrecto, esFormatoCorrecto;
		String titulo, isbn;
		int numeroPaginas = -1;
		boolean formato = false;
		String stringFormato;
		
		//Scanner sobre la entrada estándar
		scanner = new Scanner(System.in);
		
		do {
			
			do {
				//Introducción de título
				System.out.print("Título: ");
				titulo = scanner.nextLine();
				
				//Validación del título
				if (titulo.length() >= 3 && titulo.length() <= 150) {
					esTituloCorrecto = true;
				} else {
					esTituloCorrecto = false;
					System.out.println("El título debe tener un tamaño mínimo de 3 letras y máximo de 150");
				}
			} while (!esTituloCorrecto);
			
			do {
				//Introducción de ISBN
				System.out.print("ISBN: ");
				isbn = scanner.nextLine();
				
				if(isbn.matches("\\d{10}")) {
					esIsbnCorrecto = true;
				} else {
					esIsbnCorrecto = false;
					System.out.println("El ISBN debe ser un número de longitud 10");
				}
			} while (!esIsbnCorrecto);
			
			do {
				//Introducción de número de páginas
				System.out.print("Número de páginas: ");
				
				try {
					numeroPaginas = scanner.nextInt();
					
					if(numeroPaginas >= 1) {
						esNumeroPaginasCorrecto = true;
					} else {
						esNumeroPaginasCorrecto = false;
						System.out.println("El número de páginas debe ser mínimo 1");
					}
				} catch (Exception e) {
					System.out.println("Debes introducir un número (sólo dígitos)");
					esNumeroPaginasCorrecto = false;
				} finally {
					//Consumir el enter si ha ido bien, o el texto si ha habido una excepción
					scanner.nextLine();
				}
			} while (!esNumeroPaginasCorrecto);
			
			do {
				//Introducción de formato
				System.out.print("Formato (digital o papel): ");
				stringFormato = scanner.nextLine();
				
				if(stringFormato.equalsIgnoreCase("digital") || stringFormato.equalsIgnoreCase("papel")) {
					esFormatoCorrecto = true;
					formato = stringFormato.equalsIgnoreCase("digital");
				} else {
					esFormatoCorrecto = false;
					System.out.println("El formato debe ser digital o papel");
				}
			} while (!esFormatoCorrecto);
			
			//Creación de libro en base a los datos introducidos
			Libro libro = new Libro(ultimoId, titulo, isbn, numeroPaginas, formato);
			
			//Mostrar libro
			System.out.println("¿Quieres guardar este libro? (S/n)");
			System.out.println(libro);
			
			//Si me dicen que sí, guardo el libro
			if(!scanner.nextLine().equalsIgnoreCase("n")) {
				//Añadir el libro a la colección de libros
				libros.put(ultimoId++, libro);
			}
			
			//Mostrar todos los libros
			System.out.println("LISTADO DE LIBROS");
			for (Libro libroExtraido : libros.values()) {
				System.out.println(libroExtraido);
			}
			
			//Preguntar si el usuario quiere introducir un nuevo registro
			System.out.print("¿Desea introducir un nuevo registro? (S/n) ");
			esNuevoRegistro = !scanner.nextLine().equalsIgnoreCase("n");
		} while (esNuevoRegistro);
		
		System.out.println("Saliendo de la aplicación. Gracias por usar esta aplicación");
		
		scanner.close();
	}

}
