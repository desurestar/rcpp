package ru.zagrebin.laba6.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseUtil {
    public static final String URL = "jdbc:postgresql://localhost:5432/RCPP_laba6";
    public static final String USER = "dimon2";
    public static final String PASS = "190485";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }
}

