package com.david.dao;

import com.david.conexion.Conexion;
import com.david.model.Empleado;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmpleadoDAO {

	private Connection connection;

	public EmpleadoDAO() throws SQLException {
		// Inicializamos la conexión con la base de datos
		connection = Conexion.getConnection();
	}

	// Obtener todos los empleados
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

	// Filtrar empleados según los criterios proporcionados
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

	// Obtener empleado por DNI
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

	// Crear nuevo empleado
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

	// Actualizar empleado existente
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
