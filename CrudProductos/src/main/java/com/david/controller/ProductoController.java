package com.david.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.david.dao.ProductoDAO;
import com.david.model.Producto;

/**
 * Servlet que maneja las peticiones relacionadas con los productos. Permite
 * crear, listar, editar y eliminar productos en la base de datos.
 */
@WebServlet(description = "administra peticiones para la tabla productos", urlPatterns = { "/productos" })
public class ProductoController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor por defecto de la clase ProductoController.
	 */
	public ProductoController() {
		super();
	}

	/**
	 * Maneja las solicitudes HTTP GET. Dependiendo de la opción solicitada, puede
	 * redirigir a la página de creación, listar productos, editar un producto o
	 * eliminar un producto.
	 *
	 * @param request  La solicitud HTTP.
	 * @param response La respuesta HTTP.
	 * @throws ServletException Si ocurre un error en la lógica del servlet.
	 * @throws IOException      Si ocurre un error al manejar la respuesta.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String opcion = request.getParameter("opcion");

		if (opcion.equals("crear")) {
			System.out.println("Usted ha presionado la opción crear");
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/crear.jsp");
			requestDispatcher.forward(request, response);
		} else if (opcion.equals("listar")) {
			ProductoDAO productoDAO = new ProductoDAO();
			List<Producto> lista = new ArrayList<>(); // Inicializar la lista aquí
			try {
				lista = productoDAO.obtenerProductos();
				for (Producto producto : lista) {
					System.out.println(producto);
				}

				request.setAttribute("lista", lista);
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/listar.jsp");
				requestDispatcher.forward(request, response);

			} catch (SQLException e) {
				e.printStackTrace();
				request.setAttribute("error", "Error al obtener la lista de productos.");
				request.setAttribute("lista", lista); // Mantener la lista vacía
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/listar.jsp");
				requestDispatcher.forward(request, response);
			}
			System.out.println("Usted ha presionado la opción listar");
		} else if (opcion.equals("editar")) {
			int id = Integer.parseInt(request.getParameter("id"));
			System.out.println("Editar id: " + id);
			ProductoDAO productoDAO = new ProductoDAO();
			Producto p = new Producto();
			try {
				p = productoDAO.obtenerProducto(id);
				System.out.println(p);
				request.setAttribute("producto", p);
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/editar.jsp");
				requestDispatcher.forward(request, response);
			} catch (SQLException e) {
				e.printStackTrace();
				request.setAttribute("error", "Error al obtener el producto.");
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/listar.jsp");
				requestDispatcher.forward(request, response);
			}
		} else if (opcion.equals("eliminar")) {
			ProductoDAO productoDAO = new ProductoDAO();
			int id = Integer.parseInt(request.getParameter("id"));
			try {
				// Obtener el nombre del producto antes de eliminarlo
				Producto producto = productoDAO.obtenerProducto(id);
				String nombreProducto = producto.getNombre();

				// Eliminar el producto
				productoDAO.eliminar(id);
				System.out.println("Registro eliminado satisfactoriamente...");

				// Guardar mensaje en la sesión
				HttpSession session = request.getSession();
				session.setAttribute("mensaje", "Producto '" + nombreProducto + "' eliminado correctamente.");

				// Redirigir a listar productos
				response.sendRedirect("productos?opcion=listar");
			} catch (SQLException e) {
				e.printStackTrace();
				request.setAttribute("error", "Error al eliminar el producto.");
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/listar.jsp");
				requestDispatcher.forward(request, response);
			}
		}
	}

	/**
	 * Maneja las solicitudes HTTP POST. Dependiendo de la opción solicitada, puede
	 * guardar un nuevo producto o editar un producto existente.
	 *
	 * @param request  La solicitud HTTP.
	 * @param response La respuesta HTTP.
	 * @throws ServletException Si ocurre un error en la lógica del servlet.
	 * @throws IOException      Si ocurre un error al manejar la respuesta.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String opcion = request.getParameter("opcion");

		if (opcion.equals("guardar")) {
			ProductoDAO productoDAO = new ProductoDAO();
			Producto producto = new Producto();
			String nombre = request.getParameter("nombre");
			String cantidadStr = request.getParameter("cantidad");
			String precioStr = request.getParameter("precio");

			// Validación de datos
			if (nombre == null || nombre.trim().isEmpty() || cantidadStr == null || cantidadStr.trim().isEmpty()
					|| precioStr == null || precioStr.trim().isEmpty()) {
				request.setAttribute("error", "Error, todos los campos son obligatorios.");
				// Mantener la lista de productos
				List<Producto> lista = new ArrayList<>();
				try {
					lista = productoDAO.obtenerProductos();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				request.setAttribute("lista", lista);
				// Guardar los datos introducidos
				request.setAttribute("nombre", nombre);
				request.setAttribute("cantidad", cantidadStr);
				request.setAttribute("precio", precioStr);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/views/listar.jsp");
				dispatcher.forward(request, response);
				return;
			}

			// Establecer los valores en el objeto Producto
			producto.setNombre(nombre);
			producto.setCantidad(Double.parseDouble(cantidadStr));
			producto.setPrecio(Double.parseDouble(precioStr));
			producto.setFechaCrear(new java.sql.Timestamp(new Date().getTime()));

			try {
				if (productoDAO.existeProductoPorNombre(nombre)) {
					request.setAttribute("error", "Error, el producto con el nombre '" + nombre + "' ya existe.");
					// Mantener la lista de productos
					List<Producto> lista = productoDAO.obtenerProductos(); // Obtener la lista de productos
					request.setAttribute("lista", lista);
					// Guardar los datos introducidos
					request.setAttribute("nombre", nombre);
					request.setAttribute("cantidad", cantidadStr);
					request.setAttribute("precio", precioStr);
					RequestDispatcher dispatcher = request.getRequestDispatcher("/views/listar.jsp");
					dispatcher.forward(request, response);
					return;
				}

				boolean resultado = productoDAO.guardar(producto);
				if (resultado) {
					System.out.println("Producto guardado exitosamente...");

					// Guardar mensaje en la sesión
					HttpSession session = request.getSession();
					session.setAttribute("mensaje", "Producto '" + nombre + "' guardado exitosamente.");

					// Redirigir a la página de listar
					response.sendRedirect("productos?opcion=listar");
				} else {
					request.setAttribute("error", "Error al guardar el producto.");
					// Mantener la lista de productos
					List<Producto> lista = productoDAO.obtenerProductos(); // Obtener la lista de productos
					request.setAttribute("lista", lista);
					RequestDispatcher dispatcher = request.getRequestDispatcher("/views/listar.jsp");
					dispatcher.forward(request, response);
				}
			} catch (SQLException e) {
				e.printStackTrace();
				request.setAttribute("error", "Error al conectar a la base de datos.");
				// Mantener la lista de productos
				List<Producto> lista = new ArrayList<>();
				try {
					lista = productoDAO.obtenerProductos(); // Obtener la lista de productos
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				request.setAttribute("lista", lista);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/views/listar.jsp");
				dispatcher.forward(request, response);
			}
		} else if (opcion.equals("editar")) {
			ProductoDAO productoDAO = new ProductoDAO();
			Producto producto = new Producto();
			String idStr = request.getParameter("id");
			String nombre = request.getParameter("nombre");
			String cantidadStr = request.getParameter("cantidad");
			String precioStr = request.getParameter("precio");

			if (idStr == null || idStr.trim().isEmpty() || nombre == null || nombre.trim().isEmpty()
					|| cantidadStr == null || cantidadStr.trim().isEmpty() || precioStr == null
					|| precioStr.trim().isEmpty()) {
				request.setAttribute("error", "Error, imposible editar el producto con datos vacíos.");
				request.setAttribute("producto", producto);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/views/editar.jsp");
				dispatcher.forward(request, response);
				return;
			}

			producto.setId(Integer.parseInt(idStr));
			producto.setNombre(nombre);
			producto.setCantidad(Double.parseDouble(cantidadStr));
			producto.setPrecio(Double.parseDouble(precioStr));
			producto.setFechaActualizar(new java.sql.Timestamp(new Date().getTime()));

			try {
				boolean resultado = productoDAO.editar(producto);
				if (resultado) {
					// Establecer el mensaje en la sesión
					request.getSession().setAttribute("mensaje", "Artículo editado correctamente.");
					// Redirigir a la lista de productos
					response.sendRedirect("productos?opcion=listar");
				} else {
					request.setAttribute("error", "Error al actualizar el producto.");
					request.setAttribute("producto", producto);
					RequestDispatcher dispatcher = request.getRequestDispatcher("/views/editar.jsp");
					dispatcher.forward(request, response);
				}
			} catch (SQLException e) {
				e.printStackTrace();
				request.setAttribute("error", "Error al conectar a la base de datos.");
				RequestDispatcher dispatcher = request.getRequestDispatcher("/views/editar.jsp");
				dispatcher.forward(request, response);
			}
		}
	}
}
