package com.ipartek.formacion.poo.interfaces;

public class Naranja extends Fruto implements Rodable, Comestible {

	@Override
	public void comer() {
		System.out.println("¡ÑAM Que rica la naranja!");
	}

	@Override
	public void rodar() {
		System.out.println("Naranja rodando por el suelo");
	}
	
}
