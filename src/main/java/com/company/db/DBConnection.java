package com.company.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    static final String URL = "jdbc:postgresql://db:5432/diplom";
    static final String USER = "user";
    static final String PASSWORD = "secret";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
