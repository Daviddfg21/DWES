<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="javax.servlet.http.HttpSession"%>
<!DOCTYPE html>
<html>
<head>
<title>Índice de Productos</title>
<link rel="stylesheet" type="text/css" href="css/index.css">
<!-- Enlace al CSS -->
<style>
.mensaje {
	color: green;
	font-weight: bold;
	margin-bottom: 10px;
}
</style>
</head>
<body>
	<h1>Menú de Opciones Productos</h1>

	<%
	// Obtener el mensaje de la sesión directamente
	String mensaje = (String) request.getSession().getAttribute("mensaje");

	// Si hay un mensaje, mostrarlo
	if (mensaje != null) {
	%>
	<div class="mensaje">
		<%=mensaje%>
	</div>
	<%
	// Eliminar el mensaje de la sesión después de mostrarlo
	request.getSession().removeAttribute("mensaje");
	}
	%>

	<table border="1">
		<tr>
			<td><a href="productos?opcion=crear"> Crear un Producto</a></td>
		</tr>
		<tr>
			<td><a href="productos?opcion=listar"> Listar Productos</a></td>
		</tr>
	</table>
</body>
</html>
