package com.klmsystems.javafxdesktop;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConfig {

    private static final String DB_URL_POSTGRES = "jdbc:postgresql://localhost:5432/attendance_db"; // Replace with your PostgreSQL database URL
    private static final String DB_USER = "postgres"; // Replace with your PostgreSQL username
    private static final String DB_PASSWORD = "password"; // Replace with your PostgreSQL password

    public Connection getPostgresConnection() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("PostgreSQL JDBC Driver not found. Include the PostgreSQL JDBC driver in your project.");
            e.printStackTrace();
            throw new SQLException("PostgreSQL JDBC Driver not found.", e);
        }
        return DriverManager.getConnection(DB_URL_POSTGRES, DB_USER, DB_PASSWORD);
    }
}