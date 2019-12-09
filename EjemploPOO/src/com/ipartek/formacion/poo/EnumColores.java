package com.ipartek.formacion.poo;

enum Color {
	ROJO, VERDE, AZUL
}

public class EnumColores {
	public static void main(String[] args) {
		Color color = Color.ROJO;

		switch (color) {
		case ROJO:
			System.out.println("Rojo");
			break;
		default:
			System.out.println("Otro color");
		}

		System.out.println(color);
	}
}