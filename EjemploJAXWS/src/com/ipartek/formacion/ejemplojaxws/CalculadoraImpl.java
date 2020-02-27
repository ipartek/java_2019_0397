package com.ipartek.formacion.ejemplojaxws;

import javax.jws.WebService;

@WebService
public class CalculadoraImpl implements Calculadora {

	@Override
	public int sumar(int a, int b) {
		return a + b;
	}

}
