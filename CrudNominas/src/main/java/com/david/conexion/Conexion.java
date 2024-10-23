package com.david.conexion;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * La clase {@code Conexion} se encarga de manejar la conexión a la base de
 * datos utilizando un pool de conexiones de Apache Tomcat DBCP.
 * <p>
 * Esta clase proporciona un método estático para obtener una conexión a la base
 * de datos configurada. La configuración de la conexión incluye la URL de la
 * base de datos, el nombre de usuario, la contraseña y el controlador JDBC
 * correspondiente.
 * </p>
 */
public class Conexion {

	private static BasicDataSource dataSource;

	static {
		dataSource = new BasicDataSource();
		dataSource.setUrl("jdbc:mysql://localhost:3306/rrhh");
		dataSource.setUsername("root");
		dataSource.setPassword("12345");
		dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
	}

	/**
	 * Obtiene una conexión a la base de datos configurada en el
	 * {@code BasicDataSource}.
	 *
	 * @return una {@link Connection} válida a la base de datos
	 * @throws SQLException si no se puede establecer la conexión a la base de datos
	 */
	public static Connection getConnection() throws SQLException {
		return dataSource.getConnection();
	}
}
