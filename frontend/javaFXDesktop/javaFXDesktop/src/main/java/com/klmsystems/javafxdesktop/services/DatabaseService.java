package com.klmsystems.javafxdesktop.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseService {

    private static final String DB_URL_POSTGRES = "jdbc:postgresql://localhost:5433/verifyme"; // Replace with your PostgreSQL database URL
    private static final String DB_URL_SQLITE = "jdbc:sqlite:local.db"; // Local SQLite database file

    private static final String DB_USER = "postgres"; // Replace with your PostgreSQL username
    private static final String DB_PASSWORD = "Soetsang@144156"; // Replace with your PostgreSQL password

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

    public Connection getSQLiteConnection() throws SQLException {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            System.err.println("SQLite JDBC Driver not found. Include the SQLite JDBC driver in your project.");
            e.printStackTrace();
            throw new SQLException("SQLite JDBC Driver not found.", e);
        }
        return DriverManager.getConnection(DB_URL_SQLITE);
    }
}