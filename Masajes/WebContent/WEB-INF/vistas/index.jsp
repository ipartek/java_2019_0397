<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/vistas/includes/cabecera.jsp"%>

<article class="row">
	<section id="sesiones" class="p-lg-4 col-lg-6 col-xl-12">
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
				<tr>
					<td>Id1</td>
					<td>Cliente1</td>
					<td>Trabajador1</td>
					<td>Servicio1</td>
					<td>31-12-2020</td>
					<td>Reseña1</td>
					<td>Calificación1</td>
					<td>
						<a href="vistas/sesion.jsp" class="btn btn-primary">Editar</a>
						<a href="#" class="btn btn-danger">Borrar</a>
					</td>
				</tr>
				<tr>
					<td>Id2</td>
					<td>Cliente2</td>
					<td>Trabajador2</td>
					<td>Servicio2</td>
					<td>02-02-2020</td>
					<td>Reseña2</td>
					<td>Calificación2</td>
					<td>
						<a href="vistas/sesion.jsp" class="btn btn-primary">Editar</a>
						<a href="#" class="btn btn-danger">Borrar</a>
					</td>
				</tr>
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
					<th>Opciones</th>
				</tr>
			</tfoot>
		</table>
		
		<a href="vistas/sesion.jsp" class="btn btn-primary">Añadir</a>
	</section>
	<section id="clientes" class="p-lg-4 col-lg-6 col-xl-4">
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
