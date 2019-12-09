package com.ipartek.formacion.poo.interfaces;

public interface Comestible {
	public default void comer() {
		System.out.println("Comiendo algo");
	}
}
