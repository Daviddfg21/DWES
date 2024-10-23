<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.david.model.Empleado"%>
<!DOCTYPE html>
<html>
<head>
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

	<%
	Empleado empleado = (Empleado) request.getAttribute("empleado");
	if (empleado != null) {
	%>
	<h2>Información del Empleado</h2>
	<p>
		DNI:
		<%=empleado.getDni()%></p>
	<p>
		Nombre:
		<%=empleado.getNombre()%></p>
	<p>
		Sexo:
		<%=empleado.getSexo()%></p>
	<p>
		Categoría:
		<%=empleado.getCategoria()%></p>
	<p>
		Años:
		<%=empleado.getAnios()%></p>
	<p>
		Salario:
		<%=empleado.getSueldo()%></p>
	<!-- Mostrar sueldo -->
	<%
	} else if (request.getMethod().equalsIgnoreCase("POST")) {
	%>
	<p>No se encontró ningún empleado con el DNI proporcionado.</p>
	<%
	}
	%>
	<a href="index.jsp">Volver</a>
</body>
</html>
