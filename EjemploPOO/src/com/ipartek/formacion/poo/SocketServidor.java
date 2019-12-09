package com.ipartek.formacion.poo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServidor {

	private static final boolean AUTO_FLUSH = true;

	public static void main(String[] args) {
		try (ServerSocket ss = new ServerSocket(1234)) {
			System.out.println("Servidor MAYUSCULADOR ACTIVADO");
			
			Socket s = ss.accept();
			
			System.out.println("Procesando petición");
			
			PrintWriter pw = new PrintWriter(s.getOutputStream(), AUTO_FLUSH);
			BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
			
			pw.println("Bienvenido al servidor MAYUSCULADOR");
			//pw.flush();
			
			String texto = br.readLine();
			pw.println(texto.toUpperCase());
		} catch (IOException e) {
			System.out.println("Error de entrada salida");
			return;
		}
	}

}
