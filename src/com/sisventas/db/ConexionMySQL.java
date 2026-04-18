package com.sisventas.db;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConexionMySQL {
   private static final String USER = "app_user";
   private static final String URL = "jdbc:mysql://localhost:3306/sistema_inventario?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=America/Lima";
   private static final String PASSWORD = "1234";
   
    public static Connection getConnection() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
    