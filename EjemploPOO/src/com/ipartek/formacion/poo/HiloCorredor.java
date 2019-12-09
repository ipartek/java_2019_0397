package com.ipartek.formacion.poo;

public class HiloCorredor implements Runnable {
	private int dorsal;
	private int pos;
	
	public HiloCorredor(int dorsal) {
		this.dorsal = dorsal;
	}
	
	public int getPosicion() {
		return pos;
	}
	
	public int getDorsal() {
		return dorsal;
	}
	
	@Override
	public void run() {
		for(pos = 1; pos <= 10; pos++) {
			
			try {
				Thread.sleep((int)(Math.random() * 500));
			} catch (InterruptedException e) { }
		}
	}

	@Override
	public String toString() {
		return "HiloCorredor [dorsal=" + dorsal + ", pos=" + pos + "]";
	}
}
