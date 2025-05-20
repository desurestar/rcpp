package ru.zagrebin.laba2;

import lombok.Getter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:postgresql://localhost:5432/laba4";
    private static final String USER = "dimon";
    private static final String PASSWORD = "admin";

    private static Connection connection;

    public static Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("Подключение к базе данных успешно");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка подключения к БД", e);
        }
        return connection;
    }

    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Соединение с БД закрыто");
            } catch (SQLException e) {
                System.err.println("Ошибка при закрытии соединения с БД " + e.getMessage());
            }
        }
    }
}

