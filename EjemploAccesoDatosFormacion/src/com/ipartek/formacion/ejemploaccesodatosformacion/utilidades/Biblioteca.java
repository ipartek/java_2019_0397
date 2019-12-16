package com.ipartek.formacion.ejemploaccesodatosformacion.utilidades;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Biblioteca {

	private static final String LETRAS_DNI = "TRWAGMYFPDXBNJZSQVHLCKE";
	
	private static Scanner s = new Scanner(System.in);
	
	public static boolean dniValido(String dni) {
		int numero;
		char letra;
		char letraCalculada;
		
		letra = dni.charAt(8);
		
		// TODO Intentar mejorar los replaces múltiples
		numero = Integer.parseInt(dni.substring(0, 8).replace('X',  '0').replace('Y', '1').replace('Z', '2'));
		
		letraCalculada = LETRAS_DNI.charAt(numero % 23);
		
		return letraCalculada == letra;
	}

	public static int leerEntero(String mensaje) {
		
		int i = 0;
		
		boolean correcto = true;
		
		do {
			System.out.print(mensaje);
			
			try {
				correcto = true;
				
				i = s.nextInt();
				s.nextLine();
				
			} catch (InputMismatchException e) {
				correcto = false;
				
				System.out.println("El dato introducido no es un número entero");
				s.nextLine();
			}
		} while (!correcto);
		
		return i;
	}

}
