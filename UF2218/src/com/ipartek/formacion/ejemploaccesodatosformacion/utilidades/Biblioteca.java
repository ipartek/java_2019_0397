package com.ipartek.formacion.ejemploaccesodatosformacion.utilidades;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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
				
				i = Integer.parseInt(s.nextLine()); 
				
			} catch (NumberFormatException e) {
				correcto = false;
				
				System.out.println("El dato introducido no es un número entero");
			}
		} while (!correcto);
		
		return i;
	}

	public static String leerLinea(String mensaje) {
		System.out.print(mensaje);
		return s.nextLine();
	}

	public static Date leerDate(String mensaje) {
		System.out.println(mensaje);
		
		boolean correcto;
		
		int dia, mes, anyo;
		
		do {
			correcto = true;
			dia = leerEntero("Día: ");
			
			if(dia < 1 || dia > 31) {
				correcto = false;
				System.out.println("El día debe estar entre 1 y 31");
			}
		} while(!correcto);

		do {
			correcto = true;
			mes = leerEntero("Mes (en número): ");
			
			if(mes < 1 || mes > 12) {
				correcto = false;
				System.out.println("El mes debe ser entre 1 y 12");
			}
		} while(!correcto);
		
		anyo = leerEntero("Año: ");
		
		Calendar fecha = new GregorianCalendar(anyo, mes - 1, dia);
		
		return fecha.getTime();
	}
}
