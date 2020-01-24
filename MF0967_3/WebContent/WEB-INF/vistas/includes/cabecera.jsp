<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setLocale value = "es_ES"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<title>Librería</title>

<base href="${pageContext.request.contextPath}/" />

<!-- Bootstrap 4 -->
<link rel="stylesheet" href="css/bootstrap.min.css" />
<!-- DataTables con aspecto Bootstrap 4 -->
<link rel="stylesheet" href="css/dataTables.bootstrap4.min.css" />
<!-- Font Awesome -->
<link rel="stylesheet" href="css/all.min.css" />

<link rel="stylesheet" href="css/mf0967_3.css" />

<!-- jQuery -->
<script src="js/jquery-3.4.1.min.js"></script>
<!-- Bootstrap -->
<script src="js/bootstrap.bundle.min.js"></script>
<!-- DataTables -->
<script src="js/jquery.dataTables.min.js"></script>
<script src="js/dataTables.bootstrap4.min.js"></script>

<script>
	$(document).ready(function() {
		$('.table').DataTable();
	});
</script>

</head>
<body>
	<header>
		<h1>Librería</h1>
	</header>
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark sticky-top">
		<a class="navbar-brand" href="#">Librería</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>

		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav mr-auto">
				<li class="nav-item"><a class="nav-link" href="admin/listado">Administración</a></li>
			</ul>

			<c:choose>
				<c:when test="${sessionScope.usuario != null}">
					<span class="navbar-text ml-3"> ${sessionScope.usuario} </span>
					<ul class="navbar-nav">
						<li class="nav-item"><a class="nav-link" href="logout">Logout</a>
						</li>
					</ul>
				</c:when>
				<c:otherwise>
					<ul class="navbar-nav">
						<li class="nav-item"><a class="nav-link" href="login">Login</a>
						</li>
					</ul>
				</c:otherwise>
			</c:choose>

		</div>
	</nav>
	<c:if test="${mensaje != null}">
		<div
			class="alert alert-${mensaje.nivelBootstrap} alert-dismissible fade show"
			role="alert">
			${mensaje.texto}
			<button type="button" class="close" data-dismiss="alert"
				aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
		</div>

		<% session.removeAttribute("mensaje"); %>
	</c:if>
	<main class="container-fluid">