package com.ipartek.formacion.ejemplojaxws;

import java.net.MalformedURLException;
import java.net.URL;

public class Main {

	public static void main(String[] args) throws MalformedURLException {
		URL url = new URL("http://localhost:8080/calculadora?wsdl");
		
		CalculadoraImplService servicio = new CalculadoraImplService(url);
		
		CalculadoraImpl calculadora = servicio.getCalculadoraImplPort();
		
		System.out.println(calculadora.sumar(2, 3));
	}

}
