package com.ipartek.formacion.ejemplosbasicos;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class Basico {

	public static void main(String[] args) {
		sentenciasDeControl();
	}

	private static void sentenciasDeControl() {
		boolean esCursoActivo = false;
		boolean esAulaDisponible = true;

		if (esCursoActivo) {
			System.out.println("El curso está activo");
		} else if (esAulaDisponible) {
			System.out.println("Aula disponible");
		} else {
			System.out.println("Ni curso ni aula disponible");
		}

		int opcion = 1;

		switch (opcion) {
		case 1:
			System.out.println("Opción 1");
			break;
		case 2:
			System.out.println("Opción 2");
			break;
		default:
			System.out.println("Otra opción");
		}

		int dias, mes = 4;

		switch (mes) {
		case 2:
			dias = 28;
			break;
		case 4:
		case 6:
		case 9:
		case 11:
			dias = 30;
			break;
		default:
			dias = 31;
		}

		System.out.println(dias);

		int c = 0;
		while (++c <= 10) {
			System.out.println(c);
		}

		c = 0;
		boolean repetir;

		do {
			c++;

			if (c >= 10) {
				repetir = false;
			} else {
				repetir = true;
			}

			System.out.println(c);
		} while (repetir);

		for (int n = 1; n <= 10; n++) {
			System.out.println("For " + n);
		}

		int n = 1;
		while (n <= 10) {
			System.out.println("While " + n);
			n++;
		}

		for (int x = 1, y = 10; x + y < 100; x *= 2, y--, System.out.println("A repetir")) {
			System.out.println(x + ", " + y);
		}

//		for(;;) {
//			System.out.println("A REPETIIIIIIIIR");
//		}

		int[] arr = { 2, 5, 7, 89, 0 };

		for (int dato : arr) {
			System.out.println(dato);
		}

		for (int i = 0; i < arr.length; i++) {
			int dato = arr[i];
			System.out.println(dato);
		}

		int buscado = 89;

		for (int dato : arr) {
			System.out.println("Comprobando..." + dato);

			if (dato == buscado) {
				System.out.println("ENCONTRADO");
				break;
			}
		}

		boolean encontrado = false;

		for (int i = 0; i < arr.length && !encontrado; i++) {
			int dato = arr[i];
			System.out.println("Comprobando..." + dato);

			if (dato == buscado) {
				System.out.println("ENCONTRADO");
				encontrado = true;
			}
		}

		estebucle: for (int dato : arr) {

			if (dato == buscado) {
				System.out.println("ESTE NO");
				continue estebucle;
			}
			
			System.out.println("Mostrando..." + dato);
		}
	}

	@SuppressWarnings("unused")
	private static void operadores() {
		int i = 5 ^ 3; // 101 XOR 011

		System.out.println(i); // 110 -> 6

		int j = 5 & 3; // 101 AND 011

		System.out.println(j); // 001

		byte k = ~5; // 00000101

		System.out.println(k); // 11111010

		int x = 5;

		System.out.println((1 + x) + 5);

		System.out.println(1 <= x && x <= 5);

		@SuppressWarnings("unused")
		int b, c;
		@SuppressWarnings("unused")
		int a = b = c = 0;

		x += 7; // x = x + 7

		System.out.println(x);

		System.out.println(x++);

		System.out.println(x);

		++x;
		int y = 5 + x++ + (c *= 3);

		System.out.println(y);

		a = 5;
		b = 3;

		int max = a > b ? a : b;

		System.out.println(max);

		boolean bool = true;

		System.out.println(bool ? "VERDADERO" : "FALSO");
	}

	@SuppressWarnings("unused")
	private static void tiposComunes() {
		String s1 = "Hola";
		String s2 = new String("Hola");

		System.out.println(s1.equals(s2)); // s1 == s2);

		s2 = "Pepe";

		System.out.println(s1 + s2);

		String ruta = "C:\\nuevos\\trabajos";

		System.out.println(ruta.length());

		s2 = null;

		System.out.println("Pepe".equals(s2));

		Date d = new Date();

		System.out.println(DateFormat.getInstance().format(d));// d.toGMTString());

		int tam = 2;

		int[] arr = new int[tam];

		arr[0] = 123;
		arr[1] = 234;
		// arr[2] = 345;

		System.out.println(arr.length);

		char[][] tablero = new char[8][8];

		tablero[0][0] = 'T';
		tablero[1][0] = 'p';

		ArrayList<String> al = new ArrayList<String>();

		al.add("Uno");
		al.add("Dos");
		al.add("Tres");

		System.out.println(al.get(0));
		System.out.println(al.size());

		HashMap<String, String> map = new HashMap<>();

		map.put("uno", "one");
		map.put("dos", "two");

		System.out.println(map.get("dos"));
	}

	@SuppressWarnings("unused")
	private static void tiposPrimitivos() {
		int i;
		i = 5;

		@SuppressWarnings("unused")
		int x = 0x126AB;

		@SuppressWarnings("unused")
		long a, ccc;

		System.out.println(i);

		@SuppressWarnings("unused")
		double d = 5.0;

		@SuppressWarnings("unused")
		boolean b = true;

		@SuppressWarnings("unused")
		char c = '\n';
	}
}
