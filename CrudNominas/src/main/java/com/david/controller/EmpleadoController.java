package com.david.controller;

import com.david.dao.EmpleadoDAO;
import com.david.model.Empleado;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

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

			try {
				Empleado empleado = new Empleado(dni, nombre, sexo, categoria, anios);
				empleadoDAO.createEmpleado(empleado);
				response.sendRedirect("EmpleadoController?action=list");
			} catch (SQLException e) {
				e.printStackTrace();
				request.setAttribute("errorMessage", "Error al agregar el empleado.");
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
				request.getRequestDispatcher("views/modificar.jsp").forward(request, response);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
