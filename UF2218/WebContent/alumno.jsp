<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Alumno</title>
</head>
<body>

<form action="alumno">
	<input readonly name="id" type="number" value="${alumno.id}" />
	<input name="nombre" value="${alumno.nombre}"/>
	<input name="apellidos" value="${alumno.apellidos}" />
	<input name="dni" value="${alumno.dni}"/>
	<input name="fechanacimiento" type="date" value='<fmt:formatDate pattern="yyyy-MM-dd"
							value="${alumno.fechaNacimiento}" />' />
	<button>Aceptar</button>
</form>

</body>
</html>