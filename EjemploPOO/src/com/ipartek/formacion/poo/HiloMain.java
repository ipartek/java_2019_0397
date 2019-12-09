package com.ipartek.formacion.poo;

public class HiloMain {
	public static void main(String[] args) {
		HiloCorredor c1 = new HiloCorredor(1);
		HiloCorredor c2 = new HiloCorredor(2);
		
		Thread t1 = new Thread(c1);
		Thread t2 = new Thread(c2);
		
		t1.start();
		t2.start();
		
		while(c1.getPosicion() < 11 && c2.getPosicion() < 11) {
			System.out.println(c1);
			System.out.println(c2);
			
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		System.out.println("Ha terminado la carrera");
	}
}
