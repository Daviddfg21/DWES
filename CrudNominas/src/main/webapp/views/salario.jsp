<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="css/styles.css">
<meta charset="UTF-8">
<title>Salario Empleado</title>
</head>
<body>
	<h1>Buscar Salario de Empleado</h1>
	<form method="post" action="EmpleadoController">
		<input type="hidden" name="action" value="getSalary"> <label
			for="dni">DNI:</label> <input type="text" id="dni" name="dni"
			required> <input type="submit" value="Buscar">
	</form>

	<c:if test="${not empty empleado}">
		<h2>Información del Empleado</h2>
		<p>DNI: ${empleado.dni}</p>
		<p>Nombre: ${empleado.nombre}</p>
		<p>Sexo: ${empleado.sexo}</p>
		<p>Categoría: ${empleado.categoria}</p>
		<p>Años: ${empleado.anios}</p>
		<p>Salario: ${empleado.sueldo} €</p>
	</c:if>

	<c:if test="${empty empleado}">
		<p>No se encontró ningún empleado con el DNI proporcionado.</p>
	</c:if>

	<a href="index.jsp">Volver</a>
</body>
</html>
