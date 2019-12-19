package com.ipartek.formacion.uf2216.presentacion;

import java.util.Scanner;
import java.util.TreeMap;

import com.ipartek.formacion.uf2216.entidades.Libro;

public class PresentacionConsola {

	private final static TreeMap<Long, Libro> libros = new TreeMap<>();
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("Título: ");
		String titulo = scanner.nextLine();
		
		System.out.print("ISBN: ");
		String isbn = scanner.nextLine();
		
		System.out.print("Número de páginas: ");
		int numeroPaginas = scanner.nextInt();
		scanner.nextLine();
		
		System.out.print("Formato (digital o papel): ");
		boolean formato = scanner.nextLine().equals("digital");
		
		Libro libro = new Libro(null, titulo, isbn, numeroPaginas, formato);
		
		libros.put(1L, libro);
		
		for(Libro libroExtraido: libros.values()) {
			System.out.println(libroExtraido);
		}
		
		scanner.close();
	}

}
