<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%-- Cargamos las biblioteca de etiquetas JSTL (Java Standard Tag Library) que están en WEB-INF/lib/jstl-12.jar --%>
<%-- Recordad dar a refresh en WEB-INF/lib y luego botón derecho en jstl-12.jar Add to Build Path --%>

<%-- La biblioteca core tiene el prefijo c y contiene etiquetas como c:forEach y c:if --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%-- La biblioteca fmt tiene el prefijo fmt y contiene etiquetas como fmt:formatNumber y fmt:formatDate --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Alumnos</title>
</head>
<body>

	<table>
		<thead>
			<tr>
				<th>Id</th>
				<th>Nombre</th>
				<th>Apellidos</th>
				<th>DNI</th>
				<th>Fecha de nacimiento</th>
				<th>Opciones</th>
			</tr>
		</thead>
		<tbody>
		<%-- 
			items: colección a recorrer 
			var: variable que contiene cada uno de los elementos
			
			${} -> EL (Expression Language)
			
			${alumnos} == request.getAttribute("alumnos")
			
			for(Alumno alumno: alumnos)
		--%>
			<c:forEach items="${alumnos}" var="alumno">
				<tr>
					<%-- ${alumno.id} == alumno.getId() == null ? "" : alumno.getId() --%>
					<th>${alumno.id}</th>
					<td>${alumno.nombre}</td>
					<td>${alumno.apellidos}</td>
					<td>${alumno.dni}</td>
					<td><fmt:formatDate pattern="yyyy-MM-dd"
							value="${alumno.fechaNacimiento}" /></td>
					<td>
						<a href="alumnos?id=${alumno.id}&op=modificar">Modificar</a>
						<a href="alumnos?id=${alumno.id}&op=borrar">Borrar</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	
	<a href="alumnos?op=agregar">Añadir</a>

</body>
</html>