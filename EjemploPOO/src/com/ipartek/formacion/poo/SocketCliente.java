package com.ipartek.formacion.poo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class SocketCliente {

	private static final boolean AUTO_FLUSH = true;

	public static void main(String[] args) {
		try (Socket s = new Socket("localhost", 1234)) {
			System.out.println("Conectado al servidor");
			
			PrintWriter pw = new PrintWriter(s.getOutputStream(), AUTO_FLUSH);
			BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
			
			System.out.println(br.readLine());
			pw.println("Javier");
			System.out.println(br.readLine());
		} catch (IOException e) {
			System.out.println("Error de entrada salida");
			return;
		}
	}

}
