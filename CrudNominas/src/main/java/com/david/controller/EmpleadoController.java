package com.david.controller;

import com.david.dao.EmpleadoDAO;
import com.david.model.Empleado;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.regex.Pattern;

@WebServlet("/EmpleadoController")
public class EmpleadoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private EmpleadoDAO empleadoDAO;

	public EmpleadoController() throws SQLException {
		empleadoDAO = new EmpleadoDAO();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");

		if ("list".equals(action)) {
			// Mostrar lista completa de empleados
			try {
				List<Empleado> empleados = empleadoDAO.getAllEmpleados();
				request.setAttribute("empleados", empleados);
				request.getRequestDispatcher("views/empleados.jsp").forward(request, response);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if ("salary".equals(action)) {
			// Mostrar página de salario
			request.getRequestDispatcher("views/salario.jsp").forward(request, response);
		} else if ("modify".equals(action)) {
			// Mostrar página de modificar empleados
			request.getRequestDispatcher("views/modificar.jsp").forward(request, response);
		} else if ("filterEmployee".equals(action)) {
			// Filtrar empleados según los criterios del formulario
			String dni = request.getParameter("dni");
			String nombre = request.getParameter("nombre");
			String sexo = request.getParameter("sexo");
			String categoriaParam = request.getParameter("categoria");
			String aniosParam = request.getParameter("anios");

			Integer categoria = (categoriaParam != null && !categoriaParam.isEmpty()) ? Integer.parseInt(categoriaParam)
					: null;
			Integer anios = (aniosParam != null && !aniosParam.isEmpty()) ? Integer.parseInt(aniosParam) : null;

			try {
				List<Empleado> empleados = empleadoDAO.getEmpleadosFiltrados(dni, nombre, sexo, categoria, anios);
				request.setAttribute("empleados", empleados);
				request.getRequestDispatcher("views/modificar.jsp").forward(request, response);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");

		if ("getSalary".equals(action)) {
			// Obtener salario del empleado por DNI
			String dni = request.getParameter("dni");
			try {
				Empleado empleado = empleadoDAO.getEmpleadoByDni(dni);
				request.setAttribute("empleado", empleado);
				request.getRequestDispatcher("views/salario.jsp").forward(request, response);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if ("createEmployee".equals(action)) {
			// Crear un nuevo empleado
			String dni = request.getParameter("dni");
			String nombre = request.getParameter("nombre");
			char sexo = request.getParameter("sexo").charAt(0);
			int categoria = Integer.parseInt(request.getParameter("categoria"));
			int anios = Integer.parseInt(request.getParameter("anios"));

			// Validar el formato del DNI
			String dniPattern = "^\\d{8}[A-Za-z]$";
			if (!Pattern.matches(dniPattern, dni)) {
				request.setAttribute("errorMessage", "El DNI debe tener 8 números seguidos de una letra.");
				try {
					List<Empleado> empleados = empleadoDAO.getAllEmpleados();
					request.setAttribute("empleados", empleados);
					request.getRequestDispatcher("views/empleados.jsp").forward(request, response);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				return;
			}

			try {
				// Verificar si el empleado ya existe
				if (empleadoDAO.getEmpleadoByDni(dni) != null) {
					request.setAttribute("errorMessage", "Ya existe un empleado con el DNI proporcionado.");
					List<Empleado> empleados = empleadoDAO.getAllEmpleados();
					request.setAttribute("empleados", empleados);
					request.getRequestDispatcher("views/empleados.jsp").forward(request, response);
					return;
				}

				// Crear nuevo empleado
				Empleado empleado = new Empleado(dni, nombre, sexo, categoria, anios);
				empleadoDAO.createEmpleado(empleado);
				request.setAttribute("successMessage", "Empleado agregado correctamente.");
				List<Empleado> empleados = empleadoDAO.getAllEmpleados();
				request.setAttribute("empleados", empleados);
				request.getRequestDispatcher("views/empleados.jsp").forward(request, response);
			} catch (SQLException e) {
				e.printStackTrace();
				request.setAttribute("errorMessage", "Error al agregar el empleado.");
				try {
					List<Empleado> empleados = empleadoDAO.getAllEmpleados();
					request.setAttribute("empleados", empleados);
					request.getRequestDispatcher("views/empleados.jsp").forward(request, response);
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		} else if ("modifyEmployee".equals(action)) {
			// Modificar empleado existente
			String dni = request.getParameter("dni");
			String nombre = request.getParameter("nombre");
			char sexo = request.getParameter("sexo").charAt(0);
			int categoria = Integer.parseInt(request.getParameter("categoria"));
			int anios = Integer.parseInt(request.getParameter("anios"));

			try {
				Empleado empleado = empleadoDAO.getEmpleadoByDni(dni);
				if (empleado != null) {
					empleado.setNombre(nombre);
					empleado.setSexo(sexo);
					empleado.setCategoria(categoria);
					empleado.setAnios(anios);
					empleadoDAO.updateEmpleado(empleado);
					request.setAttribute("successMessage", "Empleado con DNI " + dni + " modificado correctamente.");
				} else {
					request.setAttribute("errorMessage", "Error, el empleado con DNI " + dni + " no existe.");
				}
				List<Empleado> empleados = empleadoDAO.getAllEmpleados();
				request.setAttribute("empleados", empleados);
				request.getRequestDispatcher("views/modificar.jsp").forward(request, response);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
