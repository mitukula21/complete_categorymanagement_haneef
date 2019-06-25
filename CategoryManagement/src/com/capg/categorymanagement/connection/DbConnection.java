package com.capg.categorymanagement.connection;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

import com.capg.categorymanagement.exception.CategoryManagementException;

public class DbConnection {

	static Connection connection = null;

	public static Connection getConnection() throws CategoryManagementException {

		File file = new File("resources/jdbc.properties");
		FileInputStream inputStream = null;
		Properties properties = new Properties();

		try {
			inputStream = new FileInputStream(file);
			properties.load(inputStream);

			String driver = properties.getProperty("db.driver");
			String url = properties.getProperty("db.url");
			String username = properties.getProperty("db.username");
			String password = properties.getProperty("db.password");

			Class.forName(driver);
			connection = DriverManager.getConnection(url, username, password);

		} catch (FileNotFoundException e1) {
			throw new CategoryManagementException("unable to read the data from the file");
		} catch (IOException e) {
			throw new CategoryManagementException("unable to load the file");
		} catch (ClassNotFoundException e) {
			throw new CategoryManagementException("problem occured while loading the class");
		} catch (SQLException e) {
			throw new CategoryManagementException("problem occured while establishing the connection");
		}

		return connection;
	}

}