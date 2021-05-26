package com.swvalerian.crud.repository.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    // драйвер подгружается автоматом, главное прописать его загрузку в Мавене.
    static private final String DATABASE_URL = "jdbc:mysql://localhost:3306/swvalerian";
    static private final String User = "root";
    static private final String Password = "QWERTgfdsa1980";

    static private Connection connection = null;

    public static Connection getConnection() {
        if (connection==null) {
            try {
                connection = DriverManager.getConnection(DATABASE_URL, User, Password);
            } catch (SQLException e) {
                System.err.println("Ошибка соединения с БД в классе DatabaseConnection");
            }
        }
        return connection;
    }
}
