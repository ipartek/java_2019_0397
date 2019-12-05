package com.ipartek.formacion.poo;

import java.util.*;

import static java.lang.Math.*;

@SuppressWarnings("unused")
public class EjemplosConversionTipos {

	public static void main(String[] args) {
		int i = 5;
		String strI = String.valueOf(i);
		//String strI = ((Integer)i).toString(); // Válido pero más lento
		//String strI = "" + i; // Más lento todavía
		
		Integer integer = i;
		String strInteger = integer.toString();
		
		String str = "5";
		int iStr = Integer.parseInt(str);
		
		Persona p = new Persona("Javier", "Lete");
		Empleado e = new Empleado(p); //(Empleado)p; NO FUNCIONA
		
		System.out.println(e.toString());
		
		Persona p2 = e;
		
		double d = 5.7;
		
		i = (int)d;
		
		System.out.println(i);
		
		System.out.println(sqrt(round(12)));
	}

}
