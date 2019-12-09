package com.ipartek.formacion.poo;

import java.math.BigDecimal;
import java.math.BigInteger;

public class EjemploClasesAbstractas {
	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		Number[] arr = new Number[4];
		
		arr[0] = new Integer(1);
		arr[1] = new Double(5.3);
		arr[2] = new BigDecimal("5.42");
		arr[3] = new BigInteger("56");
		
		for(Number n: arr) {
			System.out.println(n.intValue());
			System.out.println(n.doubleValue());
		}
	}
}
