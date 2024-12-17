package com.example.proj.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static Connection connection;

    private static final String DB_URL = "jdbc:postgresql://localhost:5434/hotel";  // Database URL
    private static final String DB_USER = "abdallah";  // Database username
    private static final String DB_PASSWORD = "root";  // Database password

    // Private constructor to prevent instantiation
    private DatabaseConnection() {}

    // Singleton method to get the database connection
    public static Connection getInstance() throws SQLException {
        if (connection == null || connection.isClosed()) {
            synchronized (DatabaseConnection.class) {
                if (connection == null || connection.isClosed()) {
                    try {
                        // Explicitly load the PostgreSQL JDBC driver
                        Class.forName("org.postgresql.Driver");

                        connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                    } catch (ClassNotFoundException e) {
                        throw new SQLException("PostgreSQL driver not found.", e);
                    }
                }
            }
        }
        return connection;
    }
}
