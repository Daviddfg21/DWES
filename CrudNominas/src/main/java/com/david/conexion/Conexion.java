package com.david.conexion;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Clase para gestionar la conexión a la base de datos utilizando un pool de
 * conexiones. Esta clase utiliza BasicDataSource para proporcionar conexiones a
 * la base de datos MySQL.
 *
 * <p>
 * La clase se encarga de crear y mantener un pool de conexiones para optimizar
 * el acceso a la base de datos, reduciendo el costo de abrir y cerrar
 * conexiones repetidamente.
 * </p>
 */
public class Conexion {

	// Fuente de datos para las conexiones a la base de datos
	private static BasicDataSource dataSource = null;

	/**
	 * Obtiene la fuente de datos para las conexiones a la base de datos. Si la
	 * fuente de datos no ha sido inicializada, se crea una nueva instancia de
	 * BasicDataSource y se configuran sus parámetros.
	 *
	 * @return DataSource La fuente de datos configurada.
	 */
	private static DataSource getDataSource() {
		if (dataSource == null) {
			dataSource = new BasicDataSource();
			dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver"); // Establece el controlador de MySQL
			dataSource.setUsername("root"); // Establece el nombre de usuario de la base de datos
			dataSource.setPassword("12345"); // Establece la contraseña de la base de datos
			dataSource.setUrl("jdbc:mysql://localhost:3306/CrudProductos?useTimezone=true&serverTimezone=UTC"); // URL
																												// de la
																												// base
																												// de
																												// datos
			dataSource.setInitialSize(20); // Tamaño inicial del pool de conexiones
			dataSource.setMaxIdle(15); // Número máximo de conexiones inactivas permitidas
			dataSource.setMaxTotal(20); // Número máximo total de conexiones permitidas
		}
		return dataSource;
	}

	/**
	 * Obtiene una conexión a la base de datos.
	 *
	 * <p>
	 * Este método proporciona una conexión activa del pool de conexiones. Si no hay
	 * conexiones disponibles, se bloqueará hasta que una conexión sea liberada.
	 * </p>
	 *
	 * @return Connection La conexión a la base de datos.
	 * @throws SQLException Si ocurre un error al obtener la conexión.
	 */
	public static Connection getConnection() throws SQLException {
		return getDataSource().getConnection();
	}
}
