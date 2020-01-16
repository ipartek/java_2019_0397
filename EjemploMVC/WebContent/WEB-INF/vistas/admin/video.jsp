<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/vistas/includes/cabecera.jsp"%>

<div class="row">
	<form action="admin/video" method="post"
		class="offset-xl-3 offset-md-2 offset-sm-1 col-sm-10 col-md-8 col-xl-6">
		<fieldset>
			<legend>Video</legend>

			<input type="hidden" id="op" name="op" value="${op}">

			<div class="form-group row">
				<label for="id" class="col-sm-2 col-form-label">Id</label>
				<div class="col-sm-10">
					<input type="number" class="form-control" id="id" name="id"
						value="${video.id}" readonly>
				</div>
			</div>
			<div class="form-group row">
				<label for="nombre" class="col-sm-2 col-form-label">Nombre</label>
				<div class="col-sm-10">
					<input class="form-control ${primeravez ? '' : (video.errorNombre == null ? 'is-valid' : 'is-invalid') }" id="nombre" name="nombre"
						value="${video.nombre}">
					<div class="invalid-feedback">${video.errorNombre}</div>
				</div>

			</div>
			<div class="form-group row">
				<label for="url" class="col-sm-2 col-form-label">URL</label>
				<div class="col-sm-10">
					<input type="url" class="form-control ${primeravez ? '' : (video.errorUrl == null ? 'is-valid' : 'is-invalid') }" id="url" name="url"
						value="${video.url}">
					<div class="invalid-feedback">${video.errorUrl}</div>
				</div>

			</div>
			<div class="form-group row">
				<div class="offset-sm-2 col-sm-10">
					<button type="submit" class="btn btn-primary">Aceptar</button>
				</div>
			</div>
		</fieldset>
	</form>
</div>
<%@ include file="/WEB-INF/vistas/includes/pie.jsp"%>
