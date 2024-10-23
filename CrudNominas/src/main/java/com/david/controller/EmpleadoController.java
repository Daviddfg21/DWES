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

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");

		if ("list".equals(action)) {
			try {
				List<Empleado> empleados = empleadoDAO.getAllEmpleados();
				request.setAttribute("empleados", empleados);
				request.getRequestDispatcher("views/empleados.jsp").forward(request, response);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if ("salary".equals(action)) {
			request.getRequestDispatcher("views/salario.jsp").forward(request, response);
		} else if ("modify".equals(action)) {
			request.getRequestDispatcher("views/modificar.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");

		if ("getSalary".equals(action)) {
			String dni = request.getParameter("dni");
			try {
				Empleado empleado = empleadoDAO.getEmpleadoByDni(dni);
				request.setAttribute("empleado", empleado);
				request.getRequestDispatcher("views/salario.jsp").forward(request, response);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if ("createEmployee".equals(action)) {
			String dni = request.getParameter("dni");
			String nombre = request.getParameter("nombre");
			char sexo = request.getParameter("sexo").charAt(0);
			int categoria = Integer.parseInt(request.getParameter("categoria"));
			int anios = Integer.parseInt(request.getParameter("anios"));

			// Validar el formato del DNI
			String dniPattern = "^\\d{8}[A-Za-z]$"; // Formato: 8 dígitos seguidos de una letra
			if (!Pattern.matches(dniPattern, dni)) {
				request.setAttribute("errorMessage", "El DNI debe tener 8 números seguidos de una letra.");
				List<Empleado> empleados = null;
				try {
					empleados = empleadoDAO.getAllEmpleados();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} // Cargar la lista para mostrarla
				request.setAttribute("empleados", empleados);
				request.getRequestDispatcher("views/empleados.jsp").forward(request, response);
				return;
			}

			try {
				// Verificar si el DNI ya existe
				if (empleadoDAO.getEmpleadoByDni(dni) != null) {
					request.setAttribute("errorMessage", "Ya existe un empleado con el DNI proporcionado.");
					List<Empleado> empleados = empleadoDAO.getAllEmpleados(); // Cargar la lista para mostrarla
					request.setAttribute("empleados", empleados);
					request.getRequestDispatcher("views/empleados.jsp").forward(request, response);
					return;
				}

				Empleado empleado = new Empleado(dni, nombre, sexo, categoria, anios);
				empleadoDAO.createEmpleado(empleado);
				request.setAttribute("successMessage", "Empleado agregado correctamente.");
				List<Empleado> empleados = empleadoDAO.getAllEmpleados(); // Cargar la lista para mostrarla
				request.setAttribute("empleados", empleados);
				request.getRequestDispatcher("views/empleados.jsp").forward(request, response);
			} catch (SQLException e) {
				e.printStackTrace();
				request.setAttribute("errorMessage", "Error al agregar el empleado.");
				List<Empleado> empleados = null;
				try {
					empleados = empleadoDAO.getAllEmpleados();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} // Cargar la lista para mostrarla
				request.setAttribute("empleados", empleados);
				request.getRequestDispatcher("views/empleados.jsp").forward(request, response);
			}
		} else if ("modifyEmployee".equals(action)) {
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
				List<Empleado> empleados = empleadoDAO.getAllEmpleados(); // Cargar la lista para mostrarla
				request.setAttribute("empleados", empleados);
				request.getRequestDispatcher("views/modificar.jsp").forward(request, response);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
