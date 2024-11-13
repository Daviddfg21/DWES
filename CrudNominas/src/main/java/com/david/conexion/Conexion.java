package com.david.conexion;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * La clase {@code Conexion} se encarga de manejar la conexión a la base de
 * datos utilizando un pool de conexiones de Apache Tomcat DBCP.
 * <p>
 * Esta clase implementa el patrón Singleton para garantizar que solo se cree
 * una instancia del pool de conexiones durante la ejecución de la aplicación.
 * </p>
 */
public class Conexion {

	// Atributo estático que almacena la instancia única del pool de conexiones
	private static BasicDataSource dataSource;

	// Bloque estático que inicializa el pool de conexiones
	static {
		if (dataSource == null) {
			dataSource = new BasicDataSource();
			dataSource.setUrl("jdbc:mysql://localhost:3306/rrhh");
			dataSource.setUsername("root");
			dataSource.setPassword("12345");
			dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
			// Configuración adicional del pool de conexiones, si es necesario
			dataSource.setInitialSize(10); // Número inicial de conexiones en el pool
			dataSource.setMaxTotal(50); // Número máximo de conexiones en el pool
			dataSource.setMaxIdle(30); // Número máximo de conexiones inactivas
			dataSource.setMinIdle(5); // Número mínimo de conexiones inactivas
		}
	}

	// Constructor privado para evitar que se cree una nueva instancia desde fuera
	// de la clase
	private Conexion() {
	}

	/**
	 * Obtiene una conexión a la base de datos configurada en el
	 * {@code BasicDataSource}.
	 * <p>
	 * Si el pool de conexiones no ha sido inicializado previamente, se inicializa
	 * en el bloque estático.
	 * </p>
	 *
	 * @return una {@link Connection} válida a la base de datos
	 * @throws SQLException si no se puede establecer la conexión a la base de datos
	 */
	public static Connection getConnection() throws SQLException {
		if (dataSource == null) {
			throw new SQLException("Error: el pool de conexiones no está inicializado.");
		}
		return dataSource.getConnection();
	}

	/**
	 * Cierra las conexiones del pool. Esto se debe hacer al finalizar la
	 * aplicación.
	 *
	 * @throws SQLException si ocurre un error al cerrar el pool
	 */
	public static void close() throws SQLException {
		if (dataSource != null) {
			// Se cierra el pool de conexiones
			dataSource.close();
		}
	}
}
