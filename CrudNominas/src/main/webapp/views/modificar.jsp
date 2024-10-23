<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Filtrar Empleados</title>
</head>
<body>
	<h1>Filtrar Empleados</h1>
	<form action="EmpleadoController" method="get">
		<input type="hidden" name="action" value="filterEmployee"> <label
			for="dni">DNI:</label> <input type="text" id="dni" name="dni"><br>

		<label for="nombre">Nombre:</label> <input type="text" id="nombre"
			name="nombre"><br> <label for="sexo">Sexo:</label> <select
			id="sexo" name="sexo">
			<option value="">Cualquiera</option>
			<option value="M">Masculino</option>
			<option value="F">Femenino</option>
		</select><br> <label for="categoria">Categoría:</label> <input
			type="number" id="categoria" name="categoria"><br> <label
			for="anios">Años de servicio:</label> <input type="number" id="anios"
			name="anios"><br> <input type="submit" value="Filtrar">
	</form>

	<h2>Lista de Empleados</h2>
	<table border="1">
		<tr>
			<th>DNI</th>
			<th>Nombre</th>
			<th>Sexo</th>
			<th>Categoría</th>
			<th>Años</th>
		</tr>
		<c:forEach var="empleado" items="${empleados}">
			<tr>
				<td>${empleado.dni}</td>
				<td>${empleado.nombre}</td>
				<td>${empleado.sexo}</td>
				<td>${empleado.categoria}</td>
				<td>${empleado.anios}</td>
			</tr>
		</c:forEach>
	</table>

	<br>
	<button onclick="location.href='index.jsp'">Volver al Menú
		Principal</button>
</body>
</html>
