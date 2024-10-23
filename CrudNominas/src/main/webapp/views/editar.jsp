<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="css/styles.css">
<meta charset="UTF-8">
<title>Editar Empleado</title>
</head>
<body>
	<h1>Editar Empleado</h1>

	<c:if test="${not empty successMessage}">
		<div style="color: green;">${successMessage}</div>
	</c:if>
	<c:if test="${not empty errorMessage}">
		<div style="color: red;">${errorMessage}</div>
	</c:if>

	<form method="post" action="EmpleadoController">
		<input type="hidden" name="action" value="modifyEmployee"> <input
			type="hidden" name="dni" value="${empleado.dni}">
		<!-- Campo oculto para el DNI -->

		<label for="nombre">Nombre:</label> <input type="text" id="nombre"
			name="nombre" value="${empleado.nombre}" required><br> <label
			for="sexo">Sexo:</label> <select id="sexo" name="sexo" required>
			<option value="M"
				<c:if test="${empleado.sexo == 77}">selected</c:if>>Masculino</option>
			<option value="F"
				<c:if test="${empleado.sexo == 70}">selected</c:if>>Femenino</option>
		</select><br> <label for="categoria">Categoría:</label> <input
			type="number" id="categoria" name="categoria" min="1" max="10"
			value="${empleado.categoria}" required><br> <label
			for="anios">Años Trabajados:</label> <input type="number" id="anios"
			name="anios" min="0" value="${empleado.anios}" required><br>

		<input type="submit" value="Actualizar Empleado">
	</form>
	<a href="EmpleadoController?action=list">Volver</a>
</body>
</html>
