<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Listar Productos</title>
<link rel="stylesheet" type="text/css" href="css/listar.css">
<style>
.success-message {
	color: green;
	font-weight: bold;
}

.error-message {
	color: red;
	font-weight: bold;
}
</style>
</head>
<body>
	<h1>Listar Productos</h1>

	<!-- Mostrar mensaje de éxito -->
	<c:if test="${not empty sessionScope.mensaje}">
		<div class="success-message">${sessionScope.mensaje}</div>
		<!-- Eliminar el mensaje de la sesión después de mostrarlo -->
		<c:remove var="mensaje" scope="session" />
	</c:if>

	<!-- Mostrar mensaje de error -->
	<c:if test="${not empty error}">
		<div class="error-message">${error}</div>
		<form action="productos" method="get">
			<input type="hidden" name="opcion" value="editar" /> <input
				type="hidden" name="id" value="${producto.id}" />
			<button type="submit">Editar Producto</button>
		</form>
	</c:if>

	<!-- Formulario para agregar un nuevo producto -->
	<h2>Agregar Nuevo Producto</h2>
	<form action="productos" method="post">
		<input type="hidden" name="opcion" value="guardar" /> <label
			for="nombre">Nombre:</label> <input type="text" name="nombre"
			value="${not empty nombre ? nombre : ''}" required /> <br /> <label
			for="cantidad">Cantidad:</label> <input type="number" name="cantidad"
			value="${not empty cantidad ? cantidad : ''}" step="0.01" required />
		<br /> <label for="precio">Precio:</label> <input type="number"
			name="precio" value="${not empty precio ? precio : ''}" step="0.01"
			required /> <br />
		<button type="submit">Guardar Producto</button>
	</form>

	<!-- Tabla de productos existentes -->
	<table border="1">
		<tr>
			<td>Id</td>
			<td>Nombre</td>
			<td>Cantidad</td>
			<td>Precio</td>
			<td>Fecha Creacion</td>
			<td>Fecha Actualizacion</td>
			<td>Accion</td>
		</tr>
		<c:forEach var="producto" items="${lista}">
			<tr>
				<td><c:out value="${producto.id}"></c:out></td>
				<td><c:out value="${producto.nombre}"></c:out></td>
				<td><c:out value="${producto.cantidad}"></c:out></td>
				<td><c:out value="${producto.precio}"></c:out></td>
				<td><fmt:formatDate value="${producto.fechaCrear}"
						pattern="EEEE, dd MMMM yyyy HH:mm:ss" /></td>
				<td><fmt:formatDate value="${producto.fechaActualizar}"
						pattern="EEEE, dd MMMM yyyy HH:mm:ss" /></td>
				<td><a href="productos?opcion=editar&id=${producto.id}">Editar</a>
					<a href="productos?opcion=eliminar&id=${producto.id}">Eliminar</a>
				</td>
			</tr>
		</c:forEach>
	</table>

</body>
</html>
