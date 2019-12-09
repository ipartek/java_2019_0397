package com.ipartek.formacion.poo;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class EjemploIO {

	private static final String FICHERO = "C:\\pruebas\\prueba.txt";

	public static void main(String[] args) {
		ficheros();
		// entradaConsola();
	}

	private static void ficheros() {
		FileWriter fw = null;
		PrintWriter pw = null;

		try {
			fw = new FileWriter(FICHERO);
			pw = new PrintWriter(fw);
			// fw.append("UNO");
			// fw.append("DOS");
			pw.println("UNO");
			pw.println("DOS");
		} catch (IOException e) {
			System.out.println("Error al escribir el fichero");
			return;
		} finally {
			if (pw != null) {
				pw.close();
			}

			if (fw != null) {
				try {
					fw.close();
				} catch (IOException e) {
					System.out.println("No se ha podido cerrar el fichero");
					return;
				}
			}
		}

		try (FileReader fr = new FileReader(FICHERO)) {
			try (BufferedReader br = new BufferedReader(fr)) {

				String linea;

				while ((linea = br.readLine()) != null) {
					System.out.println(linea);
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("No se ha encontrado el fichero");
			return;
		} catch (IOException e) {
			System.out.println("No se ha podido leer el fichero");
			return;
		}
		
//		br.close();
//		fr.close();
	}

	public static void entradaConsola() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		System.out.print("Introduce tu nombre: ");

		String nombre = br.readLine();

		System.out.println("Hola, " + nombre);

		System.out.print("Dime el número del curso: ");

		String num = br.readLine();

		if (num.matches("\\d*")) {
			System.out.println("OK");
			int numCurso = Integer.parseInt(num);

			System.out.println("El curso es el " + numCurso);
		} else {
			System.out.println("NO");
		}
	}
}
