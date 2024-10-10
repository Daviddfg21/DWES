package com.david.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.david.conexion.Conexion;
import com.david.model.Producto;

/**
 * Clase que maneja las operaciones de acceso a datos relacionadas con los
 * productos. Permite realizar operaciones CRUD (crear, leer, actualizar y
 * eliminar) sobre la tabla de productos en la base de datos.
 */
public class ProductoDAO {
	private Connection connection;
	private PreparedStatement statement;
	private boolean estadoOperacion;

	/**
	 * Guarda un nuevo producto en la base de datos.
	 *
	 * @param producto El objeto Producto que se desea guardar.
	 * @return true si la operación fue exitosa, false en caso contrario.
	 * @throws SQLException si ocurre un error al ejecutar la operación.
	 */
	public boolean guardar(Producto producto) throws SQLException {
		String sql = null;
		estadoOperacion = false;
		connection = obtenerConexion();

		try {
			connection.setAutoCommit(false);
			sql = "INSERT INTO productos (nombre, cantidad, precio, fecha_crear) VALUES (?, ?, ?, ?)";
			statement = connection.prepareStatement(sql);

			// Los ID normalmente se generan automáticamente, así que no los incluimos en el
			// INSERT.
			statement.setString(1, producto.getNombre());
			statement.setDouble(2, producto.getCantidad());
			statement.setDouble(3, producto.getPrecio());
			statement.setTimestamp(4, producto.getFechaCrear());

			estadoOperacion = statement.executeUpdate() > 0;

			connection.commit();
		} catch (SQLException e) {
			connection.rollback();
			e.printStackTrace();
		} finally {
			if (statement != null)
				statement.close();
			if (connection != null)
				connection.close();
		}

		return estadoOperacion;
	}

	/**
	 * Edita un producto existente en la base de datos.
	 *
	 * @param producto El objeto Producto con los datos actualizados.
	 * @return true si la operación fue exitosa, false en caso contrario.
	 * @throws SQLException si ocurre un error al ejecutar la operación.
	 */
	public boolean editar(Producto producto) throws SQLException {
		String sql = null;
		estadoOperacion = false;
		connection = obtenerConexion();
		try {
			connection.setAutoCommit(false);
			sql = "UPDATE productos SET nombre=?, cantidad=?, precio=?, fecha_actualizar=? WHERE id=?";
			statement = connection.prepareStatement(sql);

			statement.setString(1, producto.getNombre());
			statement.setDouble(2, producto.getCantidad());
			statement.setDouble(3, producto.getPrecio());
			statement.setTimestamp(4, producto.getFechaActualizar());
			statement.setInt(5, producto.getId());

			estadoOperacion = statement.executeUpdate() > 0;
			connection.commit();
			statement.close();
			connection.close();

		} catch (SQLException e) {
			connection.rollback();
			e.printStackTrace();
		}

		return estadoOperacion;
	}

	/**
	 * Elimina un producto de la base de datos dado su ID.
	 *
	 * @param idProducto El ID del producto que se desea eliminar.
	 * @return true si la operación fue exitosa, false en caso contrario.
	 * @throws SQLException si ocurre un error al ejecutar la operación.
	 */
	public boolean eliminar(int idProducto) throws SQLException {
		String sql = null;
		estadoOperacion = false;
		connection = obtenerConexion();
		try {
			connection.setAutoCommit(false);
			sql = "DELETE FROM productos WHERE id=?";
			statement = connection.prepareStatement(sql);
			statement.setInt(1, idProducto);

			estadoOperacion = statement.executeUpdate() > 0;
			connection.commit();
			statement.close();
			connection.close();

		} catch (SQLException e) {
			connection.rollback();
			e.printStackTrace();
		}

		return estadoOperacion;
	}

	/**
	 * Obtiene una lista de todos los productos de la base de datos.
	 *
	 * @return Una lista de objetos Producto.
	 * @throws SQLException si ocurre un error al ejecutar la operación.
	 */
	public List<Producto> obtenerProductos() throws SQLException {
		ResultSet resultSet = null;
		List<Producto> listaProductos = new ArrayList<>();

		String sql = null;
		estadoOperacion = false;
		connection = obtenerConexion();

		try {
			sql = "SELECT * FROM productos";
			statement = connection.prepareStatement(sql);
			resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				Producto p = new Producto();
				p.setId(resultSet.getInt(1));
				p.setNombre(resultSet.getString(2));
				p.setCantidad(resultSet.getDouble(3));
				p.setPrecio(resultSet.getDouble(4));
				p.setFechaCrear(resultSet.getTimestamp(5));
				p.setFechaActualizar(resultSet.getTimestamp(6));
				listaProductos.add(p);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listaProductos;
	}

	/**
	 * Obtiene un producto de la base de datos dado su ID.
	 *
	 * @param idProducto El ID del producto que se desea obtener.
	 * @return El objeto Producto correspondiente al ID proporcionado.
	 * @throws SQLException si ocurre un error al ejecutar la operación.
	 */
	public Producto obtenerProducto(int idProducto) throws SQLException {
		ResultSet resultSet = null;
		Producto p = new Producto();

		String sql = null;
		estadoOperacion = false;
		connection = obtenerConexion();

		try {
			sql = "SELECT * FROM productos WHERE id =?";
			statement = connection.prepareStatement(sql);
			statement.setInt(1, idProducto);

			resultSet = statement.executeQuery();

			if (resultSet.next()) {
				p.setId(resultSet.getInt(1));
				p.setNombre(resultSet.getString(2));
				p.setCantidad(resultSet.getDouble(3));
				p.setPrecio(resultSet.getDouble(4));
				p.setFechaCrear(resultSet.getTimestamp(5));
				p.setFechaActualizar(resultSet.getTimestamp(6));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return p;
	}

	/**
	 * Verifica si un producto con el mismo nombre ya existe en la base de datos.
	 *
	 * @param nombre El nombre del producto a verificar.
	 * @return true si el producto existe, false en caso contrario.
	 * @throws SQLException si ocurre un error al ejecutar la operación.
	 */
	public boolean existeProductoPorNombre(String nombre) throws SQLException {
		String sql = "SELECT COUNT(*) FROM productos WHERE nombre = ?";
		boolean existe = false;
		ResultSet resultSet = null;

		try {
			connection = obtenerConexion();
			statement = connection.prepareStatement(sql);
			statement.setString(1, nombre);
			resultSet = statement.executeQuery();

			if (resultSet.next()) {
				existe = resultSet.getInt(1) > 0; // Si el conteo es mayor a 0, el producto existe
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (resultSet != null)
				resultSet.close();
			if (statement != null)
				statement.close();
			if (connection != null)
				connection.close();
		}

		return existe;
	}

	/**
	 * Obtiene una conexión a la base de datos a través del pool de conexiones.
	 *
	 * @return Una conexión a la base de datos.
	 * @throws SQLException si ocurre un error al establecer la conexión.
	 */
	private Connection obtenerConexion() throws SQLException {
		return Conexion.getConnection();
	}
}
