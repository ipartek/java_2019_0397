<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Calculadora</title>
</head>
<body>
	<form action="calculadora" method="get">
		<input type="number" name="op1" value="${op1}"/>
		<select name="op">
			<option value="s" ${op == "s" ? "selected": ""}>+</option>
			<option ${op == "-" ? "selected": ""}>-</option>
			<option ${op == "*" ? "selected": ""}>*</option>
			<option ${op == "/" ? "selected": ""}>/</option>
		</select>
		<input type="number" name="op2" value="${op2}"/>
		<button>=</button>
		<input disabled value="<%=request.getAttribute("resultado") == null ? "" : request.getAttribute("resultado") %>" />
	</form>
</body>
</html>