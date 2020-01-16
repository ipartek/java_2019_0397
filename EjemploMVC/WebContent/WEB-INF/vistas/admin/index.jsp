<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/vistas/includes/cabecera.jsp"%>

<section id="videos">

	<table
		class="table table-striped table-bordered table-hover table-sm table-responsive">
		<thead>
			<tr>
				<th>Id</th>
				<th>Nombre</th>
				<th>URL</th>
				<th>Imagen</th>
				<th>Opciones</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${videos}" var="video">
				<tr>
					<th>${video.id}</th>
					<td>${video.nombre}</td>
					<td><a href="${video.url}">${video.url}</a></td>
					<td><img class="img-thumbnail" style="height: 4rem;" src="imgs/${video.id}.jpg" /></td>
					<td><a class="btn btn-warning" href="#">Modificar</a> <a
						class="btn btn-danger" href="#">Borrar</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

	<a class="btn btn-primary" href="#">Añadir</a>
	<!-- ID: ${video.id} -->
	<%--
			<c:forEach items="${videos}" var="video">
			<div class="card-body">
				<img src="imgs/${video.id}.jpg" class="card-img-top" alt="">
				<h3 class="card-title">${video.nombre}</h3>
				<p class="card-text">
					<a href="${video.url}">${video.url}</a>
				</p>
			</div>
			
	</article>
	</c:forEach>
	 --%>
</section>
<%@ include file="/WEB-INF/vistas/includes/pie.jsp"%>
