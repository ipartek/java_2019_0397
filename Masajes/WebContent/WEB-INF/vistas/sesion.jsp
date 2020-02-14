<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/vistas/includes/cabecera.jsp"%>

<div class="row">
	<form action="sesion" method="post"
		class="offset-xl-3 offset-md-2 offset-sm-1 col-sm-10 col-md-8 col-xl-6">
		<fieldset>
			<legend>Sesión</legend>

			<div class="form-group row">
				<label for="id" class="col-sm-2 col-form-label">Id</label>
				<div class="col-sm-10">
					<input type="number" class="form-control" id="id" name="id"
						value="${sesion.id}" readonly>
				</div>
			</div>

			<div class="form-group row">
				<label for="cliente" class="col-sm-2 col-form-label">Cliente</label>
				<div class="col-sm-10">
					<select
						class="form-control<%-- ${primeravez ? '' : (video.errorUrl == null ? 'is-valid' : 'is-invalid') } --%>"
						id="cliente" name="cliente">
						<c:forEach items="${clientes}" var="cliente">
							<option ${cliente.id == sesion.cliente.id ? 'selected': '' } value="${cliente.id}">${cliente.nombre} ${cliente.apellidos}</option>
						</c:forEach>
					</select>
				</div>
			</div>

			<div class="form-group row">
				<label for="trabajador" class="col-sm-2 col-form-label">Trabajador</label>
				<div class="col-sm-10">
					<select
						class="form-control<%-- ${primeravez ? '' : (video.errorUrl == null ? 'is-valid' : 'is-invalid') } --%>"
						id="trabajador" name="trabajador">
						<c:forEach items="${trabajadores}" var="trabajador">
							<option ${trabajador.id == sesion.trabajador.id ? 'selected': '' } value="${trabajador.id}">${trabajador.nombre} ${trabajador.apellidos}</option>
						</c:forEach>
					</select>
				</div>
			</div>

			<div class="form-group row">
				<label for="servicio" class="col-sm-2 col-form-label">Calificación</label>
				<div class="col-sm-10">
					<select
						class="form-control<%-- ${primeravez ? '' : (video.errorUrl == null ? 'is-valid' : 'is-invalid') } --%>"
						id="servicio" name="servicio">
						<c:forEach items="${servicios}" var="servicio">
							<option ${servicio.id == sesion.servicio.id ? 'selected': '' } value="${servicio.id}">${servicio.nombre}</option>
						</c:forEach>
					</select>
				</div>
			</div>

			<div class="form-group row">
				<label for="nombre" class="col-sm-2 col-form-label">Fecha</label>
				<div class="col-sm-10">
					<input type="datetime-local"
						class="form-control is-invalid <%-- ${primeravez ? '' : (video.errorNombre == null ? 'is-valid' : 'is-invalid') } --%>"
						id="nombre" name="nombre"
						value="<fmt:formatDate value="${sesion.fecha}" pattern="yyyy-MM-dd'T'HH:mm" />" />
					<div class="invalid-feedback">Fecha no válida</div>
				</div>
			</div>
			<div class="form-group row">
				<label for="resena" class="col-sm-2 col-form-label">Reseña</label>
				<div class="col-sm-10">
					<textarea
						class="form-control is-invalid <%-- ${primeravez ? '' : (video.errorUrl == null ? 'is-valid' : 'is-invalid') } --%>"
						id="resena" name="resena">${sesion.resena}</textarea>
					<div class="invalid-feedback">¿Puedes escribir un poco más?</div>
				</div>
			</div>
			<div class="form-group row">
				<label for="calificacion" class="col-sm-2 col-form-label">Calificación</label>
				<div class="col-sm-10">
					<select
						class="form-control<%-- ${primeravez ? '' : (video.errorUrl == null ? 'is-valid' : 'is-invalid') } --%>"
						id="calificacion" name="calificacion">
						<option></option>
						<option
							${sesion.calificacion == 'No recomendable' ? 'selected': '' }>No
							recomendable</option>
						<option ${sesion.calificacion == 'Aceptable' ? 'selected': '' }>Aceptable</option>
						<option ${sesion.calificacion == 'Para repetir' ? 'selected': '' }>Para
							repetir</option>
					</select>
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
