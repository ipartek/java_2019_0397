package com.ipartek.formacion.ejemplojaxws;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface Calculadora {
	@WebMethod
	int sumar(int a, int b);
}
