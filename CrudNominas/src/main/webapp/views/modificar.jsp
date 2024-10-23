<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Modificar Empleado</title>
</head>
<body>
	<h1>Modificar Empleado</h1>
	<form action="EmpleadoController" method="post">
		<input type="hidden" name="action" value="modifyEmployee"> <label
			for="dni">DNI del Empleado:</label> <input type="text" id="dni"
			name="dni" required> <br> <label for="nombre">Nombre:</label>
		<input type="text" id="nombre" name="nombre" required> <br>
		<label for="sexo">Sexo:</label> <select id="sexo" name="sexo">
			<option value="M">Masculino</option>
			<option value="F">Femenino</option>
		</select> <br> <label for="categoria">Categoría:</label> <input
			type="number" id="categoria" name="categoria" required> <br>
		<label for="anios">Años de servicio:</label> <input type="number"
			id="anios" name="anios" required> <br> <input
			type="submit" value="Modificar">
	</form>

	<c:if test="${not empty successMessage}">
		<div style="color: green;">${successMessage}</div>
	</c:if>
	<c:if test="${not empty errorMessage}">
		<div style="color: red;">${errorMessage}</div>
	</c:if>

	<br>
	<button onclick="location.href='index.jsp'">Volver al Menú
		Principal</button>
	<button
		onclick="location.href='EmpleadoController?action=list&origin=modify'">Mostrar
		Empleados</button>
</body>
</html>