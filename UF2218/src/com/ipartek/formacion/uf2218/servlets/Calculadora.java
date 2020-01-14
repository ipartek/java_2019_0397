package com.ipartek.formacion.uf2218.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/calculadora")
public class Calculadora extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		
		try {
			String op1 = request.getParameter("op1");
			String op2 = request.getParameter("op2");
			
			String op = request.getParameter("op");
			
			System.out.println("[" + op1 + "]");
			System.out.println("[" + op2 + "]");
			System.out.println("[" + op + "]");
			
			if(op1 == null || op2 == null || op == null) {
				throw new RuntimeException("Me falta op1, op2 u op");
				
				/*out.println("Me falta op1, op2 u op");
				return;*/
			}
			
			double num1 = Double.parseDouble(op1);
			double num2 = Double.parseDouble(op2);
			
			double res = 0.0;
			
			switch(op) {
			case "s": res = num1 + num2; break;
			case "-": res = num1 - num2; break;
			case "*": res = num1 * num2; break;
			case "/": res = num1 / num2; break;
			default: out.println("Operación no reconocida"); return;
			}
			
			out.print("Resultado: ");
			
			if(Double.isNaN(res)) {
				out.println("No es un número");
			} else if (Double.isInfinite(res)) {
				out.print("Infinito ");
				out.println(res >= 0 ? "positivo" : "negativo");
			} else {
				out.println(res);
			}
		} catch (NumberFormatException e) {
			out.println("Tienes que introducir números");
		} catch(Exception e) {
			out.println("Error no esperado");
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
