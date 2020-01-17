<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<title>EjemploMVC</title>

<base href="${pageContext.request.contextPath}/" />

<!-- Bootstrap 4 -->
<link rel="stylesheet" href="css/bootstrap.min.css" />
<!-- DataTables con aspecto Bootstrap 4 -->
<link rel="stylesheet" href="css/dataTables.bootstrap4.min.css" />
<!-- Font Awesome -->
<link rel="stylesheet" href="css/all.min.css" />

<link rel="stylesheet" href="css/ejemplomvc.css" />

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
		<h1>EjemploMVC</h1>
	</header>
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark sticky-top">
		<a class="navbar-brand" href="#">EjemploMVC</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>

		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav mr-auto">
				<li class="nav-item"><a class="nav-link" href="admin/index">Administración</a></li>
			</ul>
			<form class="form-inline my-2 my-lg-0">
				<input class="form-control mr-sm-2" type="search"
					placeholder="Search" aria-label="Search">
				<button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
			</form>

			<c:choose>
				<c:when test="${sessionScope.email != null}">
					<span class="navbar-text ml-3"> ${sessionScope.email} </span>
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
	<c:if test="${alertatexto != null}">
		<div class="alert alert-${alertanivel} alert-dismissible fade show"
			role="alert">
			${alertatexto}
			<button type="button" class="close" data-dismiss="alert"
				aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
		</div>

		<%
			session.removeAttribute("alertatexto");
				session.removeAttribute("alertanivel");
		%>
	</c:if>
	<main class="container-fluid">