package com.david.conexion;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Clase para gestionar la conexión a la base de datos utilizando un pool de
 * conexiones. Esta clase utiliza BasicDataSource para proporcionar conexiones a
 * la base de datos MySQL.
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
			dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
			dataSource.setUsername("root");
			dataSource.setPassword("12345");
			dataSource.setUrl("jdbc:mysql://localhost:3306/CrudProductos?useTimezone=true&serverTimezone=UTC");
			dataSource.setInitialSize(20);
			dataSource.setMaxIdle(15);
			dataSource.setMaxTotal(20);
		}
		return dataSource;
	}

	/**
	 * Obtiene una conexión a la base de datos.
	 *
	 * @return Connection La conexión a la base de datos.
	 * @throws SQLException Si ocurre un error al obtener la conexión.
	 */
	public static Connection getConnection() throws SQLException {
		return getDataSource().getConnection();
	}
}
