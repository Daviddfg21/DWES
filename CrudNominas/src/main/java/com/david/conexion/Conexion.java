package com.david.conexion;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class Conexion {
	private static BasicDataSource dataSource;

	static {
		dataSource = new BasicDataSource();
		dataSource.setUrl("jdbc:mysql://localhost:3306/rrhh");
		dataSource.setUsername("root");
		dataSource.setPassword("12345");
		dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
	}

	public static Connection getConnection() throws SQLException {
		return dataSource.getConnection();
	}
}
