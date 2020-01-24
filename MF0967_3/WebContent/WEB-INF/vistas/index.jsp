<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/vistas/includes/cabecera.jsp"%>

<header class="jumbotron">
	<h2 class="display-6">Bienvenidos a la librería</h2>
</header>

<section id="libros" class="row">
	<c:forEach items="${libros}" var="libro">
		<article class="libro col-12 col-sm-6 col-md-4 col-lg-3 col-xl-2">
			<img class="fotolibro" src="imgs/${libro.imagen}" alt="">
			<div class="d-flex justify-content-between">
				<p class="descripcion">${libro.nombre}</p>
				<i class="col-1 far fa-heart"></i>
			</div>
			<p class="autor">${libro.autor}</p>
			<p>
				<span class="precio-final"> <fmt:formatNumber type="currency"
						value="${libro.precio * (1 - libro.descuento / 100)}" />
				</span> <span class="precio"> <fmt:formatNumber type="currency"
						value="${libro.precio}" />
				</span> <span class="descuento">-${libro.descuento}%</span>
			</p>
		</article>
	</c:forEach>
</section>
<%@ include file="/WEB-INF/vistas/includes/pie.jsp"%>
