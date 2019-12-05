package com.ipartek.formacion.poo;

public class EjemploExcepciones {
	@SuppressWarnings("null")
	public static void main(String[] args) {
		try {
			int[] arr = new int[2];
			
			arr[2] = 5;
			
			String s = null;
			
			System.out.println(s.toUpperCase());
			
			int div, a, b;
			
			a = 5; b = 0;
			
			div = a / b;
			
			System.out.println(div);
		} catch (ArithmeticException e) {
			System.out.println("Se ha encontrado una división por cero");
		} catch(NullPointerException npe) {
			System.out.println("Ha habido un error de puntero nulo");
		} catch(Exception e) {
			System.out.println("ERROR NO ESPERADO");
			System.out.println(e.getMessage());
			e.printStackTrace();
		} finally {
			System.out.println("Me ejecuto SIEMPRE");
		}
		
		System.out.println("FIN DE PROGRAMA");
	}
}
