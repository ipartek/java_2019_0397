<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/vistas/includes/cabecera.jsp"%>

<article class="row">
	<h2 class="d-none">Listados</h2>
	<section id="sesiones" class="p-lg-4 col-lg-6 col-xl-12">
		<h3>Sesiones</h3>
		<table
			class="table table-striped table-bordered table-hover table-sm table-responsive-xl">
			<thead class="thead-dark">
				<tr>
					<th>Id</th>
					<th>Cliente</th>
					<th>Trabajador</th>
					<th>Servicio</th>
					<th>Fecha</th>
					<th>Reseña</th>
					<th>Calificación</th>
					<th>Opciones</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${sesiones}" var="s">
					<tr>
						<td>${s.id}</td>
						<td>${s.cliente.nombre}${s.cliente.apellidos}</td>
						<td>${s.trabajador.nombre}${s.trabajador.apellidos}</td>
						<td>${s.servicio.nombre}</td>
						<td><fmt:formatDate value="${s.fecha}" pattern="dd-MM-yyyy" /></td>
						<td><a href="javascript:alert('${s.resena}')">${fn:substring(s.resena, 0, 20)}...</a></td>
						<td>${s.calificacion}</td>
						<td><a href="vistas/sesion.jsp"
							class="btn btn-primary btn-sm">Editar</a> <a href="#"
							class="btn btn-danger btn-sm">Borrar</a></td>
					</tr>
				</c:forEach>
			</tbody>
			<tfoot class="thead-dark">
				<tr>
					<th>Id</th>
					<th>Cliente</th>
					<th>Trabajador</th>
					<th>Servicio</th>
					<th>Fecha</th>
					<th>Reseña</th>
					<th>Calificación</th>
					<th><a href="vistas/sesion.jsp" class="btn btn-primary btn-sm">Añadir</a>
					</th>
				</tr>
			</tfoot>
		</table>
	</section>
	<section id="clientes" class="p-lg-4 col-lg-6 col-xl-4">
		<h3>Clientes</h3>
		<table
			class="table table-striped table-bordered table-hover table-sm table-responsive-xl">
			<thead class="thead-dark">
				<tr>
					<th>Id</th>
					<th>Nombre</th>
					<th>Apellidos</th>
					<th>DNI</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>1</td>
					<td>Cliente Nombre1</td>
					<td>Cliente Apellidos1</td>
					<td>12345678Z</td>
				</tr>
				<tr>
					<td>2</td>
					<td>Cliente Nombre2</td>
					<td>Cliente Apellidos2</td>
					<td>DNI2</td>
				</tr>
			</tbody>
			<tfoot class="thead-dark">
				<tr>
					<th>Id</th>
					<th>Nombre</th>
					<th>Apellidos</th>
					<th>DNI</th>
				</tr>
			</tfoot>
		</table>
	</section>
	<section id="trabajadores" class="p-lg-4 col-lg-6 col-xl-4">
		<h3>Trabajadores</h3>
		<table
			class="table table-striped table-bordered table-hover table-sm table-responsive-xl">
			<thead class="thead-dark">
				<tr>
					<th>Id</th>
					<th>Nombre</th>
					<th>Apellidos</th>
					<th>DNI</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>1</td>
					<td>Trabajador Nombre1</td>
					<td>Trabajador Apellidos1</td>
					<td>TDNI1</td>
				</tr>
				<tr>
					<td>2</td>
					<td>Trabajador Nombre2</td>
					<td>Trabajador Apellidos2</td>
					<td>TDNI2</td>
				</tr>
			</tbody>
			<tfoot class="thead-dark">
				<tr>
					<th>Id</th>
					<th>Nombre</th>
					<th>Apellidos</th>
					<th>DNI</th>
				</tr>
			</tfoot>
		</table>
	</section>
	<section id="servicios" class="p-lg-4 col-lg-6 col-xl-4">
		<h3>Servicios</h3>
		<table
			class="table table-striped table-bordered table-hover table-sm table-responsive-xl">
			<thead class="thead-dark">
				<tr>
					<th>Id</th>
					<th>Nombre</th>
					<th>Precio</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>1</td>
					<td>Un servicio</td>
					<td>11,11 €</td>
				</tr>
				<tr>
					<td>2</td>
					<td>Otro servicio</td>
					<td>122,22 €</td>
				</tr>
			</tbody>
			<tfoot class="thead-dark">
				<tr>
					<th>Id</th>
					<th>Nombre</th>
					<th>Precio</th>
				</tr>
			</tfoot>
		</table>
	</section>
</article>

<%@ include file="/WEB-INF/vistas/includes/pie.jsp"%>
