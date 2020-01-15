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
		PrintWriter out;
		String resultado, op1 = null , op2 = null, op = null;
		
		try {
			out = response.getWriter();
			
			op1 = request.getParameter("op1");
			op2 = request.getParameter("op2");
			
			op = request.getParameter("op");
			
			System.out.println("[" + op1 + "]");
			System.out.println("[" + op2 + "]");
			System.out.println("[" + op + "]");
			
			if(op1 == null || op2 == null || op == null) {
				throw new RuntimeException("Me falta op1, op2 u op");
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
			
			if(Double.isNaN(res)) {
				resultado = "No es un número";
			} else if (Double.isInfinite(res)) {
				resultado = res >= 0 ? "Infinito positivo" : "Infinito negativo";
			} else {
				resultado = String.valueOf(res);
			}
		} catch (NumberFormatException e) {
			resultado = "Tienes que introducir números";
		} catch(Exception e) {
			resultado = "Error no esperado";
			e.printStackTrace();
		}
		
		request.setAttribute("resultado", resultado);
		request.setAttribute("op1", op1);
		request.setAttribute("op2", op2);
		request.setAttribute("op", op);
		
		request.getRequestDispatcher("calculadora.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
