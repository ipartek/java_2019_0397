package com.ipartek.formacion.poo;

import java.io.*;
import java.util.ArrayList;

public class EjemploIOObjetos {

	private static final String FICHERO = "C:\\datos\\puntos.pun";

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		ArrayList<Punto> puntos = new ArrayList<>();
		
		puntos.add(new Punto());
		puntos.add(new Punto(2,3,4));
		puntos.add(new Punto(4,5,6));
		
		FileOutputStream fos = new FileOutputStream(FICHERO);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		
		oos.writeObject(puntos);
		
		oos.close();
		fos.close();
		
		FileInputStream fis = new FileInputStream(FICHERO);
		ObjectInputStream ois = new ObjectInputStream(fis);
		
		@SuppressWarnings("unchecked")
		ArrayList<Punto> leido = (ArrayList<Punto>) ois.readObject();
		
		for(Punto p: leido) {
			System.out.println(p);
		}
		
		ois.close();
		fis.close();
	}

}
