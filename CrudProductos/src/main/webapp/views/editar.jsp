<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Editar Producto</title>
<link rel="stylesheet" type="text/css" href="css/editar.css">
<!-- Enlace al CSS -->
</head>
<body>
	<h1>Editar Producto</h1>
	<form action="productos" method="post">
		<input type="hidden" name="opcion" value="editar"> <input
			type="hidden" name="id" value="${producto.id}">
		<table border="1">
			<tr>
				<td>Nombre:</td>
				<td><input type="text" name="nombre" size="50"
					value="${producto.nombre}" required></td>
			</tr>
			<tr>
				<td>Cantidad:</td>
				<td><input type="number" name="cantidad" size="50" min="0"
					step="1" value="${producto.cantidad}" required></td>
			</tr>
			<tr>
				<td>Precio:</td>
				<td><input type="number" name="precio" size="50" min="0"
					step="0.01" value="${producto.precio}" required></td>
			</tr>
		</table>
		<input type="submit" value="Guardar Cambios">
	</form>


</body>
</html>
