package com.ipartek.formacion.ejemploservicioweb;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;

import javax.xml.rpc.ServiceException;

public class Main {

	public static void main(String[] args) throws ServiceException, RemoteException, MalformedURLException {
		URL  endpoint = new URL(
				"http://localhost:8080/EjemploServicioWeb/services/Calculadora");
		
		CalculadoraServiceLocator servicio = new CalculadoraServiceLocator(); 
		
		Calculadora calculadora = servicio.getCalculadora(endpoint);
		
		System.out.println(calculadora.sumar(3, 4));
	}

}
