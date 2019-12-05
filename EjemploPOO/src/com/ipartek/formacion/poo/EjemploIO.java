package com.ipartek.formacion.poo;

import java.io.*;

public class EjemploIO {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		System.out.print("Introduce tu nombre: ");
		
		String nombre = br.readLine();
		
		System.out.println("Hola, " + nombre);
		
		System.out.print("Dime el número del curso: ");
		
		String num = br.readLine();
		
		if(num.matches("\\d*")) {
			System.out.println("OK");
			int numCurso = Integer.parseInt(num);
			
			System.out.println("El curso es el " + numCurso);
		} else {
			System.out.println("NO");
		}
		
		
	}

}
