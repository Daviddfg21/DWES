<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Empleados</title>
</head>
<body>
	<h1>Lista de Empleados</h1>

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

	<h2>Agregar Nuevo Empleado</h2>
	<form method="post" action="EmpleadoController">
		<input type="hidden" name="action" value="createEmployee"> <label
			for="dni">DNI:</label> <input type="text" id="dni" name="dni"
			required><br> <label for="nombre">Nombre:</label> <input
			type="text" id="nombre" name="nombre" required><br> <label
			for="sexo">Sexo:</label> <select id="sexo" name="sexo" required>
			<option value="M">Masculino</option>
			<option value="F">Femenino</option>
		</select><br> <label for="categoria">Categoría:</label> <input
			type="number" id="categoria" name="categoria" min="1" max="10"
			required><br> <label for="anios">Años
			Trabajados:</label> <input type="number" id="anios" name="anios" min="0"
			required><br> <input type="submit"
			value="Agregar Empleado">
	</form>
</body>
</html>