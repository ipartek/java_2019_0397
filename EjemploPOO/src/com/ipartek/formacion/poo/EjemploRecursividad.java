package com.ipartek.formacion.poo;

import java.math.BigInteger;

public class EjemploRecursividad {
	public static void main(String[] args) {
		byte b = Byte.MAX_VALUE;
		b++;
		System.out.println(b);
		
		System.out.println(factorial(new BigInteger("1000")));
	}

	private static BigInteger factorial(BigInteger n) {
		if(BigInteger.ONE.equals(n)) {
			return BigInteger.ONE;
		}
		
		return n.multiply(factorial(n.subtract(BigInteger.ONE)));
	}
	
	
}
