/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.ezhov.regularexpression.infrastructure.connection;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author RRNDeonisiusEZH
 */
public class ApplicationConnection {
    private static Connection connection;

    private ApplicationConnection() {
    }

    public static Connection getInstance() throws Exception {
        if (connection == null || connection.isClosed()) connection = CreateConnection.getConnection();
        return connection;
    }

    private static class CreateConnection {
        private static final String PASSWORD = "expression";
        private static final String USERNAME = "expression";
        private static final String FULL_NAME_BASE = "base.mv.db";
        private static final String NAME_BASE = "base";

        static Connection getConnection() throws Exception {
            Class.forName("org.h2.Driver");
            String path = FULL_NAME_BASE;
            Logger.getLogger(CreateConnection.class.getName()).log(Level.INFO, path);
            if (!new File(path).isFile()) throw new IllegalArgumentException("not found base file");
            return DriverManager.getConnection("jdbc:h2:" + "./" + NAME_BASE, PASSWORD, USERNAME);
        }
    }
}
