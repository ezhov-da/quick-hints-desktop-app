/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.ezhov.regularexpression.connection;

import java.io.*;
import java.sql.*;
import java.util.logging.*;

/**
 * @author RRNDeonisiusEZH
 */
public class ApplicationConnection {
	private static Connection connection;

	private ApplicationConnection() {
	}

	;

	/**
	 * @return Connection
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws UnsupportedEncodingException
	 * @throws IllegalArgumentException
	 */
	public static Connection getInstance() throws ClassNotFoundException, SQLException, UnsupportedEncodingException, IllegalArgumentException {
		if (connection == null || connection.isClosed()) connection = CreateConnection.getConnection();
		return connection;
	}


	private static class CreateConnection {
		private static final String PASSWORD = "expression";
		private static final String USERNAME = "expression";
		private static final String FULL_NAME_BASE = "base.mv.db";
		private static final String NAME_BASE = "base";

		/**
		 * @return Connection
		 * @throws ClassNotFoundException
		 * @throws SQLException
		 * @throws UnsupportedEncodingException
		 * @throws IllegalArgumentException
		 */
		public static Connection getConnection() throws ClassNotFoundException, SQLException, UnsupportedEncodingException, IllegalArgumentException {
			Class.forName("org.h2.Driver");
						/*path to base*/
			String path = FULL_NAME_BASE;
                        /*path to file*/
			Logger.getLogger(CreateConnection.class.getName()).log(Level.INFO, path);
                        /*check base file*/
			if (!new File(path).isFile()) throw new IllegalArgumentException("not found base file");

			Connection connection = DriverManager.getConnection("jdbc:h2:" + "./" + NAME_BASE, PASSWORD, USERNAME);
			return connection;
		}
	}
}
