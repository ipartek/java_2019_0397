package com.ipartek.formacion.uf2216.presentacion;

import java.util.Scanner;
import java.util.TreeMap;

import com.ipartek.formacion.uf2216.entidades.Libro;

public class PresentacionConsola {

	private final static TreeMap<Long, Libro> libros = new TreeMap<>();
	
	public static void main(String[] args) {
		Scanner scanner;
		long ultimoId = 1L;
		boolean repetir;
		
		do {
			//Scanner sobre la entrada estándar
			scanner = new Scanner(System.in);
			
			//Introducción de título
			System.out.print("Título: ");
			String titulo = scanner.nextLine();
			
			//Introducción de ISBN
			System.out.print("ISBN: ");
			String isbn = scanner.nextLine();
			
			//Introducción de número de páginas
			System.out.print("Número de páginas: ");
			int numeroPaginas = scanner.nextInt();
			scanner.nextLine();
			
			//Introducción de formato
			System.out.print("Formato (digital o papel): ");
			boolean formato = scanner.nextLine().equals("digital");
			
			//Creación de libro en base a los datos introducidos
			Libro libro = new Libro(ultimoId, titulo, isbn, numeroPaginas, formato);
			
			//Añadir el libro a la colección de libros
			
			libros.put(ultimoId++, libro);
			
			//Mostrar todos los libros
			for (Libro libroExtraido : libros.values()) {
				System.out.println(libroExtraido);
			}
			
			//Preguntar si el usuario quiere introducir un nuevo registro
			System.out.print("¿Desea introducir un nuevo registro? (S/n) ");
			repetir = !scanner.nextLine().equalsIgnoreCase("n");
		} while (repetir);
		
		System.out.println("Saliendo de la aplicación. Gracias por usar esta aplicación");
		
		scanner.close();
	}

}
