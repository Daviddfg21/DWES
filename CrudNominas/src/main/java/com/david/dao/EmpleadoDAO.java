package com.david.dao;

import com.david.conexion.Conexion;
import com.david.model.Empleado;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * La clase {@code EmpleadoDAO} proporciona métodos para realizar operaciones
 * CRUD en la tabla de empleados de la base de datos. Utiliza la clase
 * {@code Conexion} para gestionar la conexión a la base de datos.
 */
public class EmpleadoDAO {

	private Connection connection;

	/**
	 * Constructor de la clase {@code EmpleadoDAO}. Inicializa la conexión con la
	 * base de datos.
	 * 
	 * @throws SQLException si ocurre un error al establecer la conexión con la base
	 *                      de datos.
	 */
	public EmpleadoDAO() throws SQLException {
		// Inicializamos la conexión con la base de datos
		connection = Conexion.getConnection();
	}

	/**
	 * Obtiene todos los empleados de la base de datos.
	 * 
	 * @return una lista de objetos {@code Empleado} que representan todos los
	 *         empleados.
	 * @throws SQLException si ocurre un error al acceder a la base de datos.
	 */
	public List<Empleado> getAllEmpleados() throws SQLException {
		List<Empleado> empleados = new ArrayList<>();
		String sql = "SELECT * FROM empleado";

		try (PreparedStatement stmt = connection.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
			while (rs.next()) {
				String dni = rs.getString("dni");
				String nombre = rs.getString("nombre");
				char sexo = rs.getString("sexo").charAt(0);
				int categoria = rs.getInt("categoria");
				int anios = rs.getInt("anios");

				Empleado empleado = new Empleado(dni, nombre, sexo, categoria, anios);
				empleados.add(empleado);
			}
		}

		return empleados;
	}

	/**
	 * Filtra empleados según los criterios proporcionados.
	 * 
	 * @param dni       el DNI del empleado a buscar (puede ser {@code null} o
	 *                  vacío).
	 * @param nombre    el nombre del empleado a buscar (puede ser {@code null} o
	 *                  vacío).
	 * @param sexo      el sexo del empleado a buscar (puede ser {@code null} o
	 *                  vacío).
	 * @param categoria la categoría del empleado a buscar (puede ser {@code null}).
	 * @param anios     los años de servicio del empleado a buscar (puede ser
	 *                  {@code null}).
	 * @return una lista de objetos {@code Empleado} que cumplen con los criterios
	 *         de búsqueda.
	 * @throws SQLException si ocurre un error al acceder a la base de datos.
	 */
	public List<Empleado> getEmpleadosFiltrados(String dni, String nombre, String sexo, Integer categoria,
			Integer anios) throws SQLException {
		List<Empleado> empleados = new ArrayList<>();
		StringBuilder sql = new StringBuilder("SELECT * FROM empleado WHERE 1=1");

		if (dni != null && !dni.isEmpty()) {
			sql.append(" AND dni LIKE ?");
		}
		if (nombre != null && !nombre.isEmpty()) {
			sql.append(" AND nombre LIKE ?");
		}
		if (sexo != null && !sexo.isEmpty()) {
			sql.append(" AND sexo = ?");
		}
		if (categoria != null) {
			sql.append(" AND categoria = ?");
		}
		if (anios != null) {
			sql.append(" AND anios = ?");
		}

		try (PreparedStatement stmt = connection.prepareStatement(sql.toString())) {
			int index = 1;

			if (dni != null && !dni.isEmpty()) {
				stmt.setString(index++, "%" + dni + "%");
			}
			if (nombre != null && !nombre.isEmpty()) {
				stmt.setString(index++, "%" + nombre + "%");
			}
			if (sexo != null && !sexo.isEmpty()) {
				stmt.setString(index++, sexo);
			}
			if (categoria != null) {
				stmt.setInt(index++, categoria);
			}
			if (anios != null) {
				stmt.setInt(index++, anios);
			}

			try (ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					String dniResult = rs.getString("dni");
					String nombreResult = rs.getString("nombre");
					char sexoResult = rs.getString("sexo").charAt(0);
					int categoriaResult = rs.getInt("categoria");
					int aniosResult = rs.getInt("anios");

					Empleado empleado = new Empleado(dniResult, nombreResult, sexoResult, categoriaResult, aniosResult);
					empleados.add(empleado);
				}
			}
		}

		return empleados;
	}

	/**
	 * Obtiene un empleado de la base de datos según su DNI.
	 * 
	 * @param dni el DNI del empleado a buscar.
	 * @return un objeto {@code Empleado} que representa al empleado encontrado, o
	 *         {@code null} si no se encuentra.
	 * @throws SQLException si ocurre un error al acceder a la base de datos.
	 */
	public Empleado getEmpleadoByDni(String dni) throws SQLException {
		Empleado empleado = null;
		String sql = "SELECT * FROM empleado WHERE dni = ?";

		try (PreparedStatement stmt = connection.prepareStatement(sql)) {
			stmt.setString(1, dni);
			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					String nombre = rs.getString("nombre");
					char sexo = rs.getString("sexo").charAt(0);
					int categoria = rs.getInt("categoria");
					int anios = rs.getInt("anios");

					empleado = new Empleado(dni, nombre, sexo, categoria, anios);
				}
			}
		}

		return empleado;
	}

	/**
	 * Crea un nuevo empleado en la base de datos.
	 * 
	 * @param empleado el objeto {@code Empleado} que se desea agregar a la base de
	 *                 datos.
	 * @throws SQLException si ocurre un error al acceder a la base de datos.
	 */
	public void createEmpleado(Empleado empleado) throws SQLException {
		String sql = "INSERT INTO empleado (dni, nombre, sexo, categoria, anios) VALUES (?, ?, ?, ?, ?)";
		try (PreparedStatement stmt = connection.prepareStatement(sql)) {
			stmt.setString(1, empleado.getDni());
			stmt.setString(2, empleado.getNombre());
			stmt.setString(3, String.valueOf(empleado.getSexo()));
			stmt.setInt(4, empleado.getCategoria());
			stmt.setInt(5, empleado.getAnios());
			stmt.executeUpdate();
		}
	}

	/**
	 * Actualiza un empleado existente en la base de datos.
	 * 
	 * @param empleado el objeto {@code Empleado} que contiene la información
	 *                 actualizada.
	 * @throws SQLException si ocurre un error al acceder a la base de datos.
	 */
	public void updateEmpleado(Empleado empleado) throws SQLException {
		String sql = "UPDATE empleado SET nombre = ?, sexo = ?, categoria = ?, anios = ? WHERE dni = ?";
		try (PreparedStatement stmt = connection.prepareStatement(sql)) {
			stmt.setString(1, empleado.getNombre());
			stmt.setString(2, String.valueOf(empleado.getSexo()));
			stmt.setInt(3, empleado.getCategoria());
			stmt.setInt(4, empleado.getAnios());
			stmt.setString(5, empleado.getDni());
			stmt.executeUpdate();
		}
	}
}