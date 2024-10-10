<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Crear Producto</title>
<link rel="stylesheet" type="text/css" href="css/crear.css">
<!-- Enlace al CSS -->
</head>
<body>
	<h1>Crear Producto</h1>

	<%
	// Mostrar mensaje de error si existe
	String error = (String) request.getAttribute("error");
	if (error != null) {
	%>
	<div style="color: red;"><%=error%></div>
	<%
	}
	%>

	<form action="productos" method="post">
		<input type="hidden" name="opcion" value="guardar">
		<table border="1">
			<tr>
				<td>Nombre:</td>
				<td><input type="text" name="nombre" size="50"
					value="<%=request.getAttribute("nombre") != null ? request.getAttribute("nombre") : ""%>">
				</td>
			</tr>
			<tr>
				<td>Cantidad:</td>
				<td><input type="number" name="cantidad" size="50" min="0"
					step="1"
					value="<%=request.getAttribute("cantidad") != null ? request.getAttribute("cantidad") : ""%>"
					required></td>
			</tr>
			<tr>
				<td>Precio:</td>
				<td><input type="number" name="precio" size="50" min="0"
					step="0.01"
					value="<%=request.getAttribute("precio") != null ? request.getAttribute("precio") : ""%>"
					required></td>
			</tr>
		</table>
		<input type="submit" value="Guardar">
	</form>

</body>
</html>
