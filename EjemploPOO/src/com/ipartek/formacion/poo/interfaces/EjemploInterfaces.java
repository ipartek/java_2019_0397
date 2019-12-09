package com.ipartek.formacion.poo.interfaces;

public class EjemploInterfaces {

	public static void main(String[] args) {
		Rodable[] arr = new Rodable[2];
		
		arr[0] = new Naranja();
		arr[1] = new Balon();
		
		for(Rodable r: arr) {
			r.rodar();
			
			if(r instanceof Comestible) {
				((Comestible)r).comer();
			}
		}
 	}

}
